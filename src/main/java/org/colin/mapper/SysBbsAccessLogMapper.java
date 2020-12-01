package org.colin.mapper;

import org.colin.model.entity.SysBbsAccessLog;

public interface SysBbsAccessLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysBbsAccessLog record);

    int insertSelective(SysBbsAccessLog record);

    SysBbsAccessLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysBbsAccessLog record);

    int updateByPrimaryKey(SysBbsAccessLog record);
}