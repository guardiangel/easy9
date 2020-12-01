package org.colin.controller.yw;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.constants.Constant;
import org.colin.model.ro.YwFlwRequest;
import org.colin.model.vo.req.CheckRequestVO;
import org.colin.model.vo.req.FlwRequestVO;
import org.colin.model.vo.resp.FlwCheckInfoRespVO;
import org.colin.model.vo.resp.FlwRespVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.service.FlwRequestService;
import org.colin.utils.DataResult;
import org.colin.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/flw")
@Api(tags = "流程管理")
public class FlwManagerController {

    @Autowired
    private FlwRequestService flwRequestService;

    @PostMapping("/request/addFlwRequest")
    @ApiOperation(value = "新增流程申请")
    @LogAnnotation(title = "流程管理", action = "新增流程申请")
    @RequiresPermissions("flw:request:addFlwRequest")
    public DataResult insertSuggestion(@RequestBody YwFlwRequest obj, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        obj.setCreateUserId(user_id);
        flwRequestService.addFlwRequest(obj);
        return DataResult.success();
    }

    @PostMapping("/request/pageList")
    @ApiOperation(value = "分页获取当前用户所有的流程申请记录")
    public DataResult<PageVO<FlwRespVO>> requestPageList(@RequestBody FlwRequestVO vo, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        vo.setRequestUserId(user_id);
        DataResult<PageVO<FlwRespVO>> result = DataResult.success();
        result.setData(flwRequestService.flwPageInfo(vo));
        return result;
    }

    @PostMapping("/check/pageList")
    @ApiOperation(value = "分页获取当前用户需要审核的所有流程")
    public DataResult<PageVO<FlwRespVO>> checkPageList(@RequestBody CheckRequestVO vo, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        vo.setCheckUserId(user_id);
        DataResult<PageVO<FlwRespVO>> result = DataResult.success();
        result.setData(flwRequestService.checkPageInfo(vo));
        return result;
    }

    @PostMapping("/checkRequest")
    @ApiOperation(value = "对某个申请进行审核")
    @RequiresPermissions("flw:checkRequest")
    @LogAnnotation(title = "流程管理", action = "审核某申请流程")
    public DataResult checkRequest(@RequestBody CheckRequestVO vo, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        DataResult result = DataResult.success();
        flwRequestService.checkRequest(vo, user_id);
        return result;
    }

    @GetMapping("/getCheckList/{id}")
    @ApiOperation(value = "根据流程申请的主键ID查询所有审核记录信息")
    public DataResult<List<FlwCheckInfoRespVO>> getCheckList(@PathVariable("id") String id) {
        DataResult<List<FlwCheckInfoRespVO>> result = DataResult.success();
        result.setData(flwRequestService.getCheckList(id));
        return result;
    }
}
