package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.colin.code.BaseResponseCode;
import org.colin.constants.Constant;
import org.colin.exception.ServiceException;
import org.colin.mapper.SysPermissionMapper;
import org.colin.model.entity.SysPermission;
import org.colin.model.vo.req.PermissionAddReqVO;
import org.colin.model.vo.req.PermissionPageReqVO;
import org.colin.model.vo.req.PermissionUpdateReqVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.PermissionRespNode;
import org.colin.service.PermissionService;
import org.colin.service.RedisService;
import org.colin.service.RolePermissionService;
import org.colin.service.UserRoleService;
import org.colin.utils.PageUtils;
import org.colin.utils.TokenSettings;
import org.colin.utils.Tool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 * @Description: PermissionServiceImpl
 * @ClassName: PermissionServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:18
 * @Version: 1.1.0
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenSettings tokenSettings;

    /*
     * @Description: 根据用户ID查询拥有的资源权限信息
     * @MethodName: getPermission
     * @param:  [userId]
     * @return: java.util.List<org.colin.model.entity.SysPermission>
     * @Author: wujiangbo
     * @Date: 2020/2/17 10:21
     */
    @Override
    public List<SysPermission> getPermission(String userId) {
        List<SysPermission> result = sysPermissionMapper.selectInfoByUserIds(userId);
        return result;
    }

    /*
     * @Description: 获取权限标识
     * @MethodName: getPermissionsByUserId
     * @param:  [userId]
     * @return: java.util.Set<java.lang.String>
     * @Author: wujiangbo
     * @Date: 2020/2/17 10:18
     */
    @Override
    public Set<String> getPermissionsByUserId(String userId) {
        List<SysPermission> list = getPermission(userId);
        Set<String> permissions = new HashSet<>();
        if (null == list || list.isEmpty()) {
            return null;
        }
        for (SysPermission sysPermission : list) {
            if (!StringUtils.isEmpty(sysPermission.getPerms())) {
                permissions.add(sysPermission.getPerms());
            }

        }
        return permissions;
    }

    @Override
    public List<PermissionRespNode> permissionTreeList(String userId) {
        List<SysPermission> list = getPermission(userId);
        return getTree(list, true);
    }

    /*
     * @Description: 递归获取菜单树
     * @MethodName: getTree
     * @param:  [all, type(true:查询到菜单；false:查询到按钮)]
     * @return: java.util.List<org.colin.model.vo.resp.PermissionRespNode>
     * @Author: wujiangbo
     * @Date: 2020/2/17 20:16
     */
    private List<PermissionRespNode> getTree(List<SysPermission> all, boolean type) {
        List<PermissionRespNode> list = new ArrayList<>();
        if (all == null || all.isEmpty()) {
            return list;
        }
        for (SysPermission sysPermission : all) {
            if (sysPermission.getPid().equals("0")) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                if (type) {
                    permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(), all));
                } else {
                    permissionRespNode.setChildren(getChildAll(sysPermission.getId(), all));
                }
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    /*
     * @Description: 只递归获取目录和菜单
     * @MethodName: getChildExcBtn
     * @param:  [id, all]
     * @return: java.util.List<org.colin.model.vo.resp.PermissionRespNode>
     * @Author: wujiangbo
     * @Date: 2020/2/17 20:17
     */
    private List<PermissionRespNode> getChildExcBtn(String id, List<SysPermission> all) {
        List<PermissionRespNode> list = new ArrayList<>();
        for (SysPermission sysPermission : all) {
            if (sysPermission.getPid().equals(id) && sysPermission.getType() != 3) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(), all));
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    /*
     * @Description: 递归遍历所有
     * @MethodName: getChildAll
     * @param:  [id, all]
     * @return: java.util.List<org.colin.model.vo.resp.PermissionRespNode>
     * @Author: wujiangbo
     * @Date: 2020/2/17 20:17
     */
    private List<PermissionRespNode> getChildAll(String id, List<SysPermission> all) {
        List<PermissionRespNode> list = new ArrayList<>();
        for (SysPermission sysPermission : all) {
            if (sysPermission.getPid().equals(id)) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                permissionRespNode.setChildren(getChildAll(sysPermission.getId(), all));
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    @Override
    public List<SysPermission> selectAll() {
        List<SysPermission> result = sysPermissionMapper.selectAll();
        if (!result.isEmpty()) {
            for (SysPermission sysPermission : result) {
                SysPermission parent = sysPermissionMapper.selectByPrimaryKey(sysPermission.getPid());
                if (parent != null) {
                    sysPermission.setPidName(parent.getName());
                }
            }
        }
        return result;
    }

    @Override
    public List<PermissionRespNode> selectAllByTree() {
        List<SysPermission> list = selectAll();
        return getTree(list, false);
    }

    //新增菜单权限
    @Override
    public SysPermission addPermission(PermissionAddReqVO vo) {
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(vo, sysPermission);
        verifyForm(sysPermission);
        sysPermission.setId(Tool.getPrimaryKey());
        sysPermission.setCreateTime(new Date());
        int count = sysPermissionMapper.insertSelective(sysPermission);
        if (count != 1) {
            throw new ServiceException(BaseResponseCode.OPERATION_ERROR);
        }
        return sysPermission;
    }

    /*
     * @Description: 编辑或者新增的时候检验
     * @MethodName: verifyForm
     * @param:  [sysPermission]
     * @return: void
     * @Author: wujiangbo
     * @Date: 2020/2/28 13:13
     */
    private void verifyForm(SysPermission sysPermission) {
        verifyFormPid(sysPermission);
        //ID不为空说明是编辑
        if (!StringUtils.isEmpty(sysPermission.getId())) {
            List<SysPermission> list = sysPermissionMapper.selectChild(sysPermission.getId());
            if (!list.isEmpty()) {
                throw new ServiceException(BaseResponseCode.OPERATION_MENU_PERMISSION_UPDATE);
            }
        }
    }

    /**
     * 操作后的菜单类型是目录的时候 父级必须为目录
     * 操作后的菜单类型是菜单的时候，父类必须为目录类型
     * 操作后的菜单类型是按钮的时候 父类必须为菜单类型
     */
    private void verifyFormPid(SysPermission sysPermission) {
        SysPermission parent = sysPermissionMapper.selectByPrimaryKey(sysPermission.getPid());
        switch (sysPermission.getType()) {
            case 1:
                if (parent != null) {
                    if (parent.getType() != 1) {
                        throw new ServiceException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                    }
                } else if (!sysPermission.getPid().equals("0")) {
                    throw new ServiceException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                }
                break;
            case 2:
                if (parent == null || parent.getType() != 1) {
                    throw new ServiceException(BaseResponseCode.OPERATION_MENU_PERMISSION_MENU_ERROR);
                }
                if (StringUtils.isEmpty(sysPermission.getUrl())) {
                    throw new ServiceException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                break;
            case 3:
                if (parent == null || parent.getType() != 2) {
                    throw new ServiceException(BaseResponseCode.OPERATION_MENU_PERMISSION_BTN_ERROR);
                }
                if (StringUtils.isEmpty(sysPermission.getPerms())) {
                    throw new ServiceException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_PERMS_NULL);
                }
                if (StringUtils.isEmpty(sysPermission.getUrl())) {
                    throw new ServiceException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                if (StringUtils.isEmpty(sysPermission.getMethod())) {
                    throw new ServiceException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_METHOD_NULL);
                }
                break;
        }
    }

    //删除菜单权限
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleted(String permissionId) {
        SysPermission sysPermission = sysPermissionMapper.selectByPrimaryKey(permissionId);
        if (null == sysPermission) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
        List<SysPermission> childs = sysPermissionMapper.selectChild(permissionId);
        if (!childs.isEmpty()) {
            throw new ServiceException(BaseResponseCode.ROLE_PERMISSION_RELATION);
        }
        sysPermission.setDeleted(0);
        sysPermission.setUpdateTime(new Date());
        int count = sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
        if (count != 1) {
            throw new ServiceException(BaseResponseCode.OPERATION_ERROR);
        }
        /**
         * 删除和角色关联
         */
        rolePermissionService.removeByPermissionId(permissionId);
        List<String> roleIds = rolePermissionService.getRoleIds(permissionId);
        if (!roleIds.isEmpty()) {
            List<String> userIds = userRoleService.getUserIdsByRoleIds(roleIds);
            if (!userIds.isEmpty()) {
                for (String userId : userIds) {
                    redisService.set(Constant.JWT_REFRESH_KEY + userId, userId, tokenSettings.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
                    //清空权鉴缓存
                    redisService.delete(Constant.IDENTIFY_CACHE_KEY + userId);
                }
            }
        }
    }

    @Override
    public SysPermission detailInfo(String permissionId) {
        return sysPermissionMapper.selectByPrimaryKey(permissionId);
    }

    //分页查询所有菜单权限
    @Override
    public PageVO<SysPermission> pageInfo(PermissionPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysPermission> list = selectAll();
        return PageUtils.getPageVO(list);
    }

    /*
     * @Description: 获取所有的目录菜单树排除按钮(因为不管是新增或者修改,选择所属菜单目录的时候,都不可能选择到按钮,而且编辑的时候 所属目录不能选择自己和它的子类)
     * @MethodName: selectAllMenuByTree
     * @param:  [permissionId]
     * @return: java.util.List<org.colin.model.vo.resp.PermissionRespNode>
     * @Author: wujiangbo
     * @Date: 2020/2/28 13:29
     */
    @Override
    public List<PermissionRespNode> selectAllMenuByTree(String permissionId) {
        List<SysPermission> list = selectAll();
        if (!list.isEmpty() && !StringUtils.isEmpty(permissionId)) {
            for (SysPermission sysPermission : list) {
                if (sysPermission.getId().equals(permissionId)) {
                    list.remove(sysPermission);
                    break;
                }
            }
        }
        List<PermissionRespNode> result = new ArrayList<>();
        //新增顶级目录是为了方便添加一级目录
        PermissionRespNode respNode = new PermissionRespNode();
        respNode.setId("0");
        respNode.setTitle("默认顶级菜单");
        respNode.setSpread(true);
        respNode.setChildren(getTree(list, true));
        result.add(respNode);
        return result;
    }

    //更新菜单权限
    @Override
    public void updatePermission(PermissionUpdateReqVO vo) {
        SysPermission sysPermission = sysPermissionMapper.selectByPrimaryKey(vo.getId());
        if (null == sysPermission) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
        SysPermission update = new SysPermission();
        BeanUtils.copyProperties(vo, update);
        /**
         * 只有类型变更
         * 或者所属菜单变更
         */
        if (sysPermission.getType() != vo.getType() || !sysPermission.getPid().equals(vo.getPid())) {
            verifyForm(update);
        }
        update.setUpdateTime(new Date());
        int count = sysPermissionMapper.updateByPrimaryKeySelective(update);
        if (count != 1) {
            throw new ServiceException(BaseResponseCode.OPERATION_ERROR);
        }
        /**
         * 说明这个菜单权限 权鉴标识发生变化
         * 所有管理这个菜单权限用户将重新刷新token
         */
        if (StringUtils.isEmpty(vo.getPerms()) && !sysPermission.getPerms().equals(vo.getPerms())) {
            List<String> roleIds = rolePermissionService.getRoleIds(vo.getId());
            if (!roleIds.isEmpty()) {
                List<String> userIds = userRoleService.getUserIdsByRoleIds(roleIds);
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
}
