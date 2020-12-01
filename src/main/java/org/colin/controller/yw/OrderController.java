package org.colin.controller.yw;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.colin.model.entity.yw.YwCommunity;
import org.colin.model.entity.yw.YwGoods;
import org.colin.model.entity.yw.YwStore;
import org.colin.model.ro.OrderRO;
import org.colin.model.vo.resp.YwGoodsVO;
import org.colin.model.vo.resp.YwOrderDetailVO;
import org.colin.model.vo.resp.YwOrderVO;
import org.colin.service.YwCommunityService;
import org.colin.service.YwGoodsService;
import org.colin.service.YwOrderService;
import org.colin.service.YwStoreService;
import org.colin.utils.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @Description: 访问此类中的所有方法均不需要权限
 * @ClassName: OrderController
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/5 17:33
 * @Version: 1.1.0
 */
@RestController
@Api(tags = "社区小助手-前台接口")
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private YwCommunityService ywCommunityService;

    @Autowired
    private YwStoreService ywStoreService;

    @Autowired
    private YwGoodsService ywGoodsService;

    @Autowired
    private YwOrderService ywOrderService;

    @GetMapping("/getAllOrderByOrderNum/{orderNum}")
    @ApiOperation(value = "根据订单号查询订单详情")
    public DataResult<List<YwOrderDetailVO>> getAllOrderByOrderNum(@PathVariable("orderNum") String orderNum) {
        DataResult<List<YwOrderDetailVO>> result = DataResult.success();
        List<YwOrderDetailVO> voList = ywOrderService.getAllOrderByOrderNum(orderNum);
        result.setData(voList);
        return result;
    }

    @GetMapping("/getAllGoodsByStoreId/{id}")
    @ApiOperation(value = "根据商家ID查询其下所有商品信息")
    public List<YwGoodsVO> getAllGoodsByStoreId(@PathVariable("id") String storeId) {
        YwGoods vo = new YwGoods();
        vo.setStoreId(storeId);
        List<YwGoodsVO> goodsList = ywGoodsService.selectAll(vo);
        return goodsList;
    }

    @GetMapping("/getAllGoods/{id}")
    @ApiOperation(value = "根据商家ID查询其下所有商品信息")
    public DataResult getAllGoods(@PathVariable("id") String storeId) {
        DataResult<List<YwGoodsVO>> result = DataResult.success();
        List<YwGoodsVO> goodsList = ywGoodsService.selectAllGoodsByStoreId(storeId);
        result.setData(goodsList);
        return result;
    }

    @GetMapping("/getStore/{id}")
    @ApiOperation(value = "根据商家ID查询详情")
    public DataResult getStoreById(@PathVariable("id") String communityId) {
        DataResult<YwStore> result = DataResult.success();
        YwStore object = ywStoreService.getStoreById(communityId);
        result.setData(object);
        return result;
    }

    @GetMapping("/getAllCommunity/{id}")
    @ApiOperation(value = "根据社区ID查询其下所有商家")
    public DataResult getStoreByCommunityId(@PathVariable("id") String communityId) {
        DataResult<List<YwStore>> result = DataResult.success();
        List<YwStore> list = ywStoreService.getStoreByCommunityId(communityId);
        result.setData(list);
        return result;
    }

    @GetMapping("/getAllCommunity")
    @ApiOperation(value = "查询所有社区信息")
    public DataResult getAllCommunity() {
        DataResult<List<YwCommunity>> result = DataResult.success();
        List<YwCommunity> list = ywCommunityService.selectAll();
        result.setData(list);
        return result;
    }

    @PostMapping("/buy")
    @ApiOperation(value = "下单接口")
    public DataResult<YwOrderVO> addOrder(@RequestBody OrderRO ro) {
        log.info("手机号:[]下单了，订单详情:[]", ro.getPhoneNumber(), ro);
        YwOrderVO vo = ywOrderService.addOrder(ro);
        DataResult<YwOrderVO> result = DataResult.success();
        result.setData(vo);
        return result;
    }

}
