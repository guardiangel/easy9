package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import org.colin.mapper.yw.YwFlwCheckMapper;
import org.colin.mapper.yw.YwFlwRequestMapper;
import org.colin.model.ro.YwFlwCheck;
import org.colin.model.ro.YwFlwRequest;
import org.colin.model.vo.req.CheckRequestVO;
import org.colin.model.vo.req.FlwRequestVO;
import org.colin.model.vo.resp.FlwCheckInfoRespVO;
import org.colin.model.vo.resp.FlwRespVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.service.FlwRequestService;
import org.colin.utils.PageUtils;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class FlwRequestServiceImpl implements FlwRequestService {

    @Autowired
    private YwFlwRequestMapper ywFlwRequestMapper;

    @Autowired
    private YwFlwCheckMapper ywFlwCheckMapper;

    @Override
    public void addFlwRequest(YwFlwRequest obj) {
        //新增流程申请信息
        obj.setId(Tool.getPrimaryKey());
        String flwType = obj.getFlwType();//流程类型
        obj.setFlwCode(flwType + "_" + Tool.getFlwCode(10));
        obj.setCreateTime(new Date());
        String checkCode = flwType + "_" + Tool.getCheckCode(12);//审核编码
        obj.setCheckCode(checkCode);
        obj.setCurrentCheckUserId(obj.getCurrentCheckUserId());//当前审核人
        obj.setCheckLastResult("1");//最终审核结果(0:审核不通过;1:审核中;2:审核通过;3:申请作废;)
        ywFlwRequestMapper.insertSelective(obj);
    }

    @Override
    public PageVO<FlwRespVO> flwPageInfo(FlwRequestVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<FlwRespVO> objList = ywFlwRequestMapper.flwPageInfo(vo);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public PageVO<FlwRespVO> checkPageInfo(CheckRequestVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<FlwRespVO> objList = ywFlwRequestMapper.checkPageInfo(vo);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public void checkRequest(CheckRequestVO vo, String currentUserId) {
        //新增审核记录
        YwFlwCheck check = new YwFlwCheck();
        check.setId(Tool.getPrimaryKey());
        check.setCreateTime(new Date());
        check.setCheckOpinion(vo.getCheckOpinion());
        check.setCheckResult(vo.getCheckResult());
        check.setUserId(currentUserId);//审核人ID
        check.setCheckCode(vo.getCheckCode());
        check.setCheckTime(new Date());
        ywFlwCheckMapper.insertSelective(check);
        //更新流程申请信息
        YwFlwRequest req = new YwFlwRequest();
        req.setId(vo.getId());
        /*
        说明：
            1、当结果为审核通过，且没有下一审核人时，说明审核终止结束；有下一审核人，则说明审核继续扭转；
            2、当结果为审核不通过时，审核流程直接结束；
            审核结果(0:审核不通过;1:审核中;2:审核通过;)
        */
        if("2".equals(vo.getCheckResult())){
            if(Tool.isBlank(vo.getCheckUserId())){
                req.setCurrentCheckUserId(currentUserId);
                req.setCheckLastResult("2");
            }else{
                req.setCurrentCheckUserId(vo.getCheckUserId());
                req.setCheckLastResult("1");
            }
        }else if("0".equals(vo.getCheckResult())){
                req.setCurrentCheckUserId(currentUserId);
                req.setCheckLastResult("0");
        }
        ywFlwRequestMapper.updateByPrimaryKeySelective(req);
    }

    @Override
    public List<FlwCheckInfoRespVO> getCheckList(String id) {
        return ywFlwCheckMapper.getCheckList(id);
    }
}
