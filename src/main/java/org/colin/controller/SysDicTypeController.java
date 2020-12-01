package org.colin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.constants.Constant;
import org.colin.model.dto.DictionaryTree;
import org.colin.model.entity.SysDicType;
import org.colin.model.ro.SysDicInfo;
import org.colin.model.vo.resp.DicSelectInfoVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.SysDicInfoVO;
import org.colin.model.vo.resp.SysDicTypeVO;
import org.colin.service.SysDicInfoService;
import org.colin.service.SysDicTypeService;
import org.colin.utils.DataResult;
import org.colin.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 数据字典
 * @ClassName: SysDicTypeController
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020-05-21 14:46:32
 * @Version: 1.1.0
 */
@RestController
@RequestMapping("/sys/dic")
@Api(tags = "数据字典")
public class SysDicTypeController {

    @Autowired
    private SysDicTypeService sysDicTypeService;

    @Autowired
    private SysDicInfoService sysDicInfoService;

    @ApiOperation(value = "查询select选择框中的数据字典信息")
    @GetMapping("/getSelectDicByTypeCode")
    public DataResult<List<DicSelectInfoVO>> getSelectDicByTypeCode() {
        DataResult<List<DicSelectInfoVO>> result = DataResult.success();
        result.setData(sysDicInfoService.selectDicByTypeCode());
        return result;
    }

    @ApiOperation(value = "根据数据字典类型查看其下所有数据字典详情")
    @GetMapping("/getAllDicByTypeCode/{typeCode}")
    @LogAnnotation(title = "数据字典管理", action = "查看数据字典详情")
    @RequiresPermissions("sys:dic:listDicInfo")
    public DataResult<List<SysDicInfoVO>> getAllDicByTypeCode(@PathVariable("typeCode") String typeCode) {
        List<SysDicInfoVO> dicInfoVoList = sysDicInfoService.getAllDicByTypeCode(typeCode);
        DataResult<List<SysDicInfoVO>> result = DataResult.success();
        result.setData(dicInfoVoList);
        return result;
    }

    @ApiOperation(value = "新增数据字典")
    @PostMapping("/addDicInfo")
    @RequiresPermissions("sys:dic:addDicInfo")
    @LogAnnotation(title = "数据字典管理", action = "新增数据字典")
    public DataResult<SysDicType> addDicInfo(@RequestBody @Valid SysDicInfo sysDicInfo, HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        DataResult<SysDicType> result = DataResult.success();
        sysDicInfoService.add(sysDicInfo, userId);
        return result;
    }

    @ApiOperation(value = "删除数据字典")
    @DeleteMapping("/deleteDicInfo/{id}")
    @RequiresPermissions("sys:dic:deleteDicInfo")
    @LogAnnotation(title = "数据字典管理", action = "删除数据字典")
    public DataResult deleteDicInfo(@PathVariable("id") String id) {
        sysDicInfoService.deleteById(id);
        return DataResult.success();
    }

    @ApiOperation(value = "更新数据字典")
    @PutMapping("/updateDicInfo")
    @RequiresPermissions("sys:dic:updateDicInfo")
    @LogAnnotation(title = "数据字典管理", action = "更新数据字典")
    public DataResult updateDicInfo(@RequestBody @Valid SysDicInfo sysDicInfo, HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        sysDicInfoService.update(sysDicInfo, userId);
        return DataResult.success();
    }

    @ApiOperation(value = "新增数据字典类型")
    @PostMapping("/addDicType")
    @RequiresPermissions("sys:dic:addDicType")
    @LogAnnotation(title = "数据字典管理", action = "新增数据字典类型")
    public DataResult<SysDicType> addDicType(@RequestBody @Valid SysDicType sysDicType, HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        DataResult<SysDicType> result = DataResult.success();
        sysDicTypeService.add(sysDicType, userId);
        return result;
    }

    @ApiOperation(value = "删除数据字典类型")
    @DeleteMapping("/deleteDicType/{typeCode}")
    @RequiresPermissions("sys:dic:deleteDicType")
    @LogAnnotation(title = "数据字典管理", action = "删除数据字典类型")
    public DataResult deleteDicType(@PathVariable("typeCode") String typeCode) {
        sysDicTypeService.deleteByTypeCode(typeCode);
        return DataResult.success();
    }

    @ApiOperation(value = "更新数据字典类型")
    @PutMapping("/updateDicType")
    @RequiresPermissions("sys:dic:updateDicType")
    @LogAnnotation(title = "数据字典管理", action = "更新数据字典类型")
    public DataResult updateDicType(@RequestBody @Valid SysDicType sysDicType, HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        sysDicTypeService.update(sysDicType, userId);
        return DataResult.success();
    }

    @ApiOperation(value = "数据字典类型详情")
    @GetMapping("/detailDicType/{id}")
    @RequiresPermissions("sys:dic:detailDicType")
    public DataResult<SysDicType> detailDicType(@PathVariable("id") String id) {
        SysDicType sysDicType = sysDicTypeService.findById(id);
        DataResult<SysDicType> result = DataResult.success();
        result.setData(sysDicType);
        return result;
    }

    @ApiOperation(value = "分页获取数据字典类型")
    @PostMapping("/listDicType")
    @RequiresPermissions("sys:dic:listDicType")
    @LogAnnotation(title = "数据字典管理", action = "查看数据字典类型")
    public DataResult<PageVO<SysDicTypeVO>> listDicType(@RequestBody @Valid SysDicType sysDicType) {
        DataResult<PageVO<SysDicTypeVO>> result = DataResult.success();
        result.setData(sysDicTypeService.pageInfo(sysDicType));
        return result;
    }

    @ApiOperation(value = "获取数据字典类型树型数据")
    @GetMapping("/dicTypeTree")
    @RequiresPermissions("sys:dic:dicTypeTree")
    @LogAnnotation(title = "数据字典管理", action = "查看数据字典类型")
    public DataResult<List<DictionaryTree>> getAllDicTree() {
        DataResult<List<DictionaryTree>> result = DataResult.success();
        result.setData(sysDicTypeService.getAllDicTree());
        return result;
    }
}