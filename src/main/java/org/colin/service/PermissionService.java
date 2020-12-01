package org.colin.service;

import org.colin.model.entity.SysPermission;
import org.colin.model.vo.req.PermissionAddReqVO;
import org.colin.model.vo.req.PermissionPageReqVO;
import org.colin.model.vo.req.PermissionUpdateReqVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.PermissionRespNode;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    List<SysPermission> getPermission(String userId);

    Set<String> getPermissionsByUserId(String userId);

    //以树型的形式把用户拥有的菜单权限返回给客户端
    public List<PermissionRespNode> permissionTreeList(String userId);

    //获取所有菜单权限按钮
    public List<PermissionRespNode> selectAllByTree();

    //获取所有菜单权限
    public List<SysPermission> selectAll();

    //新增菜单
    public SysPermission addPermission(PermissionAddReqVO vo);

    //删除菜单权限
    public void deleted(String permissionId);

    //查询菜单详情
    public SysPermission detailInfo(String permissionId);

    //分页查询所有菜单权限
    public PageVO<SysPermission> pageInfo(PermissionPageReqVO vo);

    public List<PermissionRespNode> selectAllMenuByTree(String permissionId);

    //更新菜单信息
    public void updatePermission(PermissionUpdateReqVO vo);
}
