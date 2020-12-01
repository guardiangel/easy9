package org.colin.service;

import org.colin.model.ro.YwFlwRequest;
import org.colin.model.vo.req.CheckRequestVO;
import org.colin.model.vo.req.FlwRequestVO;
import org.colin.model.vo.resp.FlwCheckInfoRespVO;
import org.colin.model.vo.resp.FlwRespVO;
import org.colin.model.vo.resp.PageVO;

import java.util.List;

public interface FlwRequestService {

    //新增流程申请
    void addFlwRequest(YwFlwRequest obj);

    //分页获取所有的流程申请记录
    PageVO<FlwRespVO> flwPageInfo(FlwRequestVO vo);

    //分页获取当前用户需要审核的所有流程
    PageVO<FlwRespVO> checkPageInfo(CheckRequestVO vo);

    //对某个申请进行审核
    void checkRequest(CheckRequestVO vo, String currentUserId);

    //根据流程申请的主键ID查询所有审核记录信息
    List<FlwCheckInfoRespVO> getCheckList(String id);

}
