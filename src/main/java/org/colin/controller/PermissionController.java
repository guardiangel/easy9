package org.colin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.model.entity.SysPermission;
import org.colin.model.vo.req.PermissionAddReqVO;
import org.colin.model.vo.req.PermissionPageReqVO;
import org.colin.model.vo.req.PermissionUpdateReqVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.PermissionRespNode;
import org.colin.service.PermissionService;
import org.colin.utils.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * @Description: PermissionController
 * @ClassName: PermissionController
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/28 12:41
 * @Version: 1.1.0
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织模块-菜单管理")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/permission")
    @ApiOperation(value = "新增菜单权限接口")
    @LogAnnotation(title = "菜单权限管理", action = "新增菜单权限")
    @RequiresPermissions("sys:permission:add")
    public DataResult<SysPermission> addPermission(@RequestBody @Valid PermissionAddReqVO vo) {
        DataResult<SysPermission> result = DataResult.success();
        result.setData(permissionService.addPermission(vo));
        return result;
    }

    @DeleteMapping("/permission/{id}")
    @ApiOperation(value = "删除菜单权限接口")
    @LogAnnotation(title = "菜单权限管理", action = "删除菜单权限")
    @RequiresPermissions("sys:permission:deleted")
    public DataResult deleted(@PathVariable("id") String id) {
        DataResult result = DataResult.success();
        permissionService.deleted(id);
        return result;
    }

    @GetMapping("/permission/{id}")
    @ApiOperation(value = "查询菜单权限接口")
    @LogAnnotation(title = "菜单权限管理", action = "查询菜单权限")
    @RequiresPermissions("sys:permission:detail")
    public DataResult<SysPermission> detailInfo(@PathVariable("id") String id) {
        DataResult<SysPermission> result = DataResult.success();
        result.setData(permissionService.detailInfo(id));
        return result;
    }

    @PostMapping("/permissions")
    @ApiOperation(value = "分页查询菜单权限接口")
    @LogAnnotation(title = "菜单权限管理", action = "分页查询菜单权限")
    @RequiresPermissions("sys:permission:list")
    public DataResult<PageVO<SysPermission>> pageInfo(@RequestBody PermissionPageReqVO vo) {
        DataResult<PageVO<SysPermission>> result = DataResult.success();
        result.setData(permissionService.pageInfo(vo));
        return result;

    }

    @GetMapping("/permissions")
    @ApiOperation(value = "获取所有菜单权限接口")
    @LogAnnotation(title = "菜单权限管理", action = "获取所有菜单权限")
    @RequiresPermissions("sys:permission:list")
    public DataResult<List<SysPermission>> getAllMenusPermission() {
        DataResult<List<SysPermission>> result = DataResult.success();
        result.setData(permissionService.selectAll());
        return result;
    }

    @GetMapping("/permission/tree")
    @ApiOperation(value = "获取所有目录菜单树接口")
    @LogAnnotation(title = "菜单权限管理", action = "获取所有目录菜单树")
    @RequiresPermissions(value = {"sys:permission:update", "sys:permission:add"}, logical = Logical.OR)
    public DataResult<List<PermissionRespNode>> getAllMenusPermissionTree(@RequestParam(required = false) String permissionId) {
        DataResult<List<PermissionRespNode>> result = DataResult.success();
        result.setData(permissionService.selectAllMenuByTree(permissionId));
        return result;
    }

    @GetMapping("/permission/tree/all")
    @ApiOperation(value = "获取所有目录菜单树接口")
    @RequiresPermissions(value = {"sys:role:update", "sys:role:add"}, logical = Logical.OR)
    public DataResult<List<PermissionRespNode>> getAllPermissionTree() {
        DataResult<List<PermissionRespNode>> result = DataResult.success();
        result.setData(permissionService.selectAllByTree());
        return result;
    }

    @PutMapping("/permission")
    @ApiOperation(value = "更新菜单权限接口")
    @LogAnnotation(title = "菜单权限管理", action = "更新菜单权限")
    @RequiresPermissions("sys:permission:update")
    public DataResult updatePermission(@RequestBody @Valid PermissionUpdateReqVO vo) {
        DataResult result = DataResult.success();
        permissionService.updatePermission(vo);
        return result;
    }
}
