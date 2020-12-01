package org.colin.model.entity;

import java.util.Date;

public class SysBbsAccessLog {
    private String id;

    private Date accessTime;

    private String accessIp;

    private String accessAddress;

    private String accessBrowser;

    private Integer accessWay;//访问方式（1：Web；2：H5；3：Android；4：IOS；5：其他；6：iPad；）

    private String remk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp == null ? null : accessIp.trim();
    }

    public String getAccessAddress() {
        return accessAddress;
    }

    public void setAccessAddress(String accessAddress) {
        this.accessAddress = accessAddress == null ? null : accessAddress.trim();
    }

    public String getAccessBrowser() {
        return accessBrowser;
    }

    public void setAccessBrowser(String accessBrowser) {
        this.accessBrowser = accessBrowser == null ? null : accessBrowser.trim();
    }

    public Integer getAccessWay() {
        return accessWay;
    }

    public void setAccessWay(Integer accessWay) {
        this.accessWay = accessWay;
    }

    public String getRemk() {
        return remk;
    }

    public void setRemk(String remk) {
        this.remk = remk == null ? null : remk.trim();
    }
}