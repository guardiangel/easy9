package org.colin.controller.yw;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.constants.Constant;
import org.colin.model.entity.DeptUserTree;
import org.colin.model.entity.tree.ATree;
import org.colin.model.ro.*;
import org.colin.model.vo.req.*;
import org.colin.model.vo.resp.*;
import org.colin.service.JxcService;
import org.colin.utils.DataResult;
import org.colin.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/jxc")
@Api(tags = "进销存管理")
public class JxcController {

    @Autowired
    private JxcService jxcService;

    //**************仓库管理——开始***************************************************************************************
    @PostMapping("/wh/insert")
    @ApiOperation(value = "新增仓库信息")
    @LogAnnotation(title = "仓库管理", action = "新增仓库信息")
    @RequiresPermissions("jxc:wh:insert")
    public DataResult whInsert(@RequestBody JxcWarehouse obj, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        obj.setCreateUser(user_id);
        obj.setUpdateUser(user_id);
        jxcService.addWareHouse(obj);
        return DataResult.success();
    }

    @DeleteMapping("/wh/delete/{id}")
    @ApiOperation(value = "删除仓库信息")
    @LogAnnotation(title = "仓库管理", action = "删除仓库信息")
    @RequiresPermissions("jxc:wh:delete")
    public DataResult whDelete(@PathVariable("id") String id) {
        jxcService.deleteWareHouse(id);
        return DataResult.success();
    }

    @PostMapping("/wh/pageList")
    @ApiOperation(value = "分页查询所有仓库信息")
    public DataResult<PageVO<WareHouseRespVO>> pageList(@RequestBody CommonVo vo) {
        DataResult<PageVO<WareHouseRespVO>> result = DataResult.success();
        result.setData(jxcService.getAllWareHousePageInfo(vo));
        return result;
    }

    @GetMapping("/wh/queryById/{id}")
    @ApiOperation(value = "查看仓库详情信息")
    @LogAnnotation(title = "仓库管理", action = "查看仓库详情信息")
    @RequiresPermissions("jxc:wh:queryById")
    public DataResult<WareHouseRespVO> queryById(@PathVariable("id") String id) {
        DataResult<WareHouseRespVO> result = DataResult.success();
        result.setData(jxcService.getWareHouseById(id));
        return result;
    }

    @PutMapping("/wh/update")
    @ApiOperation(value = "更新仓库信息")
    @LogAnnotation(title = "仓库管理", action = "更新仓库信息")
    @RequiresPermissions("jxc:wh:update")
    public DataResult whUpdate(@RequestBody JxcWarehouse obj, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        obj.setUpdateUser(user_id);
        jxcService.updateWareHouse(obj);
        return DataResult.success();
    }
    //**************仓库管理——结束***************************************************************************************


    //**************商品管理——开始***************************************************************************************
    @PostMapping("/goods/insert")
    @ApiOperation(value = "新增商品信息")
    @LogAnnotation(title = "商品管理", action = "新增商品信息")
    @RequiresPermissions("jxc:goods:insert")
    public DataResult goodsInsert(@RequestBody JxcGoods obj, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        obj.setCreateUser(user_id);
        obj.setUpdateUser(user_id);
        jxcService.addGoods(obj);
        return DataResult.success();
    }

    @DeleteMapping("/goods/delete/{id}")
    @ApiOperation(value = "删除商品信息")
    @LogAnnotation(title = "商品管理", action = "删除商品信息")
    @RequiresPermissions("jxc:goods:delete")
    public DataResult goodsDelete(@PathVariable("id") String id) {
        jxcService.deleteGoods(id);
        return DataResult.success();
    }

    @PostMapping("/goods/pageList")
    @ApiOperation(value = "分页查询所有商品信息")
    public DataResult<PageVO<JxcGoodsRespVO>> goodsPageList(@RequestBody JxcGoodsReqVO vo) {
        DataResult<PageVO<JxcGoodsRespVO>> result = DataResult.success();
        result.setData(jxcService.getAllGoodsPageInfo(vo));
        return result;
    }

    @GetMapping("/goods/queryById/{id}")
    @ApiOperation(value = "查看商品详情信息")
    @LogAnnotation(title = "商品管理", action = "查看商品详情信息")
    @RequiresPermissions("jxc:goods:queryById")
    public DataResult<JxcGoodsRespVO> queryGoodsById(@PathVariable("id") String id) {
        DataResult<JxcGoodsRespVO> result = DataResult.success();
        result.setData(jxcService.getGoodsById(id));
        return result;
    }

    @PutMapping("/goods/update")
    @ApiOperation(value = "更新商品信息")
    @LogAnnotation(title = "商品管理", action = "更新商品信息")
    @RequiresPermissions("jxc:goods:update")
    public DataResult goodsUpdate(@RequestBody JxcGoods obj, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        obj.setUpdateUser(user_id);
        jxcService.updateGoods(obj);
        return DataResult.success();
    }
    //**************商品管理——结束***************************************************************************************




    //**************商品入库管理——开始***************************************************************************************
    @PostMapping("/goodsIn/insert")
    @ApiOperation(value = "新增商品入库信息")
    @LogAnnotation(title = "商品入库管理", action = "新增商品入库信息")
    @RequiresPermissions("jxc:goodsIn:insert")
    public DataResult goodsInInsert(@RequestBody JxcInStore obj, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        obj.setCreateUser(user_id);
        jxcService.addInGoods(obj);
        return DataResult.success();
    }

    @DeleteMapping("/goodsIn/delete/{id}")
    @ApiOperation(value = "删除商品入库信息")
    @LogAnnotation(title = "商品入库管理", action = "删除商品入库信息")
    @RequiresPermissions("jxc:goodsIn:delete")
    public DataResult goodsInDelete(@PathVariable("id") String id) {
        jxcService.deleteInGoods(id);
        return DataResult.success();
    }

    @PostMapping("/goodsIn/pageList")
    @ApiOperation(value = "分页查询所有商品入库信息")
    public DataResult<PageVO<JxcInStoreRespVO>> goodsInPageList(@RequestBody JxcInStoreReqVo vo) {
        DataResult<PageVO<JxcInStoreRespVO>> result = DataResult.success();
        result.setData(jxcService.getAllInGoodsPageInfo(vo));
        return result;
    }

    @GetMapping("/goodsIn/queryById/{id}")
    @ApiOperation(value = "查看商品入库详情信息")
    @LogAnnotation(title = "商品入库管理", action = "查看商品入库详情信息")
    @RequiresPermissions("jxc:inGoods:queryById")
    public DataResult<JxcInStoreReqVo> queryGoodsInById(@PathVariable("id") String id) {
        DataResult<JxcInStoreReqVo> result = DataResult.success();
        result.setData(jxcService.getInGoodsById(id));
        return result;
    }
    //**************商品入库管理——结束***************************************************************************************




    //**************商品出库管理——开始***************************************************************************************
    @PostMapping("/goodsOut/insert")
    @ApiOperation(value = "新增商品出库信息")
    @LogAnnotation(title = "商品出库管理", action = "新增商品出库信息")
    @RequiresPermissions("jxc:goodsOut:insert")
    public DataResult goodsOutInsert(@RequestBody JxcOutStore obj, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        obj.setCreateUser(user_id);
        jxcService.addOutGoods(obj);
        return DataResult.success();
    }

    @DeleteMapping("/goodsOut/delete/{id}")
    @ApiOperation(value = "删除商品出库信息")
    @LogAnnotation(title = "商品出库管理", action = "删除商品出库信息")
    @RequiresPermissions("jxc:goodsOut:delete")
    public DataResult goodsOutDelete(@PathVariable("id") String id) {
        jxcService.deleteOutGoods(id);
        return DataResult.success();
    }

    @PostMapping("/goodsOut/pageList")
    @ApiOperation(value = "分页查询所有商品出库信息")
    public DataResult<PageVO<JxcOutStoreRespVO>> goodsOutPageList(@RequestBody JxcOutStoreReqVO vo) {
        DataResult<PageVO<JxcOutStoreRespVO>> result = DataResult.success();
        result.setData(jxcService.getAllOutGoodsPageInfo(vo));
        return result;
    }

    @GetMapping("/goodsOut/queryById/{id}")
    @ApiOperation(value = "查看商品出库详情信息")
    @LogAnnotation(title = "商品出库管理", action = "查看商品出库详情信息")
    @RequiresPermissions("jxc:goodsOut:queryById")
    public DataResult<JxcOutStoreRespVO> queryGoodsOutById(@PathVariable("id") String id) {
        DataResult<JxcOutStoreRespVO> result = DataResult.success();
        result.setData(jxcService.getOutGoodsById(id));
        return result;
    }
    //**************商品出库管理——结束***************************************************************************************







    //**************库存管理——开始***************************************************************************************

    @PostMapping("/store/pageList")
    @ApiOperation(value = "分页查询所有库存信息")
    public DataResult<PageVO<JxcStoreRespVO>> storePageList(@RequestBody JxcStoreReqVO vo) {
        DataResult<PageVO<JxcStoreRespVO>> result = DataResult.success();
        result.setData(jxcService.getAllStorePageInfo(vo));
        return result;
    }

    @PutMapping("/store/update")
    @ApiOperation(value = "更新库存预警信息")
    @LogAnnotation(title = "库存管理", action = "更新库存预警值")
    @RequiresPermissions("jxc:store:update")
    public DataResult storeUpdate(@RequestBody JxcStore obj, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        obj.setUpdateUser(user_id);
        jxcService.updateStore(obj);
        return DataResult.success();
    }
    //**************库存管理——结束***************************************************************************************





    //**************公用方法——开始***************************************************************************************
    @GetMapping("/getAllGoodsTreeSelect")
    @ApiOperation(value = "获取下拉列表中所有商品信息")
    public DataResult<List<ATree>> getAllGoodsTreeSelect() {
        DataResult<List<ATree>> result = DataResult.success();
        result.setData(jxcService.getAllGoodsTreeSelect());
        return result;
    }

    @GetMapping("/getAllWareHouseSelect")
    @ApiOperation(value = "获取下拉框列表中所有仓库信息")
    public DataResult<List<WareHouseRespVO>> getAllWareHouseSelect() {
        DataResult<List<WareHouseRespVO>> result = DataResult.success();
        result.setData(jxcService.getAllWareHouseSelect());
        return result;
    }
    //**************公用方法——结束***************************************************************************************
}
