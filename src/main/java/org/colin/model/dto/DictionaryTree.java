package org.colin.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @ClassName: DictionaryTree
 * @Author: wujiangbo
 * @Date: 2020/7/27 19:09
 * @Version: 1.1.0
 */
@ApiModel(value = "数据字典类型树型数据模型")
public class DictionaryTree {

    @ApiModelProperty(value = "节点ID")
    private String id;

    @ApiModelProperty(value = "父节点ID")
    private String pid;

    @ApiModelProperty(value = "节点名称")
    private String name;

    @ApiModelProperty(value = "节点是否选中（true：选中；false：未选中）")
    private boolean checked;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "字典类型编码")
    private String typeCode;

    @ApiModelProperty(value = "备用字段")
    private String bak;

    @ApiModelProperty(value = "状态(0：不可用；1：可用)")
    private Integer status;

    @ApiModelProperty(value = "标题(鼠标停留节点时提示信息)")
    private String title;

    @Override
    public String toString() {
        return "DictionaryTree{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", checked=" + checked +
                ", icon='" + icon + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", bak='" + bak + '\'' +
                ", status=" + status +
                ", title='" + title + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
