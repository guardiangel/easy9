package org.colin.license;

import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseContentException;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseNotary;
import de.schlichtherle.license.LicenseParam;
import de.schlichtherle.license.NoLicenseInstalledException;
import de.schlichtherle.xml.GenericCertificate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.license.pojo.LicenseCheckModel;
import org.colin.license.service.AbstractServerInfos;
import org.colin.license.service.LinuxServerInfos;
import org.colin.license.service.WindowsServerInfos;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @Description: 自定义LicenseManager，用于增加额外的服务器硬件信息校验
 * @ClassName: CustomLicenseManager
 * @Author: wujiangbo
 * @Date: 2020/7/10
 * @Version: 1.1.0
 */
@Slf4j
public class CustomLicenseManager extends LicenseManager {

    //XML编码
    private static final String XML_CHARSET = "UTF-8";

    //默认BUFSIZE
    private static final int DEFAULT_BUFSIZE = 8 * 1024;

    public CustomLicenseManager() {
    }

    public CustomLicenseManager(LicenseParam param) {
        super(param);
    }

    /**
     * 复写create方法
     */
    @Override
    protected synchronized byte[] create(
            LicenseContent content,
            LicenseNotary notary)
            throws Exception {
        initialize(content);
        this.validateCreate(content);
        final GenericCertificate certificate = notary.sign(content);
        return getPrivacyGuard().cert2key(certificate);
    }

    /**
     * 复写install方法，其中validate方法调用本类中的validate方法，校验IP地址、Mac地址等其他信息
     */
    @Override
    protected synchronized LicenseContent install(
            final byte[] key,
            final LicenseNotary notary)
            throws Exception {
        final GenericCertificate certificate = getPrivacyGuard().key2cert(key);
        notary.verify(certificate);
        final LicenseContent content = (LicenseContent)this.load(certificate.getEncoded());
        this.validate(content);
        setLicenseKey(key);
        setCertificate(certificate);
        return content;
    }

    /**
     * 复写verify方法，调用本类中的validate方法，校验IP地址、Mac地址等其他信息
     */
    @Override
    protected synchronized LicenseContent verify(final LicenseNotary notary)
            throws Exception {
        GenericCertificate certificate = getCertificate();
        final byte[] key = getLicenseKey();
        if (null == key){
            throw new NoLicenseInstalledException(getLicenseParam().getSubject());
        }
        certificate = getPrivacyGuard().key2cert(key);
        notary.verify(certificate);
        final LicenseContent content = (LicenseContent)this.load(certificate.getEncoded());
        this.validate(content);
        setCertificate(certificate);
        return content;
    }

    /**
     * @Description: 校验生成证书的参数信息
     * @MethodName: validateCreate
     * @param:  [content] 证书正文
     * @return: void
     * @Author: wujiangbo(QQ:1134135987)
     * @Date: 2020/7/9
     */
    protected synchronized void validateCreate(final LicenseContent content) {
        final LicenseParam param = getLicenseParam();
        final Date now = new Date();
        final Date notBefore = content.getNotBefore();
        final Date notAfter = content.getNotAfter();
        if (null != notAfter && now.after(notAfter)) {
            throw new ServiceException(BaseResponseCode.LICENSE_ERROR_9);
        }
        if (null != notBefore && null != notAfter && notAfter.before(notBefore)) {
            throw new ServiceException(BaseResponseCode.LICENSE_ERROR_8);
        }
        final String consumerType = content.getConsumerType();
        if (null == consumerType) {
            throw new ServiceException(BaseResponseCode.LICENSE_ERROR_7);
        }
    }


    /**
     * 复写validate方法，增加IP地址、Mac地址等其他信息校验
     */
    @Override
    protected synchronized void validate(final LicenseContent content) throws LicenseContentException {
        //1. 首先调用父类的validate方法
        super.validate(content);
        //2. 获取License授权文件中的服务器信息
        LicenseCheckModel expectedCheck = (LicenseCheckModel) content.getExtra();
        if(expectedCheck == null){
            log.error("无法从License授权文件获取授权硬件信息");
            throw new ServiceException(BaseResponseCode.LICENSE_ERROR_10);
        }
        //获取当前服务器真实的参数信息
        LicenseCheckModel serverCheckModel = getServerInfos();
        if(serverCheckModel == null){
            log.error("无法获取当前服务器硬件信息");
            throw new ServiceException(BaseResponseCode.LICENSE_ERROR_2);
        }
        //开始校验服务器硬件相关信息
        //校验IP地址
        if(expectedCheck.isIpCheck() && !checkIpAddress(expectedCheck.getIpAddress(),serverCheckModel.getIpAddress())){
            log.error("证书无效，当前服务器的IP没在授权范围内");
            throw new ServiceException(BaseResponseCode.LICENSE_ERROR_6);
        }
        //校验Mac地址
        if(expectedCheck.isIpCheck() && !checkIpAddress(expectedCheck.getMacAddress(),serverCheckModel.getMacAddress())){
            log.error("证书无效，当前服务器的Mac地址没在授权范围内");
            throw new ServiceException(BaseResponseCode.LICENSE_ERROR_5);
        }
        //校验主板序列号
        if(expectedCheck.isIpCheck() && !checkSerial(expectedCheck.getMainBoardSerial(),serverCheckModel.getMainBoardSerial())){
            log.error("证书无效，当前服务器的主板序列号没在授权范围内");
            throw new ServiceException(BaseResponseCode.LICENSE_ERROR_4);
        }
        //校验CPU序列号
        if(expectedCheck.isIpCheck() && !checkSerial(expectedCheck.getCpuSerial(),serverCheckModel.getCpuSerial())){
            log.error("证书无效，当前服务器的CPU序列号没在授权范围内");
            throw new ServiceException(BaseResponseCode.LICENSE_ERROR_3);
        }
    }


    /**
     * 重写XMLDecoder解析XML
     */
    private Object load(String encoded){
        BufferedInputStream inputStream = null;
        XMLDecoder decoder = null;
        try {
            inputStream = new BufferedInputStream(new ByteArrayInputStream(encoded.getBytes(XML_CHARSET)));
            decoder = new XMLDecoder(new BufferedInputStream(inputStream, DEFAULT_BUFSIZE),null,null);
            return decoder.readObject();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                if(decoder != null){
                    decoder.close();
                }
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (Exception e) {
                log.error("XMLDecoder解析XML失败",e);
            }
        }

        return null;
    }

    /**
     * 获取当前服务器需要额外校验的License参数
     */
    private LicenseCheckModel getServerInfos(){
        //操作系统类型
        String osName = System.getProperty("os.name").toLowerCase();
        AbstractServerInfos abstractServerInfos = null;
        //根据不同操作系统类型选择不同的数据获取方法
        if (osName.startsWith("windows")) {
            abstractServerInfos = new WindowsServerInfos();
        } else if (osName.startsWith("linux")) {
            abstractServerInfos = new LinuxServerInfos();
        }else{//其他服务器类型
            abstractServerInfos = new LinuxServerInfos();
        }
        return abstractServerInfos.getServerInfos();
    }

    /**
     * 校验当前服务器的IP/Mac地址是否在可被允许的IP范围内<br/>
     * 如果存在IP在可被允许的IP/Mac地址范围内，则返回true
     */
    private boolean checkIpAddress(List<String> expectedList,List<String> serverList){
        if(expectedList != null && expectedList.size() > 0){
            if(serverList != null && serverList.size() > 0){
                for(String expected : expectedList){
                    if(serverList.contains(expected.trim())){
                        return true;
                    }
                }
            }
            return false;
        }else {
            return true;
        }
    }

    /**
     * 校验当前服务器硬件（主板、CPU等）序列号是否在可允许范围内
     */
    private boolean checkSerial(String expectedSerial,String serverSerial){
        if(StringUtils.isNotBlank(expectedSerial)){
            if(StringUtils.isNotBlank(serverSerial)){
                if(expectedSerial.equals(serverSerial)){
                    return true;
                }
            }
            return false;
        }else{
            return true;
        }
    }
}
