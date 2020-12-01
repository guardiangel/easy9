package org.colin.controller.yw;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.model.entity.yw.YwCommunity;
import org.colin.model.entity.yw.YwGoods;
import org.colin.model.entity.yw.YwStore;
import org.colin.model.vo.req.YwCommunityPageVO;
import org.colin.model.vo.req.YwStorePageVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.YwCommStoreTreeVO;
import org.colin.model.vo.resp.YwGoodsVO;
import org.colin.model.vo.resp.YwStoreVO;
import org.colin.service.YwCommunityService;
import org.colin.service.YwGoodsService;
import org.colin.service.YwStoreService;
import org.colin.utils.DataResult;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "社区小助手-后台接口")
@RequestMapping("/buy")
@Slf4j
public class BuyController {

    @Autowired
    private YwCommunityService ywCommunityService;

    @Autowired
    private YwStoreService ywStoreService;

    @Autowired
    private YwGoodsService ywGoodsService;

    //-----------------------------------------商品管理---开始--------------------------------------
    @PostMapping("/goods")
    @ApiOperation(value = "新增商家接口")
    @RequiresPermissions("buy:goods:add")
    @LogAnnotation(title = "社区小助手", action = "新增商品")
    public DataResult addObjectGoods(@RequestBody @Valid YwGoods vo) {
        ywGoodsService.addObject(vo);
        return DataResult.success();
    }

    @DeleteMapping("/goods/{id}")
    @ApiOperation(value = "删除商品接口")
    @LogAnnotation(title = "社区小助手", action = "删除商品")
    @RequiresPermissions("buy:goods:deleted")
    public DataResult deletedObjectGoods(@PathVariable("id") String id) {
        ywGoodsService.deletedObject(id);
        return DataResult.success();
    }

    @GetMapping("/goodsAll")
    @ApiOperation(value = "查询所有商品(无需权限)")
    public List<YwGoodsVO> goodsAll() {
        YwGoods vo = new YwGoods();
        List<YwGoodsVO> objList = ywGoodsService.selectAll(vo);
        return objList;
    }

    @PostMapping("/goodsAll/{storeId}")
    @ApiOperation(value = "查询所有商品")
    @RequiresPermissions("buy:goods:list")
    public DataResult<List<YwGoodsVO>> pageInfoGoods(@PathVariable("storeId") String storeId) {
        List<YwGoodsVO> objList = ywGoodsService.selectAllGoodsByStoreId(storeId);
        DataResult<List<YwGoodsVO>> result = DataResult.success();
        result.setData(objList);
        return result;
    }

    @PutMapping("/goods")
    @ApiOperation(value = "更新商品信息接口")
    @LogAnnotation(title = "社区小助手", action = "更新商品信息")
    @RequiresPermissions("buy:goods:update")
    public DataResult updateObjectGoods(@RequestBody @Valid YwGoods vo) {
        ywGoodsService.updateObject(vo);
        return DataResult.success();
    }

    @PostMapping("/getAllGoodsTypeByStoreId/{storeId}")
    @ApiOperation(value = "根据商家ID查询其下所有商品种类")
    public DataResult<List<YwGoodsVO>> getAllGoodsTypeByStoreId(@PathVariable("storeId") String storeId) {
        List<YwGoodsVO> objList = ywGoodsService.getAllGoodsTypeByStoreId(storeId);
        DataResult<List<YwGoodsVO>> result = DataResult.success();
        result.setData(objList);
        return result;
    }
    //-----------------------------------------商品管理---结束--------------------------------------

    //-----------------------------------------商家管理---开始--------------------------------------
    @PostMapping("/store")
    @ApiOperation(value = "新增商家接口")
    @RequiresPermissions("buy:store:add")
    @LogAnnotation(title = "社区小助手", action = "新增商家")
    public DataResult addObjectStore(@RequestBody @Valid YwStore vo) {
        ywStoreService.addObject(vo);
        return DataResult.success();
    }

    @DeleteMapping("/store/{id}")
    @ApiOperation(value = "删除商家接口")
    @LogAnnotation(title = "社区小助手", action = "删除商家")
    @RequiresPermissions("buy:store:deleted")
    public DataResult deletedObjectStore(@PathVariable("id") String id, HttpServletRequest request) {
        ywStoreService.deletedObject(id);
        return DataResult.success();
    }

    @PostMapping("/storeAll")
    @ApiOperation(value = "分页查询所有商家")
    @RequiresPermissions("buy:store:list")
    public DataResult<PageVO<YwStoreVO>> pageInfoStore(@RequestBody YwStorePageVO vo) {
        PageVO<YwStoreVO> pageVO = ywStoreService.pageInfo(vo);
        DataResult<PageVO<YwStoreVO>> result = DataResult.success();
        result.setData(pageVO);
        return result;
    }

    @PutMapping("/store")
    @ApiOperation(value = "更新商家信息接口")
    @LogAnnotation(title = "社区小助手", action = "更新商家信息")
    @RequiresPermissions("buy:store:update")
    public DataResult updateObjectStore(@RequestBody @Valid YwStore vo) {
        ywStoreService.updateObject(vo);
        return DataResult.success();
    }

    @PostMapping("/uploadWeixinMoney")
    @ApiOperation(value = "上传收款码")
    @LogAnnotation(title = "社区小助手", action = "上传商家微信收款码")
    public DataResult uploadWeixinMoney(@RequestParam("file") MultipartFile file, HttpServletRequest request, String storeId) {
        YwStore vo = new YwStore();
        vo.setId(storeId);
        vo.setReceiveMoney(Tool.MutipartFileToBase64(file));
        ywStoreService.updateObject(vo);
        return DataResult.success();
    }
    //-----------------------------------------商家管理---结束--------------------------------------

    //-----------------------------------------社区管理---开始--------------------------------------
    @PostMapping("/community")
    @ApiOperation(value = "新增社区接口")
    @RequiresPermissions("buy:community:add")
    @LogAnnotation(title = "社区小助手", action = "新增社区")
    public DataResult addObject(@RequestBody @Valid YwCommunity vo) {
        ywCommunityService.addObject(vo);
        return DataResult.success();
    }

    @DeleteMapping("/community/{id}")
    @ApiOperation(value = "删除社区接口")
    @LogAnnotation(title = "社区小助手", action = "删除社区")
    @RequiresPermissions("buy:community:deleted")
    public DataResult deletedObject(@PathVariable("id") String id) {
        ywCommunityService.deletedObject(id);
        return DataResult.success();
    }

    @PostMapping("/communityAll")
    @ApiOperation(value = "分页查询所有小区")
    @RequiresPermissions("buy:community:list")
    public DataResult<PageVO<YwCommunity>> pageInfo(@RequestBody YwCommunityPageVO vo) {
        PageVO<YwCommunity> pageVO = ywCommunityService.pageInfo(vo);
        DataResult<PageVO<YwCommunity>> result = DataResult.success();
        result.setData(pageVO);
        return result;
    }

    @PutMapping("/community")
    @ApiOperation(value = "更新社区信息接口")
    @LogAnnotation(title = "社区小助手", action = "更新社区信息")
    @RequiresPermissions("buy:community:update")
    public DataResult updateObject(@RequestBody @Valid YwCommunity vo) {
        ywCommunityService.updateObject(vo);
        return DataResult.success();
    }

    @GetMapping("/commStoreTree")
    @ApiOperation(value = "查询所有社区-商家树型结构数据(无需权限)")
    public List<YwCommStoreTreeVO> commStoreTree() {
        List<YwCommStoreTreeVO> objList = ywCommunityService.commStoreTree();
        return objList;
    }
    //-----------------------------------------社区管理---结束--------------------------------------

}
