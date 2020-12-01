package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * @Description: YwCommStoreTreeVO
 * @ClassName: YwCommStoreTreeVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/9 15:28
 * @Version: 1.1.0
 */
public class YwCommStoreTreeVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "父节点ID")
    private String pid;

    @ApiModelProperty(value = "节点名称")
    private String nodeName;

    @ApiModelProperty(value = "节点类型(1：社区；2：商家；)")
    private Integer nodeType;

    @Override
    public String toString() {
        return "YwCommStoreTreeVO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", nodeType=" + nodeType +
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

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }
}
