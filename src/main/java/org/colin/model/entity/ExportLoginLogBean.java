package org.colin.model.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: ExportLoginLogBean
 * @ClassName: ExportLoginLogBean
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/25 11:26
 * @Version: 1.1.0
 */
@ContentRowHeight(25)
@HeadRowHeight(36)
@ColumnWidth(38)
public class ExportLoginLogBean extends BaseRowModel implements Serializable {

    @ExcelProperty(value = {"用户ID"}, index = 0)
    private String userId;

    @ExcelProperty(value = {"登录时间"}, index = 1)
    private Date createTime;

    @ExcelProperty(value = {"登录账号"}, index = 2)
    private String userName;

    @ExcelProperty(value = {"登录IP"}, index = 3)
    private String loginIp;

    @ExcelProperty(value = {"登录地点"}, index = 4)
    private String loginAddress;

    @ExcelProperty(value = {"登录浏览器"}, index = 5)
    private String loginBrowser;

    @ExcelProperty(value = {"登录渠道(1：Web；2：H5；3：Android；4：IOS；5：其他；)"}, index = 6)
    private Integer loginWay;

    @Override
    public String toString() {
        return "ExportLoginLogBean{" +
                "userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", userName='" + userName + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", loginAddress='" + loginAddress + '\'' +
                ", loginBrowser='" + loginBrowser + '\'' +
                ", loginWay=" + loginWay +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
