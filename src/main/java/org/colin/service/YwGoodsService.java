package org.colin.service;

import org.colin.model.entity.yw.YwGoods;
import org.colin.model.vo.resp.YwGoodsVO;

import java.util.List;

public interface YwGoodsService {

    void addObject(YwGoods obj);

    void deletedObject(String id);

    void updateObject(YwGoods obj);

    List<YwGoodsVO> selectAll(YwGoods vo);

    List<YwGoodsVO> selectAllGoodsByStoreId(String storeId);

    List<YwGoodsVO> getAllGoodsTypeByStoreId(String storeId);
}
