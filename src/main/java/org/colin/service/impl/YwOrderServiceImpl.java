package org.colin.service.impl;

import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.mapper.yw.YwGoodsMapper;
import org.colin.mapper.yw.YwOrderDetailMapper;
import org.colin.mapper.yw.YwOrderMasterMapper;
import org.colin.mapper.yw.YwStoreMapper;
import org.colin.model.entity.yw.YwOrderDetail;
import org.colin.model.entity.yw.YwOrderMaster;
import org.colin.model.entity.yw.YwStore;
import org.colin.model.ro.OrderRO;
import org.colin.model.vo.resp.YwOrderDetailVO;
import org.colin.model.vo.resp.YwOrderVO;
import org.colin.service.YwOrderService;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @Description: YwOrderServiceImpl
 * @ClassName: YwOrderServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/8 12:24
 * @Version: 1.1.0
 */

@Service
public class YwOrderServiceImpl implements YwOrderService {

    @Autowired
    private YwOrderMasterMapper ywOrderMasterMapper;

    @Autowired
    private YwOrderDetailMapper ywOrderDetailMapper;

    @Autowired
    private YwStoreMapper ywStoreMapper;

    @Autowired
    private YwGoodsMapper ywGoodsMapper;

    @Override
    public synchronized YwOrderVO addOrder(OrderRO ro) {
        //生成订单号
        String orderNm = Tool.getOrderNum(6);
        YwOrderVO vo = insertOrder(ro, orderNm);
        return vo;
    }

    @Override
    public List<YwOrderDetailVO> getAllOrderByOrderNum(String orderNum) {
        List<YwOrderDetailVO> voList = ywGoodsMapper.selectAllOrderByOrderNum(orderNum);
        return voList;
    }

    @Transactional(rollbackFor = Exception.class)
    public YwOrderVO insertOrder(OrderRO ro, String orderNm) {
        //商品名称:单价:数量#商品名称:单价:数量#商品名称:单价:数量
        String goods = ro.getGoods();//获取购买的商品串信息
        BigDecimal totalPrice = new BigDecimal(0);
        List<YwOrderDetail> detailList = new ArrayList<YwOrderDetail>();
        YwOrderDetail detail = null;
        if (goods.contains("#")) {
            String[] goodsInfo1 = goods.split("#");
            for (int i = 0; i < goodsInfo1.length; i++) {
                String goodsString = goodsInfo1[i];
                String[] goodsInfo2 = goodsString.split(":");
                String goodsName = (String) goodsInfo2[0];
                BigDecimal goodsPrice = new BigDecimal(goodsInfo2[1]);
                int buyCount = Integer.valueOf(goodsInfo2[2]);
                detail = new YwOrderDetail();
                detail.setId(Tool.getPrimaryKey());
                detail.setOrderNumber(orderNm);
                detail.setGoodsName(goodsName);
                detail.setGoodsPrice(goodsPrice);
                detail.setBuyCount(buyCount);
                detailList.add(detail);
                //计算总价
                BigDecimal count = new BigDecimal(buyCount);
                BigDecimal totalPriceTemp = goodsPrice.multiply(count);
                totalPrice = totalPrice.add(totalPriceTemp);
            }
            int result = ywOrderDetailMapper.batchAddDetail(detailList);
            if (result <= 1) {
                throw new ServiceException(BaseResponseCode.DATA_ERROR);
            }
        } else {
            detail = new YwOrderDetail();
            String[] goodsInfo = goods.split(":");
            String goodsName = (String) goodsInfo[0];
            BigDecimal goodsPrice = new BigDecimal(goodsInfo[1]);
            int buyCount = Integer.valueOf(goodsInfo[2]);
            detail.setId(Tool.getPrimaryKey());
            detail.setOrderNumber(orderNm);
            detail.setGoodsName(goodsName);
            detail.setGoodsPrice(goodsPrice);
            detail.setBuyCount(buyCount);
            detailList.add(detail);
            //计算总价
            BigDecimal count = new BigDecimal(buyCount);
            totalPrice = goodsPrice.multiply(count);
            int result = ywOrderDetailMapper.batchAddDetail(detailList);
            if (result != 1) {
                throw new ServiceException(BaseResponseCode.DATA_ERROR);
            }
        }
        YwOrderMaster master = new YwOrderMaster();
        master.setId(Tool.getPrimaryKey());
        master.setOrderNumber(orderNm);
        master.setCustPhone(ro.getPhoneNumber());
        master.setCustBuilding(ro.getBuildingNumber());
        master.setTotalPrice(totalPrice);
        master.setCreateTime(new Date());
        int result1 = ywOrderMasterMapper.insertSelective(master);
        if (result1 != 1) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
        //根据商家ID查询商家信息
        YwStore stroeVo = ywStoreMapper.selectByPrimaryKey(ro.getStoreId());
        YwOrderVO vo = new YwOrderVO();
        vo.setOrderNum(orderNm);
        vo.setWeixinMoney(stroeVo.getReceiveMoney());
        vo.setTotalPrice(totalPrice);
        vo.setStoreLinkUser(stroeVo.getStoreLinkUser());
        vo.setStoreLinkPhone(stroeVo.getStoreLinkPhone());
        return vo;
    }
}
