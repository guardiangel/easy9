package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import org.colin.mapper.SysLogMapper;
import org.colin.model.entity.SysLog;
import org.colin.model.vo.req.SysLogPageReqVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.service.LogService;
import org.colin.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Description: LogServiceImpl
 * @ClassName: LogServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 21:15
 * @Version: 1.1.0
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public PageVO<SysLog> pageInfo(SysLogPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysLog> sysLogs = sysLogMapper.selectAll(vo);
        return PageUtils.getPageVO(sysLogs);
    }

    @Override
    public void deleted(List<String> logIds) {
        sysLogMapper.batchDeletedLog(logIds);
    }
}
