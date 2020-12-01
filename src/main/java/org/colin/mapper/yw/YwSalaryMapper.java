package org.colin.mapper.yw;

import org.colin.model.entity.ExportSalaryBean;
import org.colin.model.entity.yw.YwSalary;
import org.colin.model.vo.req.SalaryAddReqVO;
import org.colin.model.vo.req.SalaryPageReqVO;
import org.colin.model.vo.resp.YwSalaryVO;
import java.util.List;

public interface YwSalaryMapper {

    int deleteByPrimaryKey(String id);

    int insert(YwSalary record);

    int insertSelective(SalaryAddReqVO record);

    YwSalary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YwSalary record);

    int updateByPrimaryKey(YwSalary record);

    List<YwSalaryVO> selectAll(SalaryPageReqVO vo);

    List<ExportSalaryBean> selectAllExport(SalaryPageReqVO vo);
}