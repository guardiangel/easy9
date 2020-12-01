package org.colin.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * @Description: ImageTool
 * @ClassName: ImageTool
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/3 11:27
 * @Version: 1.1.0
 */
@Slf4j
public class ImageTool {

    public static void main(String[] args) {
        String imgPath = "E:/user_photo.jpg";
        String imgBase64 = GetImageStr(imgPath);
        System.out.println(imgBase64);
    }

    /*
     * @Description: 图片转化成base64字符串
     * @MethodName: GetImageStr
     * @param:  [imgFile]
     * @return: java.lang.String
     * @Author: wujiangbo
     * @Date: 2020/3/3 11:28
     */
    public static String GetImageStr(String imgFile) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            log.error("图片转化成base64字符串，异常：{}", e);
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return "data:img/jpg;base64," + encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    /*
     * @Description:base64字符串转化成图片
     * @MethodName: GenerateImage
     * @param:  [base64str, savepath]
     * @return: boolean
     * @Author: wujiangbo
     * @Date: 2020/3/3 11:28
     */
    public static boolean GenerateImage(String base64str, String savepath) {   //对字节数组字符串进行Base64解码并生成图片
        if (base64str == null) //图像数据为空  
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码  
            byte[] b = decoder.decodeBuffer(base64str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据  
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(savepath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            log.error("base64字符串转化成图片，异常：{}", e);
            return false;
        }
    }
}
