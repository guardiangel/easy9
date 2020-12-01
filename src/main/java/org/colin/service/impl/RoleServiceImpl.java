package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import org.colin.code.BaseResponseCode;
import org.colin.constants.Constant;
import org.colin.exception.ServiceException;
import org.colin.mapper.SysRoleMapper;
import org.colin.mapper.SysUserRoleMapper;
import org.colin.model.entity.SysRole;
import org.colin.model.vo.req.RoleAddReqVO;
import org.colin.model.vo.req.RolePageReqVO;
import org.colin.model.vo.req.RolePermissionOperationReqVO;
import org.colin.model.vo.req.RoleUpdateReqVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.PermissionRespNode;
import org.colin.service.*;
import org.colin.utils.PageUtils;
import org.colin.utils.TokenSettings;
import org.colin.utils.Tool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 * @Description: RoleServiceImpl
 * @ClassName: RoleServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:28
 * @Version: 1.1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenSettings tokenSettings;

    @Autowired
    private PermissionService permissionService;

    @Override
    public List<String> getRoleNames(String userId) {
        List<SysRole> sysRoles = getRoleInfoByUserId(userId);
        if (null == sysRoles || sysRoles.isEmpty()) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (SysRole sysRole : sysRoles) {
            list.add(sysRole.getName());
        }
        return list;
    }

    @Override
    public List<SysRole> getRoleInfoByUserId(String userId) {
        List<String> roleIds = userRoleService.getRoleIdsByUserId(userId);
        if (roleIds.isEmpty()) {
            return null;
        }
        return sysRoleMapper.getRoleInfoByIds(roleIds);
    }

    @Override
    public List<SysRole> selectAllRoles() {
        return sysRoleMapper.selectAll(new RolePageReqVO());
    }

    @Override
    public PageVO<SysRole> pageInfo(RolePageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysRole> sysRoles = sysRoleMapper.selectAll(vo);
        return PageUtils.getPageVO(sysRoles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletedRole(String roleId) {
        //删除角色表中角色信息
        int count = sysRoleMapper.deleteByPrimaryKey(roleId);
        if (count != 1) {
            throw new ServiceException(BaseResponseCode.OPERATION_ERROR);
        }
        //删除[角色权限关联表]中的角色关联信息
        rolePermissionService.removeByRoleId(roleId);
        //删除[角色用户关联表]中的角色关联信息
        userRoleService.removeByRoleId(roleId);
        //根据角色ID查询该角色下的所有用户ID集合
        List<String> userIds = sysUserRoleMapper.getInfoByUserIdByRoleId(roleId);
        //删除Redis中之前拥有过该角色的缓存信息
        if (!userIds.isEmpty()) {
            for (String userId : userIds) {
                redisService.set(Constant.JWT_REFRESH_KEY + userId, userId, tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
                //清空权鉴缓存
                redisService.delete(Constant.IDENTIFY_CACHE_KEY + userId);
            }
        }
    }

    @Override
    public SysRole detailInfo(String id) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        if (sysRole == null) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
        List<PermissionRespNode> permissionRespNodes = permissionService.selectAllByTree();
        Set<String> checkList = new HashSet<>(rolePermissionService.getPermissionIdsByRoleId(sysRole.getId()));
        setCheckced(permissionRespNodes, checkList);
        sysRole.setPermissionRespNodes(permissionRespNodes);
        return sysRole;
    }

    private void setCheckced(List<PermissionRespNode> list, Set<String> checkList) {
        for (PermissionRespNode node : list) {
            if (checkList.contains(node.getId()) && (node.getChildren() == null || node.getChildren().isEmpty())) {
                node.setChecked(true);
            }
            setCheckced((List<PermissionRespNode>) node.getChildren(), checkList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysRole addRole(RoleAddReqVO vo) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(vo, sysRole);
        sysRole.setId(Tool.getPrimaryKey());
        sysRole.setCreateTime(new Date());
        int count = sysRoleMapper.insertSelective(sysRole);
        if (count != 1) {
            throw new ServiceException(BaseResponseCode.OPERATION_ERROR);
        }
        if (null != vo.getPermissions() && !vo.getPermissions().isEmpty()) {
            RolePermissionOperationReqVO reqVO = new RolePermissionOperationReqVO();
            reqVO.setRoleId(sysRole.getId());
            reqVO.setPermissionIds(vo.getPermissions());
            rolePermissionService.addRolePermission(reqVO);
        }
        return sysRole;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(RoleUpdateReqVO vo, String accessToken) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(vo.getId());
        if (null == sysRole) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
        SysRole update = new SysRole();
        BeanUtils.copyProperties(vo, update);
        update.setUpdateTime(new Date());
        int count = sysRoleMapper.updateByPrimaryKeySelective(update);
        if (count != 1) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
        rolePermissionService.removeByRoleId(sysRole.getId());
        if (null != vo.getPermissions() && !vo.getPermissions().isEmpty()) {
            RolePermissionOperationReqVO reqVO = new RolePermissionOperationReqVO();
            reqVO.setRoleId(sysRole.getId());
            reqVO.setPermissionIds(vo.getPermissions());
            rolePermissionService.addRolePermission(reqVO);
            List<String> userIds = sysUserRoleMapper.getInfoByUserIdByRoleId(vo.getId());
            if (!userIds.isEmpty()) {
                for (String userId : userIds) {
                    redisService.set(Constant.JWT_REFRESH_KEY + userId, userId, tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
                    //清空权鉴缓存
                    redisService.delete(Constant.IDENTIFY_CACHE_KEY + userId);
                }
            }
        }
    }
}
