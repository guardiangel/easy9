package org.colin.service.impl;

import org.colin.mapper.yw.BbsNoticeMapper;
import org.colin.model.ro.BbsNotice;
import org.colin.model.vo.resp.BbsNoticeVO;
import org.colin.service.BbsNoticeService;
import org.colin.utils.Tool;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * @Description:
 * @ClassName: BbsNoticeServiceImpl
 * @Author: wujiangbo
 * @Date: 2020/6/22 0022 15:49
 * @Version: 1.1.0
 */
@Service
public class BbsNoticeServiceImpl implements BbsNoticeService {

    @Resource
    private BbsNoticeMapper bbsNoticeMapper;

    @Override
    public List<BbsNoticeVO> getAllNotice() {
        return bbsNoticeMapper.getAllNotice();
    }

    @Override
    public BbsNoticeVO getNoticeById(String id) {
        //查看通知详情，那么阅读次数+1
        bbsNoticeMapper.addReadCount(id);
        return bbsNoticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(BbsNotice obj) {
        obj.setId(Tool.getPrimaryKey());
        obj.setUpdateUserId("fcf34b56-a7a2-4719-9236-867495e74c31");
        obj.setUpdateTime(new Date());
        bbsNoticeMapper.insertSelective(obj);
    }
}
