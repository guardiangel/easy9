package org.colin.mapper.yw;

import org.colin.model.ro.YwFlwCheck;
import org.colin.model.vo.req.CheckRequestVO;
import org.colin.model.vo.resp.FlwCheckInfoRespVO;
import java.util.List;

public interface YwFlwCheckMapper {

    int deleteByPrimaryKey(String id);

    int insert(YwFlwCheck record);

    int insertSelective(YwFlwCheck record);

    YwFlwCheck selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YwFlwCheck record);

    int updateByPrimaryKey(YwFlwCheck record);

    //根据流程申请的主键ID查询所有审核记录信息
    List<FlwCheckInfoRespVO> getCheckList(String id);
}