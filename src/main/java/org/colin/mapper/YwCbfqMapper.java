package org.colin.mapper;

import org.colin.model.entity.YwCbfq;
import java.util.List;

public interface YwCbfqMapper {

    int deleteByPrimaryKey(String id);

    int insert(YwCbfq record);

    int insertSelective(YwCbfq record);

    YwCbfq selectByPrimaryKey(String id);

    List<YwCbfq> selectAll(YwCbfq obj);

    int updateByPrimaryKeySelective(YwCbfq record);

    int updateByPrimaryKey(YwCbfq record);
}