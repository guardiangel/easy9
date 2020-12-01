package org.colin.model.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @Description: YwCommunityPageVO
 * @ClassName: YwCommunityPageVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/6 11:24
 * @Version: 1.1.0
 */
public class YwCommunityPageVO {

    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    @ApiModelProperty(value = "社区名称")
    private String comName;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @Override
    public String toString() {
        return "YwCommunityPageVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", comName='" + comName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
