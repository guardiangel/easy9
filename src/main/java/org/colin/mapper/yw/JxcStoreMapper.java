package org.colin.mapper.yw;

import org.apache.ibatis.annotations.Param;
import org.colin.model.ro.JxcStore;
import org.colin.model.vo.req.JxcStoreReqVO;
import org.colin.model.vo.resp.JxcStoreRespVO;

import java.util.List;

public interface JxcStoreMapper {

    int deleteByPrimaryKey(String id);

    int insert(JxcStore record);

    int insertSelective(JxcStore record);

    JxcStoreRespVO selectByPrimaryKey(String id);

    JxcStore selectByGoodsCodeWhCode(@Param(value = "goodsCode") String goodsCode, @Param(value = "whCode") String whCode);

    List<JxcStoreRespVO> selectAll(JxcStoreReqVO vo);

    int updateByPrimaryKeySelective(JxcStore record);

    int updateByPrimaryKey(JxcStore record);
}