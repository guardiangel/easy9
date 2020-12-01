package org.colin.service;

import org.colin.model.entity.ExportLoginLogBean;
import org.colin.model.ro.LoginLogPageRO;
import org.colin.model.vo.resp.LoginLogVO;
import org.colin.model.vo.resp.PageVO;

import java.util.List;

public interface SysLoginLogService {

    //分页查询登录日志信息
    PageVO<LoginLogVO> pageInfo(LoginLogPageRO ro);

    //删除日志
    void deleted(List<String> logIds);

    List<ExportLoginLogBean> selectAll();

}