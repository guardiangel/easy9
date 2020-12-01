package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description: 数据库表信息
 * @ClassName: TableVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/26 8:53
 * @Version: 1.1.0
 */
public class TableVO implements Serializable {

    @ApiModelProperty(value = "tableSchema")
    private String tableSchema;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "表数据总行数")
    private Integer tableRows;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "表注释")
    private String tableComment;

    @Override
    public String toString() {
        return "TableVO{" +
                "tableSchema='" + tableSchema + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableRows=" + tableRows +
                ", createTime=" + createTime +
                ", tableComment='" + tableComment + '\'' +
                '}';
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getTableRows() {
        return tableRows;
    }

    public void setTableRows(Integer tableRows) {
        this.tableRows = tableRows;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}
