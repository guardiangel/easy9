package org.colin.service;

import org.colin.model.vo.req.SalaryAddReqVO;
import org.colin.model.vo.req.SalaryPageReqVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.YwSalaryVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdministrationService {

    //新增工资记录
    void addSaraly(SalaryAddReqVO vo);

    //删除工资记录
    void deleteSaraly(String pkId);

    //分页查询所有工资记录
    PageVO<YwSalaryVO> pageInfo(SalaryPageReqVO vo);

    //根据条件导出所有工资记录
    void export(SalaryPageReqVO vo, HttpServletResponse response);
}
