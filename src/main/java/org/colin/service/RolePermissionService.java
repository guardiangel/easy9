package org.colin.service;

import org.colin.model.vo.req.RolePermissionOperationReqVO;

import java.util.List;
public interface RolePermissionService {

    List<String> getPermissionIdsByRoles(List<String> roleIds);

    public int removeByRoleId(String roleId);

    public List<String> getPermissionIdsByRoleId(String roleId);

    public void addRolePermission(RolePermissionOperationReqVO vo);

    public int removeByPermissionId(String permissionId);

    public List<String> getRoleIds(String permissionId);
}
