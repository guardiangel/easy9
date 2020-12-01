package org.colin.license.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 自定义需要校验的License参数
 * @ClassName: LicenseCheckModel
 * @Author: wujiangbo
 * @Date: 2020/7/10 0010 14:11
 * @Version: 1.1.0
 */
public class LicenseCheckModel implements Serializable {

    private static final long serialVersionUID = -4317986994684757038L;

    /**
     * 是否认证ip
     */
    private boolean isIpCheck;

    /**
     * 可被允许的IP地址
     */
    private List<String> ipAddress;

    /**
     * 是否认证mac
     */
    private boolean isMacCheck;

    /**
     * 可被允许的MAC地址
     */
    private List<String> macAddress;

    /**
     * 可被允许的CPU序列号
     */
    private String cpuSerial;

    /**
     * 可被允许的主板序列号
     */
    private String mainBoardSerial;

    public boolean isIpCheck() {
        return isIpCheck;
    }

    public void setIpCheck(boolean ipCheck) {
        isIpCheck = ipCheck;
    }

    public List<String> getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(List<String> ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isMacCheck() {
        return isMacCheck;
    }

    public void setMacCheck(boolean macCheck) {
        isMacCheck = macCheck;
    }

    public List<String> getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(List<String> macAddress) {
        this.macAddress = macAddress;
    }

    public String getCpuSerial() {
        return cpuSerial;
    }

    public void setCpuSerial(String cpuSerial) {
        this.cpuSerial = cpuSerial;
    }

    public String getMainBoardSerial() {
        return mainBoardSerial;
    }

    public void setMainBoardSerial(String mainBoardSerial) {
        this.mainBoardSerial = mainBoardSerial;
    }

    @Override
    public String toString() {
        return "LicenseCheckModel{" +
                "isIpCheck=" + isIpCheck +
                ", ipAddress=" + ipAddress +
                ", isMacCheck=" + isMacCheck +
                ", macAddress=" + macAddress +
                ", cpuSerial='" + cpuSerial + '\'' +
                ", mainBoardSerial='" + mainBoardSerial + '\'' +
                '}';
    }
}
