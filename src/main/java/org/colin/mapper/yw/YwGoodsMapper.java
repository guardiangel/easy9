package org.colin.mapper.yw;

import org.apache.ibatis.annotations.Param;
import org.colin.model.entity.yw.YwGoods;
import org.colin.model.vo.resp.YwGoodsVO;
import org.colin.model.vo.resp.YwOrderDetailVO;

import java.util.List;

public interface YwGoodsMapper {

    int deleteByPrimaryKey(String id);

    int insert(YwGoods record);

    int insertSelective(YwGoods record);

    YwGoods selectByPrimaryKey(String id);

    List<YwGoodsVO> selectAllGoodsByStoreId(@Param(value = "storeId") String storeId);

    List<YwGoodsVO> getAllGoodsTypeByStoreId(@Param(value = "storeId") String storeId);

    List<YwGoodsVO> selectAll(YwGoods record);

    List<YwOrderDetailVO> selectAllOrderByOrderNum(@Param(value = "orderNum") String orderNum);

    int updateByPrimaryKeySelective(YwGoods record);

    int updateByPrimaryKey(YwGoods record);
}