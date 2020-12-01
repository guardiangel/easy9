package org.colin.service.impl;

import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.mapper.SysNoticeMapper;
import org.colin.model.ro.SysNotice;
import org.colin.model.vo.resp.SysNoticeRespVO;
import org.colin.service.SysNoticeService;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysNoticeServiceImpl implements SysNoticeService {

    @Autowired
    private SysNoticeMapper sysNoticeMapper;

    @Override
    public void add(SysNotice obj) {
        SysNoticeRespVO notice = sysNoticeMapper.getNoticeByType(obj.getType());
        if(notice != null){
            throw new ServiceException(BaseResponseCode.SYS_NOTICE_TYPE_EXIST);
        }
        obj.setId(Tool.getPrimaryKey());
        obj.setUpdateTime(new Date());
        sysNoticeMapper.insertSelective(obj);
    }

    @Override
    public void deleteById(String id) {
        sysNoticeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysNoticeRespVO getNoticeByType(String type) {
        return sysNoticeMapper.getNoticeByType(type);
    }

    @Override
    public SysNoticeRespVO getNoticeById(String id) {
        return sysNoticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysNoticeRespVO> selectAll() {
        return sysNoticeMapper.selectAll();
    }

    @Override
    public void updateObj(SysNotice obj) {
        obj.setUpdateTime(new Date());
        obj.setType("");//不允许修改type
        sysNoticeMapper.updateByPrimaryKeySelective(obj);
    }
}
