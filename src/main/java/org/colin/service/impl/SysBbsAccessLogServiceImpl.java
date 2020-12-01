package org.colin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.colin.mapper.SysBbsAccessLogMapper;
import org.colin.model.entity.SysBbsAccessLog;
import org.colin.service.SysBbsAccessLogService;
import org.colin.utils.IPAddressTool;
import org.colin.utils.IPUtils;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author : wujiangbo(QQ:1134135987)
 * @version : 1.1.0.1
 * @description : TODO
 * @date : 2020-09-16 15:16:10
 */
@Service
@Slf4j
public class SysBbsAccessLogServiceImpl implements SysBbsAccessLogService {

    @Resource
    private SysBbsAccessLogMapper sysBbsAccessLogMapper;

    @Resource
    private IPAddressTool ipAddressTool;

    @Value("${system.getIPAddress}")
    private String getIPAddressFlag;//是否调用百度API接口获取IP归属地（0：不调用；1：调用）

    @Override
    @Async
    public void addLog(HttpServletRequest request) {
        synchronized (request) {
            SysBbsAccessLog accessLog = new SysBbsAccessLog();
            String ip = IPUtils.getIpAddr(request);
            accessLog.setId(Tool.getPrimaryKey());
            accessLog.setAccessTime(new Date());
            accessLog.setAccessIp(ip);
            accessLog.setAccessBrowser(Tool.getBrowserInfo(request));
            accessLog.setAccessWay(Integer.valueOf(IPUtils.getAccessType(request)));
            String ipAddress = "";
            if("1".equalsIgnoreCase(getIPAddressFlag)){
                ipAddress = ipAddressTool.getAddressById(ip);
            }
            accessLog.setAccessAddress(ipAddress);
            accessLog.setRemk(IPUtils.getUserAgent(request));//获取浏览器的User-Agent
            sysBbsAccessLogMapper.insertSelective(accessLog);
            log.info("有人访问[北方社区]：IP={},访问者归属地={},访问时间={}", ip, ipAddress, Tool.getCurrentTimeString());
        }
    }
}
