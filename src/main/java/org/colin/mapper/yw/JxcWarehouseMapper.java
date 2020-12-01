package org.colin.mapper.yw;

import org.colin.model.ro.JxcWarehouse;
import org.colin.model.vo.resp.WareHouseRespVO;
import java.util.List;

public interface JxcWarehouseMapper {

    int deleteByPrimaryKey(String id);

    int insert(JxcWarehouse record);

    int insertSelective(JxcWarehouse record);

    WareHouseRespVO selectByPrimaryKey(String id);

    JxcWarehouse selectByWhName(String whName);

    int updateByPrimaryKeySelective(JxcWarehouse record);

    int updateByPrimaryKey(JxcWarehouse record);

    List<WareHouseRespVO> getAllWareHouse(JxcWarehouse obj);
}