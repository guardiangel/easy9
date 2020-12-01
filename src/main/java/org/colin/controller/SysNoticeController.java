package org.colin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.constants.Constant;
import org.colin.model.ro.SysNotice;
import org.colin.model.vo.resp.SysNoticeRespVO;
import org.colin.service.SysNoticeService;
import org.colin.utils.DataResult;
import org.colin.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/notice")
@Api(tags = "信息发布")
@RestController
public class SysNoticeController {

    @Autowired
    private SysNoticeService sysNoticeService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增信息发布数据")
    @LogAnnotation(title = "信息发布", action = "新增信息发布数据")
    @RequiresPermissions("notice:insert")
    public DataResult insert(@RequestBody SysNotice vo, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        vo.setUpdateUser(user_id);
        sysNoticeService.add(vo);
        return DataResult.success();
    }

    @DeleteMapping("/delNotice/{id}")
    @ApiOperation(value = "删除信息发布数据")
    @LogAnnotation(title = "信息发布", action = "删除信息发布数据")
    @RequiresPermissions("notice:delNotice")
    public DataResult deleted(@PathVariable("id") String id) {
        sysNoticeService.deleteById(id);
        return DataResult.success();
    }

    @GetMapping("/queryById/{id}")
    @ApiOperation(value = "查询指定发布信息数据")
    public DataResult<SysNoticeRespVO> queryById(@PathVariable("id") String id) {
        DataResult<SysNoticeRespVO> result = DataResult.success();
        result.setData(sysNoticeService.getNoticeById(id));
        return result;
    }

    @GetMapping("/queryByType/{type}")
    @ApiOperation(value = "查询指定发布信息数据")
    public DataResult<SysNoticeRespVO> queryByType(@PathVariable("type") String type) {
        DataResult<SysNoticeRespVO> result = DataResult.success();
        result.setData(sysNoticeService.getNoticeByType(type));
        return result;
    }

    @PostMapping("/queryAll")
    @ApiOperation(value = "查询所有信息发布数据")
    @RequiresPermissions("notice:queryAll")
    public DataResult<List<SysNoticeRespVO>> queryAll() {
        DataResult<List<SysNoticeRespVO>> result = DataResult.success();
        result.setData(sysNoticeService.selectAll());
        return result;
    }

    @PutMapping("/updateNotice")
    @ApiOperation(value = "更新信息发布数据")
    @LogAnnotation(title = "信息发布", action = "更新信息发布数据")
    @RequiresPermissions("notice:updateNotice")
    public DataResult updateNotice(@RequestBody SysNotice vo, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        vo.setUpdateUser(user_id);
        sysNoticeService.updateObj(vo);
        return DataResult.success();
    }
}
