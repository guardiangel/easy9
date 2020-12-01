package org.colin.model.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class YwCbfq implements Serializable {

    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "服务请求号")
    private String fwqqh;

    @ApiModelProperty(value = "服务申请编码")
    private String fwsqm;

    @ApiModelProperty(value = "服务申请描述")
    private String fwsqms;

    @ApiModelProperty(value = "公司代码")
    private String gsdm;

    @ApiModelProperty(value = "优先级")
    private String yxj;

    @ApiModelProperty(value = "利润中心/会计集中核算主体")
    private String hszt;

    @ApiModelProperty(value = "服务对象")
    private String fwdx;

    @ApiModelProperty(value = "实际派工人")
    private String sjpgr;

    @ApiModelProperty(value = "会计年度")
    private String kjnd;

    @ApiModelProperty(value = "会计期间")
    private String kjqj;

    @ApiModelProperty(value = "状态")
    private String zt;

    @ApiModelProperty(value = "挂账-到期付款")
    private String dqfk;

    @ApiModelProperty(value = "创建日期")
    private Date cjrq;

    @ApiModelProperty(value = "运营部名称")
    private String yybmc;

    @ApiModelProperty(value = "末级服务目录名称")
    private String mjfwmlmc;

    @ApiModelProperty(value = "执行人员姓名")
    private String zxrxm;

    @ApiModelProperty(value = "执行日期")
    private String zxrq;

    @ApiModelProperty(value = "操作步骤")
    private String czbz;

    @ApiModelProperty(value = "岗位")
    private String gw;

    @ApiModelProperty(value = "CheckList状态描述")
    private String ztms;

    @ApiModelProperty(value = "Checklist步骤开始日期")
    private Date bzksrq;

    @ApiModelProperty(value = "Checklist步骤结束日期")
    private Date bzjsrq;

    @ApiModelProperty(value = "检查清单标识")
    private String jcqdbs;

    @ApiModelProperty(value = "付款日期")
    private Date fkrq;

    @ApiModelProperty(value = "实际派工人姓名")
    private String sjpgrxm;

    @ApiModelProperty(value = "协同子状态描述")
    private String xtzztms;

    @ApiModelProperty(value = "协同子状态")
    private String xtzzt;

    @ApiModelProperty(value = "FQ提报人姓名")
    private String tbrxm;

    @ApiModelProperty(value = "预制发票号")
    private String yzfph;

    @ApiModelProperty(value = "CheckList状态编码")
    private String zzbm;

    @ApiModelProperty(value = "末级服务目录ID")
    private String mjfwml;

    @ApiModelProperty(value = "往来单位名称")
    private String wldwmc;

    @ApiModelProperty(value = "往来单位")
    private String wldw;

    @ApiModelProperty(value = "服务对象描述")
    private String fwdxms;

    @ApiModelProperty(value = "客户/供应商")
    private String gys;

    @ApiModelProperty(value = "FQ创建申请日期")
    private Date cjsqrq;

    @ApiModelProperty(value = "SLA截止时间")
    private Date jzsj;

    @ApiModelProperty(value = "SLA截止日期")
    private Date jzrq;

    @ApiModelProperty(value = "创建时间")
    private Date cjsj;

    @ApiModelProperty(value = "用户状态")
    private String yhzt;

    @ApiModelProperty(value = "部门")
    private String bm;

    @ApiModelProperty(value = "CheckList步骤结束时间")
    private Date bzjssj;

    @ApiModelProperty(value = "CheckList步骤开始时间")
    private Date bzkssj;

    @ApiModelProperty(value = "CheckList步骤时间小计")
    private String bzsjxj;

    @ApiModelProperty(value = "FQ提报人联系电话")
    private String tbrlxdh;

    @ApiModelProperty(value = "执行时间")
    private Date zxsj;

    @ApiModelProperty(value = "执行人员编号")
    private String zxrbh;

    @Override
    public String toString() {
        return "YwCbfq{" +
                "id='" + id + '\'' +
                ", fwqqh='" + fwqqh + '\'' +
                ", fwsqm='" + fwsqm + '\'' +
                ", fwsqms='" + fwsqms + '\'' +
                ", gsdm='" + gsdm + '\'' +
                ", yxj='" + yxj + '\'' +
                ", hszt='" + hszt + '\'' +
                ", fwdx='" + fwdx + '\'' +
                ", sjpgr='" + sjpgr + '\'' +
                ", kjnd='" + kjnd + '\'' +
                ", kjqj='" + kjqj + '\'' +
                ", zt='" + zt + '\'' +
                ", dqfk='" + dqfk + '\'' +
                ", cjrq=" + cjrq +
                ", yybmc='" + yybmc + '\'' +
                ", mjfwmlmc='" + mjfwmlmc + '\'' +
                ", zxrxm='" + zxrxm + '\'' +
                ", zxrq='" + zxrq + '\'' +
                ", czbz='" + czbz + '\'' +
                ", gw='" + gw + '\'' +
                ", ztms='" + ztms + '\'' +
                ", bzksrq=" + bzksrq +
                ", bzjsrq=" + bzjsrq +
                ", jcqdbs='" + jcqdbs + '\'' +
                ", fkrq=" + fkrq +
                ", sjpgrxm='" + sjpgrxm + '\'' +
                ", xtzztms='" + xtzztms + '\'' +
                ", xtzzt='" + xtzzt + '\'' +
                ", tbrxm='" + tbrxm + '\'' +
                ", yzfph='" + yzfph + '\'' +
                ", zzbm='" + zzbm + '\'' +
                ", mjfwml='" + mjfwml + '\'' +
                ", wldwmc='" + wldwmc + '\'' +
                ", wldw='" + wldw + '\'' +
                ", fwdxms='" + fwdxms + '\'' +
                ", gys='" + gys + '\'' +
                ", cjsqrq=" + cjsqrq +
                ", jzsj=" + jzsj +
                ", jzrq=" + jzrq +
                ", cjsj=" + cjsj +
                ", yhzt='" + yhzt + '\'' +
                ", bm='" + bm + '\'' +
                ", bzjssj=" + bzjssj +
                ", bzkssj=" + bzkssj +
                ", bzsjxj='" + bzsjxj + '\'' +
                ", tbrlxdh='" + tbrlxdh + '\'' +
                ", zxsj=" + zxsj +
                ", zxrbh='" + zxrbh + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFwqqh() {
        return fwqqh;
    }

    public void setFwqqh(String fwqqh) {
        this.fwqqh = fwqqh == null ? null : fwqqh.trim();
    }

    public String getFwsqm() {
        return fwsqm;
    }

    public void setFwsqm(String fwsqm) {
        this.fwsqm = fwsqm == null ? null : fwsqm.trim();
    }

    public String getFwsqms() {
        return fwsqms;
    }

    public void setFwsqms(String fwsqms) {
        this.fwsqms = fwsqms == null ? null : fwsqms.trim();
    }

    public String getGsdm() {
        return gsdm;
    }

    public void setGsdm(String gsdm) {
        this.gsdm = gsdm == null ? null : gsdm.trim();
    }

    public String getYxj() {
        return yxj;
    }

    public void setYxj(String yxj) {
        this.yxj = yxj == null ? null : yxj.trim();
    }

    public String getHszt() {
        return hszt;
    }

    public void setHszt(String hszt) {
        this.hszt = hszt == null ? null : hszt.trim();
    }

    public String getFwdx() {
        return fwdx;
    }

    public void setFwdx(String fwdx) {
        this.fwdx = fwdx == null ? null : fwdx.trim();
    }

    public String getSjpgr() {
        return sjpgr;
    }

    public void setSjpgr(String sjpgr) {
        this.sjpgr = sjpgr == null ? null : sjpgr.trim();
    }

    public String getKjnd() {
        return kjnd;
    }

    public void setKjnd(String kjnd) {
        this.kjnd = kjnd == null ? null : kjnd.trim();
    }

    public String getKjqj() {
        return kjqj;
    }

    public void setKjqj(String kjqj) {
        this.kjqj = kjqj == null ? null : kjqj.trim();
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt == null ? null : zt.trim();
    }

    public String getDqfk() {
        return dqfk;
    }

    public void setDqfk(String dqfk) {
        this.dqfk = dqfk == null ? null : dqfk.trim();
    }

    public Date getCjrq() {
        return cjrq;
    }

    public void setCjrq(Date cjrq) {
        this.cjrq = cjrq;
    }

    public String getYybmc() {
        return yybmc;
    }

    public void setYybmc(String yybmc) {
        this.yybmc = yybmc == null ? null : yybmc.trim();
    }

    public String getMjfwmlmc() {
        return mjfwmlmc;
    }

    public void setMjfwmlmc(String mjfwmlmc) {
        this.mjfwmlmc = mjfwmlmc == null ? null : mjfwmlmc.trim();
    }

    public String getZxrxm() {
        return zxrxm;
    }

    public void setZxrxm(String zxrxm) {
        this.zxrxm = zxrxm == null ? null : zxrxm.trim();
    }

    public String getZxrq() {
        return zxrq;
    }

    public void setZxrq(String zxrq) {
        this.zxrq = zxrq == null ? null : zxrq.trim();
    }

    public String getCzbz() {
        return czbz;
    }

    public void setCzbz(String czbz) {
        this.czbz = czbz == null ? null : czbz.trim();
    }

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw == null ? null : gw.trim();
    }

    public String getZtms() {
        return ztms;
    }

    public void setZtms(String ztms) {
        this.ztms = ztms == null ? null : ztms.trim();
    }

    public Date getBzksrq() {
        return bzksrq;
    }

    public void setBzksrq(Date bzksrq) {
        this.bzksrq = bzksrq;
    }

    public Date getBzjsrq() {
        return bzjsrq;
    }

    public void setBzjsrq(Date bzjsrq) {
        this.bzjsrq = bzjsrq;
    }

    public String getJcqdbs() {
        return jcqdbs;
    }

    public void setJcqdbs(String jcqdbs) {
        this.jcqdbs = jcqdbs == null ? null : jcqdbs.trim();
    }

    public Date getFkrq() {
        return fkrq;
    }

    public void setFkrq(Date fkrq) {
        this.fkrq = fkrq;
    }

    public String getSjpgrxm() {
        return sjpgrxm;
    }

    public void setSjpgrxm(String sjpgrxm) {
        this.sjpgrxm = sjpgrxm == null ? null : sjpgrxm.trim();
    }

    public String getXtzztms() {
        return xtzztms;
    }

    public void setXtzztms(String xtzztms) {
        this.xtzztms = xtzztms == null ? null : xtzztms.trim();
    }

    public String getXtzzt() {
        return xtzzt;
    }

    public void setXtzzt(String xtzzt) {
        this.xtzzt = xtzzt == null ? null : xtzzt.trim();
    }

    public String getTbrxm() {
        return tbrxm;
    }

    public void setTbrxm(String tbrxm) {
        this.tbrxm = tbrxm == null ? null : tbrxm.trim();
    }

    public String getYzfph() {
        return yzfph;
    }

    public void setYzfph(String yzfph) {
        this.yzfph = yzfph == null ? null : yzfph.trim();
    }

    public String getZzbm() {
        return zzbm;
    }

    public void setZzbm(String zzbm) {
        this.zzbm = zzbm == null ? null : zzbm.trim();
    }

    public String getMjfwml() {
        return mjfwml;
    }

    public void setMjfwml(String mjfwml) {
        this.mjfwml = mjfwml == null ? null : mjfwml.trim();
    }

    public String getWldwmc() {
        return wldwmc;
    }

    public void setWldwmc(String wldwmc) {
        this.wldwmc = wldwmc == null ? null : wldwmc.trim();
    }

    public String getWldw() {
        return wldw;
    }

    public void setWldw(String wldw) {
        this.wldw = wldw == null ? null : wldw.trim();
    }

    public String getFwdxms() {
        return fwdxms;
    }

    public void setFwdxms(String fwdxms) {
        this.fwdxms = fwdxms == null ? null : fwdxms.trim();
    }

    public String getGys() {
        return gys;
    }

    public void setGys(String gys) {
        this.gys = gys == null ? null : gys.trim();
    }

    public Date getCjsqrq() {
        return cjsqrq;
    }

    public void setCjsqrq(Date cjsqrq) {
        this.cjsqrq = cjsqrq;
    }

    public Date getJzsj() {
        return jzsj;
    }

    public void setJzsj(Date jzsj) {
        this.jzsj = jzsj;
    }

    public Date getJzrq() {
        return jzrq;
    }

    public void setJzrq(Date jzrq) {
        this.jzrq = jzrq;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getYhzt() {
        return yhzt;
    }

    public void setYhzt(String yhzt) {
        this.yhzt = yhzt == null ? null : yhzt.trim();
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm == null ? null : bm.trim();
    }

    public Date getBzjssj() {
        return bzjssj;
    }

    public void setBzjssj(Date bzjssj) {
        this.bzjssj = bzjssj;
    }

    public Date getBzkssj() {
        return bzkssj;
    }

    public void setBzkssj(Date bzkssj) {
        this.bzkssj = bzkssj;
    }

    public String getBzsjxj() {
        return bzsjxj;
    }

    public void setBzsjxj(String bzsjxj) {
        this.bzsjxj = bzsjxj == null ? null : bzsjxj.trim();
    }

    public String getTbrlxdh() {
        return tbrlxdh;
    }

    public void setTbrlxdh(String tbrlxdh) {
        this.tbrlxdh = tbrlxdh == null ? null : tbrlxdh.trim();
    }

    public Date getZxsj() {
        return zxsj;
    }

    public void setZxsj(Date zxsj) {
        this.zxsj = zxsj;
    }

    public String getZxrbh() {
        return zxrbh;
    }

    public void setZxrbh(String zxrbh) {
        this.zxrbh = zxrbh == null ? null : zxrbh.trim();
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
}