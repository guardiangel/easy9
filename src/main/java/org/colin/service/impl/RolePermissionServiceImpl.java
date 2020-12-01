package org.colin.service.impl;

import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.mapper.SysRolePermissionMapper;
import org.colin.model.entity.SysRolePermission;
import org.colin.model.vo.req.RolePermissionOperationReqVO;
import org.colin.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * @Description: RolePermissionServiceImpl
 * @ClassName: RolePermissionServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:24
 * @Version: 1.1.0
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<String> getPermissionIdsByRoles(List<String> roleIds) {
        return sysRolePermissionMapper.getPermissionIdsByRoles(roleIds);
    }

    @Override
    public int removeByRoleId(String roleId) {
        return sysRolePermissionMapper.removeByRoleId(roleId);
    }

    @Override
    public List<String> getPermissionIdsByRoleId(String roleId) {
        return sysRolePermissionMapper.getPermissionIdsByRoleId(roleId);
    }

    @Override
    public void addRolePermission(RolePermissionOperationReqVO vo) {
        Date createTime = new Date();
        List<SysRolePermission> list = new ArrayList<>();
        for (String permissionId : vo.getPermissionIds()) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setId(UUID.randomUUID().toString());
            sysRolePermission.setCreateTime(createTime);
            sysRolePermission.setPermissionId(permissionId);
            sysRolePermission.setRoleId(vo.getRoleId());
            list.add(sysRolePermission);
        }
        sysRolePermissionMapper.removeByRoleId(vo.getRoleId());
        int count = sysRolePermissionMapper.batchRolePermission(list);
        if (count == 0) {
            throw new ServiceException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public int removeByPermissionId(String permissionId) {
        return sysRolePermissionMapper.removeByPermissionId(permissionId);
    }

    @Override
    public List<String> getRoleIds(String permissionId) {
        return sysRolePermissionMapper.getRoleIds(permissionId);
    }
}
