package org.colin.mapper.yw;

import org.colin.model.entity.yw.YwOrderDetail;

import java.util.List;

public interface YwOrderDetailMapper {

    int deleteByPrimaryKey(String id);

    int insert(YwOrderDetail record);

    int batchAddDetail(List<YwOrderDetail> list);

    int insertSelective(YwOrderDetail record);

    YwOrderDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YwOrderDetail record);

    int updateByPrimaryKey(YwOrderDetail record);
}