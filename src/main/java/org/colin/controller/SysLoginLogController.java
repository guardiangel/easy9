package org.colin.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.model.entity.ExportLoginLogBean;
import org.colin.model.ro.LoginLogPageRO;
import org.colin.model.vo.resp.LoginLogVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.service.SysLoginLogService;
import org.colin.utils.DataResult;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RequestMapping("/sys")
@Api(tags = "系统模块-登录日志管理")
@Controller
public class SysLoginLogController {

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @ApiOperation(value = "下载所有登录日志")
    @RequiresPermissions("sys:loginLogs:exportAllLoginLogExcel")
    @GetMapping("/loginLogs/exportAllLoginLogExcel")
    public void export(HttpServletResponse response) throws IOException {
        List<ExportLoginLogBean> list = sysLoginLogService.selectAll();
        OutputStream out = response.getOutputStream();
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        String fileName = "Easy9云平台登录日志记录表_" + Tool.getCurrentTimeStr();
        Sheet sheet = new Sheet(1, 0, ExportLoginLogBean.class);
        //设置自适应宽度
        sheet.setAutoWidth(Boolean.TRUE);
        // 第一个 sheet 名称
        sheet.setSheetName("登录日志");
        writer.write(list, sheet);
        //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1") + ".xlsx");
        writer.finish();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("UTF-8");
        out.flush();
    }

    @ResponseBody
    @PostMapping("/loginLogs")
    @ApiOperation(value = "分页查询登录日志")
    @RequiresPermissions("sys:loginLogs:list")
    public DataResult<PageVO<LoginLogVO>> pageInfo(@RequestBody LoginLogPageRO ro) {
        PageVO<LoginLogVO> loginLogVO = sysLoginLogService.pageInfo(ro);
        DataResult<PageVO<LoginLogVO>> result = DataResult.success();
        result.setData(loginLogVO);
        return result;
    }

    @ResponseBody
    @DeleteMapping("/loginLogs")
    @ApiOperation(value = "删除登录日志")
    @LogAnnotation(title = "登录日志", action = "删除登录日志")
    @RequiresPermissions("sys:loginLog:deleted")
    public DataResult<PageVO<LoginLogVO>> delete(@RequestBody List<String> logIds) {
        sysLoginLogService.deleted(logIds);
        return DataResult.success();
    }

}