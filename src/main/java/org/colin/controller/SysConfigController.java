package org.colin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.constants.Constant;
import org.colin.constants.DicTypeCode;
import org.colin.model.ro.SysConfig;
import org.colin.model.vo.req.SysConfigReqVO;
import org.colin.model.vo.resp.SysConfigRespVO;
import org.colin.model.vo.resp.SysDicInfoVO;
import org.colin.service.SysConfigService;
import org.colin.service.SysDicInfoService;
import org.colin.utils.DataResult;
import org.colin.utils.JwtTokenUtil;
import org.colin.utils.Tool;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/config")
@Api(tags = "系统模块-系统参数配置")
@RestController
public class SysConfigController {

    @Resource
    private SysConfigService sysConfigService;

    @Resource
    private SysDicInfoService sysDicInfoService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增系统参数配置")
    @LogAnnotation(title = "系统参数", action = "新增系统参数")
    @RequiresPermissions("config:insert")
    public DataResult addDept(@RequestBody SysConfig vo, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        vo.setUpdateUserId(user_id);
        sysConfigService.addConfig(vo);
        return DataResult.success();
    }

    @DeleteMapping("/delConfig/{id}")
    @ApiOperation(value = "删除系统参数")
    @LogAnnotation(title = "系统参数", action = "删除系统参数")
    @RequiresPermissions("config:delConfig")
    public DataResult deleted(@PathVariable("id") String id) {
        sysConfigService.deleteConfig(id);
        return DataResult.success();
    }

    @GetMapping("/queryById/{id}")
    @ApiOperation(value = "查询指定系统参数详情")
    @RequiresPermissions("config:queryById")
    public DataResult<SysConfigRespVO> queryById(@PathVariable("id") String id) {
        DataResult<SysConfigRespVO> result = DataResult.success();
        result.setData(sysConfigService.selectById(id));
        return result;
    }

    @GetMapping("/queryByKey/{key}")
    @ApiOperation(value = "查询指定系统参数详情")
    public DataResult<SysConfig> queryByKey(@PathVariable("key") String key) {
        DataResult<SysConfig> result = DataResult.success();
        SysConfig config = sysConfigService.queryByKey(key);
        List<SysDicInfoVO> dicInfoList = sysDicInfoService.getAllOKDicByTypeCode(DicTypeCode.TYPE_CODE_1);//获取所有心灵鸡汤
        String xljt = "";
        if(CollectionUtils.isNotEmpty(dicInfoList)){
            xljt = dicInfoList.get(Integer.valueOf(Tool.getRandomString(0, dicInfoList.size()-1, 1))).getDicValue();
        }
        config.setXljt(xljt);
        result.setData(config);
        return result;
    }

    @PostMapping("/queryAll")
    @ApiOperation(value = "查询所有系统参数")
    @RequiresPermissions("config:queryAll")
    public DataResult<List<SysConfigRespVO>> queryAll(@RequestBody SysConfigReqVO vo) {
        DataResult<List<SysConfigRespVO>> result = DataResult.success();
        result.setData(sysConfigService.selectAllConfig(vo));
        return result;
    }

    @PutMapping("/updateConfig")
    @ApiOperation(value = "更新组织信息接口")
    @LogAnnotation(title = "系统参数", action = "更新系统参数")
    @RequiresPermissions("config:updateConfig")
    public DataResult updateDept(@RequestBody @Valid SysConfig vo, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        vo.setUpdateUserId(user_id);
        sysConfigService.updateConfig(vo);
        return DataResult.success();
    }
}
