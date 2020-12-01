package org.colin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.model.vo.req.BbsReplyPageReqVO;
import org.colin.model.vo.req.SysSuggestionPageReqVO;
import org.colin.model.vo.resp.*;
import org.colin.service.BbsPostService;
import org.colin.service.HomeService;
import org.colin.utils.DataResult;
import org.colin.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @Description: HomeController
 * @ClassName: HomeController
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 20:08
 * @Version: 1.1.0
 */
@RestController
@RequestMapping("/sys")
@Api(tags = "首页数据")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private BbsPostService bbsPostService;

    @DeleteMapping("/suggestion/delete/{id}")
    @ApiOperation(value = "删除意见反馈")
    @LogAnnotation(title = "系统管理", action = "删除意见反馈信息")
    @RequiresPermissions("sys:suggestion:delete")
    public DataResult deleteById(@PathVariable("id") String id) {
        bbsPostService.deleteSuggestionByPkId(id);
        return DataResult.success();
    }

    @PostMapping("/suggestion/list")
    @ApiOperation(value = "分页获取所有意见反馈信息")
    @LogAnnotation(title = "系统管理", action = "分页获取所有意见反馈信息")
    @RequiresPermissions("sys:suggestion:list")
    public DataResult<PageVO<SysSuggestionVO>> suggestionPageInfo(@RequestBody SysSuggestionPageReqVO vo) {
        DataResult<PageVO<SysSuggestionVO>> result = DataResult.success();
        result.setData(bbsPostService.suggestionPageInfo(vo));
        return result;
    }

    @ApiOperation(value = "根据表名查询表结构信息")
    @GetMapping("/queryTableByName/{tableName}")
    @LogAnnotation(title = "查询表结构信息", action = "查询表结构信息")
    @RequiresPermissions("sys:queryTableByName")
    public DataResult<List<TableStructureVO>> queryTableByName(@PathVariable("tableName") String tableName) {
        List<TableStructureVO> resultList = homeService.selectTabelByName(tableName);
        DataResult<List<TableStructureVO>> result = DataResult.success();
        result.setData(resultList);
        return result;
    }

    @ApiOperation(value = "查询所有数据库表信息")
    @GetMapping("/queryAllTable")
    @LogAnnotation(title = "查询所有数据库表信息", action = "查询所有数据库表信息")
    @RequiresPermissions("sys:queryAllTable")
    public DataResult<List<TableVO>> getAllTable() {
        List<TableVO> resultList = homeService.selectAllTable();
        DataResult<List<TableVO>> result = DataResult.success();
        result.setData(resultList);
        return result;
    }

    @GetMapping("/home")
    @ApiOperation(value = "获取首页数据接口")
    public DataResult<HomeRespVO> getHomeInfo(HttpServletRequest request) {
        String accessToken = request.getHeader("authorization");
        /**
         * 通过access_token拿userId
         */
        String userId = JwtTokenUtil.getUserId(accessToken);
        DataResult<HomeRespVO> result = DataResult.success();
        result.setData(homeService.getHomeInfo(userId));
        return result;
    }

    @GetMapping("/getIpAddressInfo")
    @ApiOperation(value = "获取用户登录归属地信息")
    public DataResult<List<EchartsVO>> getIpAddressInfo(HttpServletRequest request) {
        DataResult<List<EchartsVO>> result = DataResult.success();
        result.setData(homeService.getIpAddressInfo());
        return result;
    }

    @GetMapping("/getMainInfo")
    @ApiOperation(value = "获取main.html页面中的数据信息")
    public DataResult<MainVO> getMainInfo(HttpServletRequest request) {
        DataResult<MainVO> result = DataResult.success();
        result.setData(homeService.getMainInfo());
        return result;
    }
}
