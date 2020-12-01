package org.colin.mapper.yw;

import org.colin.model.ro.JxcOutStore;
import org.colin.model.vo.req.JxcOutStoreReqVO;
import org.colin.model.vo.resp.JxcOutStoreRespVO;

import java.util.List;

public interface JxcOutStoreMapper {

    int deleteByPrimaryKey(String id);

    int insert(JxcOutStore record);

    int insertSelective(JxcOutStore record);

    JxcOutStoreRespVO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JxcOutStore record);

    int updateByPrimaryKey(JxcOutStore record);

    List<JxcOutStoreRespVO> getAllOutStore(JxcOutStoreReqVO vo);
}