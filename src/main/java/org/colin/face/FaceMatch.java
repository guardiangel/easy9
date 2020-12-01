package org.colin.face;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.colin.face.util.*;
import sun.misc.BASE64Decoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 人脸对比
 * @ClassName: FaceMatch
 * @Author: wujiangbo
 * @Date: 2020/9/3 0003 13:26
 * @Version: 1.1.0
 */
@Slf4j
public class FaceMatch {

    public static void main(String[] args) {
        String image1 = Base64Util.encode(FileUtil.readFileByBytes("D:/work/Easy9/img1.jpg"));
        String image2 = Base64Util.encode(FileUtil.readFileByBytes("D:/work/Easy9/img2.jpg"));
        System.out.println("人脸比对结果=" + match(image1, image2));
    }

    //对比登录者人脸和允许登录用户的人脸
    public static double matchLoginImage(String loginFaceImage, String systemFaceImage) {
        BASE64Decoder beDecoder = new BASE64Decoder();
        // 转换为字节数组
        byte[] bytes1 = null;
        try
        {
            bytes1 = beDecoder.decodeBuffer(loginFaceImage);
        } catch (Exception e)
        {
            log.error("人脸照转换为字节数组异常:{}", e.getLocalizedMessage());
        }
        return match(Base64Util.encode(bytes1), systemFaceImage);
    }

    //对比两张人脸照，获取相似度值
    public static double match(String image1, String image2) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        double score = 0.0;
        try {
//            byte[] bytes1 = FileUtil.readFileByBytes("D:/liuming.jpg");
//            byte[] bytes2 = FileUtil.readFileByBytes("D:/aa.jpg");
//            String image1 = Base64Util.encode(bytes1);
//            String image2 = Base64Util.encode(bytes2);
            List<Map<String, Object>> images = new ArrayList<Map<String,Object>>();
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NONE");// 活体检测控制
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NONE");
            images.add(map1);
            images.add(map2);
            String param = GsonUtils.toJson(images);
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();
            //调用接口
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            log.info("人脸对比结果：" + result);// score：float类型，人脸相似度得分，推荐阈值80分
            //把服务器的数据转换为JSON
            JSONObject newresult = JSONObject.fromObject(result);
            //获取状态码
            int number = (Integer) newresult.get("error_code");
            //获取二级result对象
            JSONObject eventInfoData = (JSONObject)newresult.get("result");
            //判断状态码
            if (number == 0) {
                //获取相识度
                score = eventInfoData.getDouble("score");
            }else{
                log.error("调用百度人脸识别比对API接口异常，异常信息：{}", result);
            }
        } catch (Exception e) {
            score = -1;
        }
        return score;
    }
}
