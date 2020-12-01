package org.colin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.model.vo.resp.EchartsRespVO;
import org.colin.service.UserService;
import org.colin.utils.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: EchartsController
 * @ClassName: EchartsController
 * @Author: wujiangbo
 * @Date: 2020/7/20 18:22
 * @Version: 1.1.0
 */
@Api(tags = "统计图管理", value = "负责返回统计图数据")
@RestController
@RequestMapping("/echarts")
@Slf4j
public class EchartsController {

    @Autowired
    private UserService userService;

    @GetMapping("/userEcharts/{type}")
    @ApiOperation(value = "统计图管理-获取用户统计图数据")
    @RequiresPermissions("echarts:userEcharts")
    public DataResult<EchartsRespVO> userEcharts(@PathVariable("type") String type) {
        DataResult<EchartsRespVO> result = DataResult.success();
        result.setData(userService.userEcharts(type));
        return result;
    }

    @GetMapping("/loginBrowser")
    @ApiOperation(value = "数据看版-用户登录浏览器分析")
    public DataResult<EchartsRespVO> loginBrowser() {
        DataResult<EchartsRespVO> result = DataResult.success();
        result.setData(userService.loginBrowser());
        return result;
    }
}
