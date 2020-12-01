package org.colin.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
/**
 * @Description: BASE64DecodedMultipartFile
 * @ClassName: BASE64DecodedMultipartFile
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/1 13:15
 * @Version: 1.1.0
 */
public class BASE64DecodedMultipartFile implements MultipartFile {

    private final byte[] imgContent;

    private final String header;

    public BASE64DecodedMultipartFile(byte[] imgContent, String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
    }

    @Override
    public String getName() {
        return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        return System.currentTimeMillis() + (int) Math.random() * 10000 + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        return header.split(":")[1];
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }
}
