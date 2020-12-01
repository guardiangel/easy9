package org.colin.license;

import de.schlichtherle.license.AbstractKeyStoreParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 自定义KeyStoreParam，用于将公私钥存储文件存放到其他磁盘位置而不是项目中
 * @ClassName: CustomKeyStoreParam
 * @Author: wujiangbo
 * @Date: 2020/7/10 0010 14:16
 * @Version: 1.1.0
 */
public class CustomKeyStoreParam extends AbstractKeyStoreParam {

    private String storePath;//公钥/私钥在磁盘上的存储路径
    private String alias;
    private String storePwd;
    private String keyPwd;

    public CustomKeyStoreParam(Class clazz, String resource,String alias,String storePwd,String keyPwd) {
        super(clazz, resource);
        this.storePath = resource;
        this.alias = alias;
        this.storePwd = storePwd;
        this.keyPwd = keyPwd;
    }


    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getStorePwd() {
        return storePwd;
    }

    @Override
    public String getKeyPwd() {
        return keyPwd;
    }

    /**
     * 复写de.schlichtherle.license.AbstractKeyStoreParam的getStream()方法<br/>
     * 用于将公私钥存储文件存放到其他磁盘位置而不是项目中
     */
    @Override
    public InputStream getStream() throws IOException {
        final InputStream in = new FileInputStream(new File(storePath));
        if (null == in){
            throw new FileNotFoundException(storePath);
        }
        return in;
    }
}
