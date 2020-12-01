package org.colin.service;

import org.colin.model.entity.SysLog;
import org.colin.model.vo.req.SysLogPageReqVO;
import org.colin.model.vo.resp.PageVO;

import java.util.List;
public interface LogService {

    PageVO<SysLog> pageInfo(SysLogPageReqVO vo);

    void deleted(List<String> logIds);
}
