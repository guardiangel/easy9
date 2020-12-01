package org.colin.mapper;

import org.colin.model.entity.SysRole;
import org.colin.model.vo.req.RolePageReqVO;

import java.util.List;
public interface SysRoleMapper {

    List<SysRole> getRoleInfoByIds(List<String> ids);

    List<SysRole> selectAll(RolePageReqVO vo);

    //根据ID删除角色信息
    int deleteByPrimaryKey(String id);

    //根据ID查询角色信息
    SysRole selectByPrimaryKey(String id);

    int insertSelective(SysRole record);

    int updateByPrimaryKeySelective(SysRole record);
}
