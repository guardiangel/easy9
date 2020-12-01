package org.colin.model.entity.tree;

import java.io.Serializable;
import java.util.List;

//下拉选择框树型数据模型
public class ATree implements Serializable {

    private String id;
    private String title;
    private String icon;
    private String pid;
    private String pidName;
    private boolean spread = true;//false:不展开;true:展开
    private List<?> children;

    @Override
    public String toString() {
        return "ATree{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", pid='" + pid + '\'' +
                ", pidName='" + pidName + '\'' +
                ", spread=" + spread +
                ", children=" + children +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPidName() {
        return pidName;
    }

    public void setPidName(String pidName) {
        this.pidName = pidName;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }
}
