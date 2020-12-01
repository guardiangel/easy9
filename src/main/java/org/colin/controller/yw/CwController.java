package org.colin.controller.yw;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.model.entity.YwCbfq;
import org.colin.model.vo.resp.PageVO;
import org.colin.service.YwCbfqService;
import org.colin.utils.DataResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/cw")
@Api(tags = "财务系统")
public class CwController {

    @Resource
    private YwCbfqService cwService;

    @PostMapping("/cwbb/insert")
    @ApiOperation(value = "新增财务报表信息")
    @LogAnnotation(title = "财务报表", action = "新增财务报表信息")
    @RequiresPermissions("cw:cwbb:insert")
    public DataResult cwInsert(@RequestBody YwCbfq obj) {
        cwService.addObject(obj);
        return DataResult.success();
    }

    @DeleteMapping("/cwbb/delete/{id}")
    @ApiOperation(value = "删除财务报表信息")
    @LogAnnotation(title = "财务报表", action = "删除财务报表信息")
    @RequiresPermissions("cw:cwbb:delete")
    public DataResult cwDelete(@PathVariable("id") String id) {
        cwService.deleteObject(id);
        return DataResult.success();
    }

    @PostMapping("/cwbb/pageList")
    @ApiOperation(value = "分页查询所有财务报表信息")
    public DataResult<PageVO<YwCbfq>> pageList(@RequestBody YwCbfq vo) {
        DataResult<PageVO<YwCbfq>> result = DataResult.success();
        result.setData(cwService.selectPageInfo(vo));
        return result;
    }

    @GetMapping("/cwbb/queryById/{id}")
    @ApiOperation(value = "查看财务报表详情信息")
    @LogAnnotation(title = "财务报表", action = "查看财务报表详情信息")
    @RequiresPermissions("cw:cwbb:queryById")
    public DataResult<YwCbfq> queryById(@PathVariable("id") String id) {
        DataResult<YwCbfq> result = DataResult.success();
        result.setData(cwService.selectById(id));
        return result;
    }

    @PutMapping("/cwbb/update")
    @ApiOperation(value = "更新财务报表信息")
    @LogAnnotation(title = "财务报表", action = "更新财务报表信息")
    @RequiresPermissions("cw:cwbb:update")
    public DataResult cwUpdate(@RequestBody YwCbfq obj) {
        cwService.updateObj(obj);
        return DataResult.success();
    }
}
