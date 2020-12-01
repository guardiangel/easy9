package org.colin.service.impl;

import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.mapper.yw.YwGoodsMapper;
import org.colin.model.entity.yw.YwGoods;
import org.colin.model.vo.resp.YwGoodsVO;
import org.colin.service.YwGoodsService;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 * @Description: YwGoodsServiceImpl
 * @ClassName: YwGoodsServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/7 11:29
 * @Version: 1.1.0
 */
@Service
public class YwGoodsServiceImpl implements YwGoodsService {

    @Autowired
    private YwGoodsMapper ywGoodsMapper;

    @Override
    public void addObject(YwGoods obj) {
        if (obj.getType() == 1) {
            //商品类型
            YwGoods ro = new YwGoods();
            ro.setId(Tool.getPrimaryKey());
            ro.setCreateTime(new Date());
            ro.setGoodsName(obj.getGoodsName());
            ro.setOrderNum(obj.getOrderNum());
            ro.setPid("0");
            ro.setStoreId(obj.getStoreId());
            ro.setType(obj.getType());
            int result = ywGoodsMapper.insertSelective(ro);
            if (result != 1) {
                throw new ServiceException(BaseResponseCode.DATA_ERROR);
            }
        } else if (obj.getType() == 2) {
            //商品
            obj.setId(Tool.getPrimaryKey());
            obj.setCreateTime(new Date());
            int result = ywGoodsMapper.insertSelective(obj);
            if (result != 1) {
                throw new ServiceException(BaseResponseCode.DATA_ERROR);
            }
        }
    }

    @Override
    public void deletedObject(String id) {
        int result = ywGoodsMapper.deleteByPrimaryKey(id);
        if (result != 1) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
    }

    @Override
    public void updateObject(YwGoods obj) {
        obj.setCreateTime(new Date());
        int result = ywGoodsMapper.updateByPrimaryKeySelective(obj);
        if (result != 1) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
    }

    @Override
    public List<YwGoodsVO> selectAll(YwGoods vo) {
        List<YwGoodsVO> goodsList = ywGoodsMapper.selectAll(vo);
        return goodsList;
    }

    @Override
    public List<YwGoodsVO> selectAllGoodsByStoreId(String storeId) {
        List<YwGoodsVO> list = ywGoodsMapper.selectAllGoodsByStoreId(storeId);
        return list;
    }

    @Override
    public List<YwGoodsVO> getAllGoodsTypeByStoreId(String storeId) {
        List<YwGoodsVO> list = ywGoodsMapper.getAllGoodsTypeByStoreId(storeId);
        return list;
    }
}
