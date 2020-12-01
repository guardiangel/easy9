package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import org.colin.mapper.SysLoginLogMapper;
import org.colin.model.entity.ExportLoginLogBean;
import org.colin.model.ro.LoginLogPageRO;
import org.colin.model.vo.resp.LoginLogVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.service.SysLoginLogService;
import org.colin.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public PageVO<LoginLogVO> pageInfo(LoginLogPageRO ro) {
        PageHelper.startPage(ro.getPageNum(), ro.getPageSize());
        List<LoginLogVO> loginLogVO = sysLoginLogMapper.selectAll(ro);
        return PageUtils.getPageVO(loginLogVO);
    }

    @Override
    public void deleted(List<String> logIds) {
        sysLoginLogMapper.batchDeletedLog(logIds);
    }

    @Override
    public List<ExportLoginLogBean> selectAll() {
        return sysLoginLogMapper.selectAllLog();
    }

}