package org.colin.mapper;

import org.colin.model.entity.SysRolePermission;

import java.util.List;
public interface SysRolePermissionMapper {

    List<String> getPermissionIdsByRoles(List<String> roleIds);

    int removeByRoleId(String roleId);

    List<String> getPermissionIdsByRoleId(String roleId);

    int batchRolePermission(List<SysRolePermission> list);

    int removeByPermissionId(String permissionId);

    List<String> getRoleIds(String permissionId);
}
