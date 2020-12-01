package org.colin.utils;

import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import org.colin.code.BaseResponseCode;
import org.colin.constants.Constant;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 工具类
 * @ClassName: Tool
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/1 12:50
 * @Version: 1.1.0
 */
@Slf4j
public class Tool {

    public static void main(String[] args) {
//        String userPhotoPath = "D:/Easy9/userPhoto";
//        File file = new File(userPhotoPath);
//        String[] fileList = file.list();
//        for(int i=0; i<fileList.length; i++){
//            String newFilePath = userPhotoPath + "/" + fileList[0];
//            System.out.println(ImageToBase64(newFilePath));
//        }

//        System.out.println(getRandomString(1, 100000, 1));
//        for(int i=1; i<= 50; i++){
//            long currentMills = System.currentTimeMillis();
//            if(currentMills % 2 == 0){
//                System.out.println("男");
//            }else{
//                System.out.println("女");
//            }
//        }

        System.out.println(getCode123(23));
    }

    //传入过期日期，过期返回true,未过期返回false
    public static boolean dateExpired(String dateStr){
        boolean flag = false;
        Date nowDate = new Date();
        Date pastDate = null;
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        //在日期字符串非空时执行
        if (dateStr != null && !"".equals(dateStr)) {
            try {
                //将字符串转为日期格式，如果此处字符串为非合法日期就会抛出异常。
                pastDate = sdf.parse(dateStr);
                //调用Date里面的before方法来做判断
                flag = pastDate.before(nowDate);
                if (flag) {
                    log.info("该日期早于今日");
                }else {
                    log.info("该日期晚于今日");
                }
            } catch (ParseException e) {
                log.error("日期解析异常:{}", e.getLocalizedMessage());
            }
        }else {
            log.info("日期参数不可为空");
        }
        return flag;
    }

    /** 
     * @Description: 将本地图片转成Base64数据
     * @MethodName: ImageToBase64
     * @param:  [imgPath]
     * @return: java.lang.String
     * @Author: wujiangbo(QQ:1134135987)
     * @Date: 2020/9/21 20:46
     */ 
    public static String ImageToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            log.error("图片转Base64数据时发生异常:{}", e.getLocalizedMessage());
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return Constant.BASE64_HEAD + encoder.encode(Objects.requireNonNull(data));
    }

    //返回前端JSON信息
    public static void responseMessage(HttpServletResponse response, BaseResponseCode code) {
        try {
            response.setContentType("application/json;charset=" + Constant.SYSTEM_CHARACTER_ENCODING);
            OutputStream out = response.getOutputStream();
            DataResult result = new DataResult();
            result.setCode(code.getCode());
            result.setMsg(code.getMsg());
            String str = JSON.toJSONString(result);
            out.write(str.getBytes(Constant.SYSTEM_CHARACTER_ENCODING));
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("responseMessage error:{}", e);
        }
    }

    /* 
     * @Description: 验证密码，必须是6-18位长度，必须由数字和字母组成，并且要同时含有数字和字母，且长度要在6-18位之间
     * @MethodName: checkNumChar
     * @param:  [str]
     * @return: boolean
     * @Author: wujiangbo
     * @Date: 2020/7/1 0001 13:23
     */ 
    public static boolean checkPassword(String str){
        boolean checkResult = false;
        if(!Tool.isBlank(str)){
            String regEx = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            if(m.matches()){
                checkResult = true;
            }
        }
        return checkResult;
    }

    /*
     * @Description: 验证字母和数字，必须是6-18位长度
     * @MethodName: checkNumChar
     * @param:  [str]
     * @return: boolean
     * @Author: wujiangbo
     * @Date: 2020/7/1 0001 13:23
     */
    public static boolean checkNumChar(String str){
        boolean checkResult = false;
        if(!Tool.isBlank(str)){
            String regEx = "^[0-9A-Za-z]{6,18}$";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            if(m.matches()){
                checkResult = true;
            }
        }
        return checkResult;
    }

    /*
     * @Description: 验证手机号
     * @MethodName: checkPhone
     * @param:  [phone]
     * @return: boolean
     * @Author: wujiangbo
     * @Date: 2020/6/30 0030 15:55
     */ 
    public static boolean checkPhone(String phone){
        boolean checkResult = false;
        if(!Tool.isBlank(phone)){
            if(phone.length() == 11){
                String regEx = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0, 5-9]))\\d{8}$";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(phone);
                if(m.matches()){
                    checkResult = true;
                }
            }
        }
        return checkResult;
    }

    /*
     * @Description: 验证邮箱格式
     * @MethodName: checkEmail
     * @param:  [email]
     * @return: boolean
     * @Author: wujiangbo
     * @Date: 2020/6/30 0030 15:50
     */
    public static boolean checkEmail(String email){
        boolean checkResult = false;
        if(!Tool.isBlank(email)){
            String regEx = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(email);
            if(m.matches()){
                checkResult = true;
            }
        }
        return checkResult;
    }

    // 获取汉字的拼音
    public static String toHanyuPinyin(String ChineseLanguage) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {// 如果字符不是中文,则不转换
                    hanyupinyin += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }

    /**
     * @description: 获取浏览器信息
     * @author: wujiangbo(QQ:1134135987)
     * @date: 2020-09-18 13:21:42
     * @param: request
     * @return: java.lang.String
     */
    public static String getBrowserInfo(HttpServletRequest request) {
        String returnString = "";
        // 获取浏览器信息
        String ua = request.getHeader("User-Agent");
        try {
            // 转成UserAgent对象
            UserAgent userAgent = UserAgent.parseUserAgentString(ua);
            // 获取浏览器信息
            Browser browser = userAgent.getBrowser();
            // 获取系统信息
            OperatingSystem os = userAgent.getOperatingSystem();
            // 系统名称
            String system = os.getName();
            // 浏览器名称
            String browserName = browser.getName();
            returnString = browserName;
        } catch (Exception e) {
            log.error("获取[浏览器信息]时发生异常:{}", e.getLocalizedMessage());
            return returnString;
        }
        return returnString;
    }

    /*
     * @Description: 获取49位长度的UUID(当前时间 + UUID)
     *
     * @MethodName: getUUID49
     *
     * @param: []
     *
     * @return: java.lang.String
     *
     * @Author: wujiangbo
     *
     * @Date: 2020/3/1 13:24
     */
    public static String getUUID49() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String nowStr = now.format(format);
        return nowStr + UUID.randomUUID().toString().replaceAll("-", "");
    }

    /*
     * @Description: 获取订单号
     *
     * @MethodName: getOrderNum
     *
     * @param: []
     *
     * @return: java.lang.String
     *
     * @Author: wujiangbo
     *
     * @Date: 2020/3/5 16:22
     */
    public synchronized static String getOrderNum(int num) {
        return getRandomNum(num);
    }

    //流程申请编号
    public synchronized static String getFlwCode(int num) {
        return getRandomNum(num);
    }

    //审核编码
    public synchronized static String getCheckCode(int num) {
        return getRandomNum(num);
    }

    /** 
     * @Description: 获取指定位数的随机整数
     * @MethodName: getRandomNum
     * @param:  [num]
     * @return: java.lang.String
     * @Author: wujiangbo(QQ:1134135987)
     * @Date: 2020/07/22 13:16:27
     */ 
    public synchronized static String getRandomNum(int num) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= num; i++) {
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }

    /**
     * @description: 获取指定串中随机指定位数的字符串
     * @author: wujiangbo(QQ:1134135987)
     * @date: 2020-09-21 15:34:44
     * @param: num
     * @return: java.lang.String
     */
    public synchronized static String getRandomChar(int num) {
        //先定义取值范围
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer value = new StringBuffer();
        for (int i = 0; i < num; i++) {
            value.append(chars.charAt((int)(Math.random() * chars.length())));
        }
        return value.toString();
    }

    /*
     * @Description: 随机获取指定位数的数字
     * @MethodName: getCode
     * @param:  [num]
     * @return: java.lang.String
     * @Author: wujiangbo
     * @Date: 2020/6/30 0030 14:54
     */
    public synchronized static String getCode(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= length; i++) {
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }

    public synchronized static String getCode123(int length) {
        StringBuffer sb = new StringBuffer();
        try{
            SecureRandom number = SecureRandom.getInstanceStrong();
            for (int i = 1; i <= length; i++) {
                sb.append(number.nextInt(9));
            }
        }catch(NoSuchAlgorithmException e){
            log.error("获取随机数发生异常：{}", e.getMessage());
        }
        return sb.toString();
    }

    /*
     * @Description: 获取主键ID，与业务无关
     * @MethodName: getPrimaryKey
     * @param: []
     * @return: java.lang.String
     * @Author: wujiangbo
     * @Date: 2020/3/1 13:16
     */
    public synchronized static String getPrimaryKey() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime now = LocalDateTime.now();
        String nowStr = now.format(format);
        return nowStr + getRandomString(1, 9, 4);
    }

    /*
     * @Description: 获取当前时间字符串(yyyy-MM-dd HH:mm:ss)
     * @MethodName: getCurrentTimeString
     * @param:  []
     * @return: java.lang.String
     * @Author: wujiangbo
     * @Date: 2020/5/20 15:43
     */
    public static String getCurrentTimeString() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /*
     * @Description: 获取当前时间字符串(yyyyMMddHHmmss)
     * @MethodName: getCurrentTimeStr
     * @param:  []
     * @return: java.lang.String
     * @Author: wujiangbo
     * @Date: 2020/5/25 14:58
     */
    public static String getCurrentTimeStr() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }

    /*
     * @Description: 获取指定长度的随机数字串(可指定起始值)
     * @MethodName: getrandString
     * @param:  [min, max, length]
     * @return: java.lang.String
     * @Author: wujiangbo
     * @Date: 2020/5/20 9:05
     */
    public synchronized static String getRandomString(int min, int max, int length) {
        Random rand = new Random();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            result.append(String.valueOf(rand.nextInt(max - min + 1) + min));
        }
        return result.toString();
    }

    /*
     * @Description: 根据MultipartFile文件获取图片Base64数据
     * @MethodName: MutipartFileToBase64
     * @param: [file]
     * @return: java.lang.String
     * @Author: wujiangbo
     * @Date: 2020/3/3 12:34
     */
    public static String MutipartFileToBase64(MultipartFile file) {
        String base64EncoderImg = "";
        try {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            base64EncoderImg = Constant.BASE64_HEAD + base64Encoder.encode(file.getBytes());
        } catch (Exception e) {
            log.error("根据MultipartFile文件获取Base64数据，异常：{}", e);
        }
        return base64EncoderImg;
    }

    /*
     * @Description: 将图片Base64数据(包含头信息)字符串转成MultipartFile文件类型
     * @MethodName: base64ToMutipartFile
     * @param: [imgStr:Base64数据(包含头信息)字符串]
     * @return: org.springframework.web.multipart.MultipartFile
     * @Author: wujiangbo
     * @Date: 2020/3/1 13:15
     */
    public static MultipartFile base64ToMutipartFile(String imgStr) {
        try {
            // Base64数据头：data:image/png;base64,
            String[] baseStr = imgStr.split(",");
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = base64Decoder.decodeBuffer(baseStr[1]);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new BASE64DecodedMultipartFile(b, baseStr[0]);
        } catch (Exception e) {
            log.error("图片Base64串转MultipartFile发生异常:{}", e);
            return null;
        }
    }

    /*
     * @Description:根据身份证号获取性别、出生日期、年龄、省份等信息
     * @MethodName: getInfoByIdCard
     * @param: [idCard]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @Author: wujiangbo
     * @Date: 2020/3/1 13:13
     */
    public static Map<String, String> getInfoByIdCard(String idCard) {
        Map<String, String> map = new HashMap<String, String>();
        if (isBlank(idCard) || !IdcardUtil.isValidCard(idCard)) {
            return map;
        }
        int sex_code = IdcardUtil.getGenderByIdCard(idCard);// 性别（0：女；1：男；）
        map.put("sex", sex_code == 0 ? "女" : "男");
        map.put("birthday", IdcardUtil.getYearByIdCard(idCard) + "-" + IdcardUtil.getMonthByIdCard(idCard) + "-" + IdcardUtil.getDayByIdCard(idCard));
        map.put("age", String.valueOf(IdcardUtil.getAgeByIdCard(idCard)));
        map.put("province", IdcardUtil.getProvinceByIdCard(idCard));
        return map;
    }

    /**
     * @description: 判断字符串是否为空
     * @author: wujiangbo(QQ:1134135987)
     * @date: 2020-10-21 14:39:26
     * @param: cs
     * @return: boolean
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
