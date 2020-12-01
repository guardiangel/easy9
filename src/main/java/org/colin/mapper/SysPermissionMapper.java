package org.colin.mapper;

import org.colin.model.entity.SysPermission;

import java.util.List;
public interface SysPermissionMapper {

    List<SysPermission> selectInfoByIds(List<String> ids);

    List<SysPermission> selectInfoByUserIds(String userId);

    List<SysPermission> selectAll();

    SysPermission selectByPrimaryKey(String id);

    int insertSelective(SysPermission record);

    List<SysPermission> selectChild(String pid);

    int updateByPrimaryKeySelective(SysPermission record);
}
