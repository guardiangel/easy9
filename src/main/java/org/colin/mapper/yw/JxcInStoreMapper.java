package org.colin.mapper.yw;

import org.colin.model.ro.JxcInStore;
import org.colin.model.vo.req.JxcInStoreReqVo;
import org.colin.model.vo.resp.JxcInStoreRespVO;
import java.util.List;

public interface JxcInStoreMapper {

    int deleteByPrimaryKey(String id);

    int insert(JxcInStore record);

    int insertSelective(JxcInStore record);

    JxcInStoreReqVo selectByPrimaryKey(String id);

    List<JxcInStoreRespVO> selectAll(JxcInStoreReqVo vo);

    int updateByPrimaryKeySelective(JxcInStore record);

    int updateByPrimaryKey(JxcInStore record);
}