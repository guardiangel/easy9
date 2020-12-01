package org.colin.service;

import org.colin.model.entity.SysRole;
import org.colin.model.vo.req.RoleAddReqVO;
import org.colin.model.vo.req.RolePageReqVO;
import org.colin.model.vo.req.RoleUpdateReqVO;
import org.colin.model.vo.resp.PageVO;

import java.util.List;
public interface RoleService {

    public List<String> getRoleNames(String userId);

    public List<SysRole> getRoleInfoByUserId(String userId);

    List<SysRole> selectAllRoles();

    //分页查询角色列表
    public PageVO<SysRole> pageInfo(RolePageReqVO vo);

    //根据ID删除角色
    public void deletedRole(String id);

    //根据ID查询角色详情
    public SysRole detailInfo(String id);

    //新增角色
    public SysRole addRole(RoleAddReqVO vo);

    //更新角色信息
    public void updateRole(RoleUpdateReqVO vo, String accessToken);
}
