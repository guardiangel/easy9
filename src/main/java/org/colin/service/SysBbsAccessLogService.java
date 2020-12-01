package org.colin.service;

import javax.servlet.http.HttpServletRequest;

public interface SysBbsAccessLogService {

    //新增日志
    void addLog(HttpServletRequest request);
}
