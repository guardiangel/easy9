package org.colin.mapper.yw;

import org.colin.model.ro.YwFlwRequest;
import org.colin.model.vo.req.CheckRequestVO;
import org.colin.model.vo.req.FlwRequestVO;
import org.colin.model.vo.resp.FlwRespVO;

import java.util.List;

public interface YwFlwRequestMapper {

    int deleteByPrimaryKey(String id);

    int insert(YwFlwRequest record);

    int insertSelective(YwFlwRequest record);

    YwFlwRequest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YwFlwRequest record);

    int updateByPrimaryKey(YwFlwRequest record);

    //分页获取所有的流程申请记录
    List<FlwRespVO> flwPageInfo(FlwRequestVO vo);

    //分页获取当前用户需要审核的所有流程
    List<FlwRespVO> checkPageInfo(CheckRequestVO vo);
}