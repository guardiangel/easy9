package org.colin.mapper;

import org.colin.model.entity.SysUserRole;

import java.util.List;
public interface SysUserRoleMapper {

    List<String> getRoleIdsByUserId(String userId);

    int removeByUserId(String userId);

    int batchUserRole(List<SysUserRole> list);

    List<String> getInfoByUserIdByRoleId(String roleId);

    int removeByRoleId(String roleId);

    List<String> getUserIdsByRoleIds(List<String> roleIds);
}
