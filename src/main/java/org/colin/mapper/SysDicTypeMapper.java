package org.colin.mapper;

import org.colin.model.entity.SysDicType;
import org.colin.model.vo.resp.SysDicTypeVO;

import java.util.List;

public interface SysDicTypeMapper {

    int deleteByPrimaryKey(String id);

    int deleteByTypeCode(String typeCode);

    int insert(SysDicType record);

    int insertSelective(SysDicType record);

    List<SysDicTypeVO> selectAllDicType();

    List<SysDicTypeVO> selectAll(SysDicType sysDicType);

    SysDicType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDicType record);

    int updateByPrimaryKey(SysDicType record);
}