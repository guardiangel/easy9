package org.colin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.constants.Constant;
import org.colin.model.entity.SysRole;
import org.colin.model.vo.req.RoleAddReqVO;
import org.colin.model.vo.req.RolePageReqVO;
import org.colin.model.vo.req.RoleUpdateReqVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.service.RoleService;
import org.colin.utils.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
/**
 * @Description: RoleController
 * @ClassName: RoleController
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/27 12:28
 * @Version: 1.1.0
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织模块-角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    @ApiOperation(value = "新增角色接口")
    @LogAnnotation(title = "角色管理", action = "新增角色")
    @RequiresPermissions("sys:role:add")
    public DataResult<SysRole> addRole(@RequestBody @Valid RoleAddReqVO vo) {
        DataResult<SysRole> result = DataResult.success();
        result.setData(roleService.addRole(vo));
        return result;
    }

    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "删除角色接口")
    @LogAnnotation(title = "角色管理", action = "删除角色")
    @RequiresPermissions("sys:role:deleted")
    public DataResult deleted(@PathVariable("id") String id) {
        roleService.deletedRole(id);
        return DataResult.success();
    }

    @PostMapping("/roles")
    @ApiOperation(value = "分页获取角色信息接口")
    @LogAnnotation(title = "角色管理", action = "分页获取角色信息")
    @RequiresPermissions("sys:role:list")
    public DataResult<PageVO<SysRole>> pageInfo(@RequestBody RolePageReqVO vo) {
        DataResult<PageVO<SysRole>> result = DataResult.success();
        result.setData(roleService.pageInfo(vo));
        return result;
    }

    @GetMapping("/role/{id}")
    @ApiOperation(value = "查询角色详情接口")
    @RequiresPermissions("sys:role:detail")
    public DataResult<SysRole> detailInfo(@PathVariable("id") String id) {
        DataResult<SysRole> result = DataResult.success();
        result.setData(roleService.detailInfo(id));
        return result;
    }

    @PutMapping("/role")
    @ApiOperation(value = "更新角色信息接口")
    @LogAnnotation(title = "角色管理", action = "更新角色信息")
    @RequiresPermissions("sys:role:update")
    public DataResult updateDept(@RequestBody @Valid RoleUpdateReqVO vo, HttpServletRequest request) {
        roleService.updateRole(vo, request.getHeader(Constant.ACCESS_TOKEN));
        return DataResult.success();
    }
}
