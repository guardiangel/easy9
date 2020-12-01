package org.colin.mapper.yw;

import org.apache.ibatis.annotations.Param;
import org.colin.model.ro.JxcGoods;
import org.colin.model.vo.req.JxcGoodsReqVO;
import org.colin.model.vo.resp.JxcGoodsRespVO;
import java.util.List;

public interface JxcGoodsMapper {

    int deleteByPrimaryKey(String id);

    int insert(JxcGoods record);

    int insertSelective(JxcGoods record);

    JxcGoodsRespVO selectByPrimaryKey(String id);

    //根据商品名称和商品型号查询商品信息
    JxcGoodsRespVO selectByNameAndModel(@Param(value = "goodsName") String goodsName, @Param(value = "goodsModel") String goodsModel);

    int updateByPrimaryKeySelective(JxcGoods record);

    int updateByPrimaryKey(JxcGoods record);

    List<JxcGoodsRespVO> getAllGoods(JxcGoodsReqVO vo);

    List<JxcGoodsRespVO> getAllGoodsByGoodsType(String goodsType);
}