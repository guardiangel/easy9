package org.colin.mapper;

import org.colin.model.entity.ExportLoginLogBean;
import org.colin.model.entity.SysLoginLog;
import org.colin.model.ro.LoginLogPageRO;
import org.colin.model.vo.resp.LoginLogVO;

import java.util.List;

public interface SysLoginLogMapper {

    //新增登录日志
    int insertSelective(SysLoginLog record);

    //查询所有登录日志
    List<LoginLogVO> selectAll(LoginLogPageRO ro);

    //查询所有登录日志
    List<ExportLoginLogBean> selectAllLog();

    void batchDeletedLog(List<String> logIds);

}
