package org.colin.service.impl;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.mapper.yw.YwSalaryMapper;
import org.colin.model.entity.ExportSalaryBean;
import org.colin.model.vo.req.SalaryAddReqVO;
import org.colin.model.vo.req.SalaryPageReqVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.YwSalaryVO;
import org.colin.service.AdministrationService;
import org.colin.utils.ArithTool;
import org.colin.utils.PageUtils;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class AdministrationServiceImpl implements AdministrationService {

    @Autowired
    private YwSalaryMapper ywSalaryMapper;

    @Override
    public void addSaraly(SalaryAddReqVO vo) {
        if(!Tool.isBlank(vo.getYearMonth())){
            vo.setId(Tool.getPrimaryKey());
            vo.setCreateTime(new Date());
            vo.setSalaryYear(vo.getYearMonth().split("-")[0]);
            vo.setSalaryMonth(vo.getYearMonth().split("-")[1]);
        }else{
            throw new ServiceException(BaseResponseCode.XZGL_YEARMONTH_ERROR);
        }
        ywSalaryMapper.insertSelective(vo);
    }

    @Override
    public void deleteSaraly(String pkId) {
        ywSalaryMapper.deleteByPrimaryKey(pkId);
    }

    public void setData1(List<YwSalaryVO> objList){
        if(CollectionUtils.isNotEmpty(objList)){
            List<BigDecimal> koukuanList = new ArrayList<>();
            List<BigDecimal> jiangjinList = new ArrayList<>();
            for(int i=0; i<objList.size(); i++){
                YwSalaryVO obj = objList.get(i);
                //扣款项
                koukuanList.add(obj.getYanglaoInsurance());
                koukuanList.add(obj.getYiliaoInsurance());
                koukuanList.add(obj.getShiyeInsurance());
                koukuanList.add(obj.getGongshangInsurance());
                koukuanList.add(obj.getShengyuInsurance());
                koukuanList.add(obj.getPublicAccumulationFunds());
                koukuanList.add(obj.getPayTaxes());
                koukuanList.add(obj.getPunish());
                koukuanList.add(obj.getAttendancePunish());
                obj.setTotal_koukuan(ArithTool.addByList(koukuanList, 2));
                //奖金项
                jiangjinList.add(obj.getBonus());
                jiangjinList.add(obj.getTravelAllowance());
                jiangjinList.add(obj.getDeptAchievements());
                jiangjinList.add(obj.getQuarterAchievements());
                jiangjinList.add(obj.getSpecialSubsidy());
                jiangjinList.add(obj.getAttendanceSubsidy());
                jiangjinList.add(obj.getAlltimeBonus());
                jiangjinList.add(obj.getWorkyearBonus());
                obj.setTotal_jiangjin(ArithTool.addByList(jiangjinList, 2));
                //计算实发工资
                BigDecimal c1 = obj.getMonthSalary().add(obj.getTotal_jiangjin()).setScale(2, BigDecimal.ROUND_HALF_UP);
                obj.setActual_pay(c1.subtract(obj.getTotal_koukuan()).setScale(2, BigDecimal.ROUND_HALF_UP));
                //拼接工资发放时间
                obj.setPay_time(obj.getSalaryYear() + "-" + obj.getSalaryMonth());
            }
        }
    }

    public void setData2(List<ExportSalaryBean> objList){
        if(CollectionUtils.isNotEmpty(objList)){
            List<BigDecimal> koukuanList = new ArrayList<>();
            List<BigDecimal> jiangjinList = new ArrayList<>();
            for(int i=0; i<objList.size(); i++){
                ExportSalaryBean obj = objList.get(i);
                //扣款项
                koukuanList.add(obj.getYanglaoInsurance());
                koukuanList.add(obj.getYiliaoInsurance());
                koukuanList.add(obj.getShiyeInsurance());
                koukuanList.add(obj.getGongshangInsurance());
                koukuanList.add(obj.getShengyuInsurance());
                koukuanList.add(obj.getPublicAccumulationFunds());
                koukuanList.add(obj.getPayTaxes());
                koukuanList.add(obj.getPunish());
                koukuanList.add(obj.getAttendancePunish());
                obj.setTotal_koukuan(ArithTool.addByList(koukuanList, 2));
                //奖金项
                jiangjinList.add(obj.getBonus());
                jiangjinList.add(obj.getTravelAllowance());
                jiangjinList.add(obj.getDeptAchievements());
                jiangjinList.add(obj.getQuarterAchievements());
                jiangjinList.add(obj.getSpecialSubsidy());
                jiangjinList.add(obj.getAttendanceSubsidy());
                jiangjinList.add(obj.getAlltimeBonus());
                jiangjinList.add(obj.getWorkyearBonus());
                obj.setTotal_jiangjin(ArithTool.addByList(jiangjinList, 2));
                //计算实发工资
                BigDecimal c1 = obj.getMonthSalary().add(obj.getTotal_jiangjin()).setScale(2, BigDecimal.ROUND_HALF_UP);
                obj.setActual_pay(c1.subtract(obj.getTotal_koukuan()).setScale(2, BigDecimal.ROUND_HALF_UP));
                //拼接工资发放时间
                obj.setPay_time(obj.getSalaryYear() + "-" + obj.getSalaryMonth());
            }
        }
    }

    @Override
    public PageVO<YwSalaryVO> pageInfo(SalaryPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        if(!Tool.isBlank(vo.getYearMonth())){
            vo.setSalaryYear(vo.getYearMonth().split("-")[0]);
            vo.setSalaryMonth(vo.getYearMonth().split("-")[1]);
        }
        List<YwSalaryVO> objList = ywSalaryMapper.selectAll(vo);
        setData1(objList);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public void export(SalaryPageReqVO vo, HttpServletResponse response) {
        String fileName = Tool.getUUID49();//获取文件名称
        List<ExportSalaryBean> list = ywSalaryMapper.selectAllExport(vo);
        setData2(list);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            Sheet sheet = new Sheet(1, 0, ExportSalaryBean.class);
            //设置自适应宽度
            sheet.setAutoWidth(Boolean.TRUE);
            // 第一个 sheet 名称
            sheet.setSheetName("工资列表");
            writer.write(list, sheet);
            //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1") + ".xlsx");
            writer.finish();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("UTF-8");
            out.flush();
        } catch (IOException e) {
            log.error("导出Excel异常:{}", e.getLocalizedMessage());
        } finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e1) {
                    log.error("导出Excel异常:{}", e1.getLocalizedMessage());
                }
            }
        }
    }
}
