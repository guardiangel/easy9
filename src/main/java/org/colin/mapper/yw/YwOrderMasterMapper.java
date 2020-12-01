package org.colin.mapper.yw;

import org.colin.model.entity.yw.YwOrderMaster;

public interface YwOrderMasterMapper {

    int deleteByPrimaryKey(String id);

    int insert(YwOrderMaster record);

    int insertSelective(YwOrderMaster record);

    YwOrderMaster selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YwOrderMaster record);

    int updateByPrimaryKey(YwOrderMaster record);
}