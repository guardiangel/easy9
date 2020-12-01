package org.colin.controller.yw;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.constants.Constant;
import org.colin.mapper.yw.YwSalaryMapper;
import org.colin.model.entity.SysDept;
import org.colin.model.vo.req.SalaryAddReqVO;
import org.colin.model.vo.req.SalaryPageReqVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.YwSalaryVO;
import org.colin.service.AdministrationService;
import org.colin.utils.DataResult;
import org.colin.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Description: 行政管理
 * @ClassName: AdministrationController
 * @Author: wujiangbo
 * @Date: 2020/7/13 19:43
 * @Version: 1.1.0
 */
@Controller
@Api(tags = "行政管理接口")
@RequestMapping("/xzgl")
@Slf4j
public class AdministrationController {

    @Autowired
    private AdministrationService administrationService;

    @Autowired
    private YwSalaryMapper ywSalaryMapper;

    @ResponseBody
    @PostMapping("/salary/add")
    @ApiOperation(value = "新增工资记录")
    @LogAnnotation(title = "行政管理", action = "新增工资记录")
    @RequiresPermissions("xzgl:salary:add")
    public DataResult addSalary(@RequestBody @Valid SalaryAddReqVO vo, HttpServletRequest request) {
        DataResult<SysDept> result = DataResult.success();
        //获取当前登录者ID
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        vo.setCreateUserId(user_id);
        administrationService.addSaraly(vo);
        return DataResult.success();
    }

    @ResponseBody
    @DeleteMapping("/saraly/delete/{id}")
    @ApiOperation(value = "删除工资记录")
    @LogAnnotation(title = "行政管理", action = "删除工资记录")
    @RequiresPermissions("xzgl:salary:deleted")
    public DataResult deleted(@PathVariable("id") String id) {
        administrationService.deleteSaraly(id);
        return DataResult.success();
    }

    @ResponseBody
    @PostMapping("/salary/list")
    @ApiOperation(value = "分页查询所有工资记录")
    @RequiresPermissions("xzgl:salary:list")
    public DataResult<PageVO<YwSalaryVO>> pageInfo(@RequestBody SalaryPageReqVO ro) {
        PageVO<YwSalaryVO> pageVO = administrationService.pageInfo(ro);
        DataResult<PageVO<YwSalaryVO>> result = DataResult.success();
        result.setData(pageVO);
        return result;
    }

    @PostMapping("/salary/export")
    @ApiOperation(value = "根据条件导出所有工资记录")
    @RequiresPermissions("xzgl:salary:export")
    public void export(SalaryPageReqVO ro, HttpServletResponse response) {
        administrationService.export(ro, response);
    }

}
