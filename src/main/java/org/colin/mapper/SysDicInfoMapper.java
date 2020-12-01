package org.colin.mapper;

import org.colin.model.ro.SysDicInfo;
import org.colin.model.vo.resp.DicInfo;
import org.colin.model.vo.resp.DicSelectInfoVO;
import org.colin.model.vo.resp.SysDicInfoVO;

import java.util.List;

public interface SysDicInfoMapper {

    int deleteByPrimaryKey(String id);

    int deleteByTypeCode(String typeCode);

    int insert(SysDicInfo record);

    int insertSelective(SysDicInfo record);

    SysDicInfo selectByPrimaryKey(String id);

    List<SysDicInfoVO> selectAll(String typeCode);

    List<SysDicInfoVO> selectAllOK(String typeCode);

    //根据typeCode查询所有数据字典信息，select下拉列表数据
    List<DicInfo> selectDicByTypeCode(String typeCode);

    int updateByPrimaryKeySelective(SysDicInfo record);

    int updateByPrimaryKey(SysDicInfo record);
}