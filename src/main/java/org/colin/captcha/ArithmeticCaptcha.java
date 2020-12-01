package org.colin.captcha;

import lombok.extern.slf4j.Slf4j;
import org.colin.constants.Constant;
import org.colin.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * @Description: ArithmeticCaptcha
 * @ClassName: ArithmeticCaptcha
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 13:41
 * @Version: 1.1.0
 */
@Component
@Slf4j
public class ArithmeticCaptcha {

    @Autowired
    private RedisService redisUtil;

    @Value("${system.loginCodeTimeOut}")
    private Integer loginCodeTimeOut;

    private int imageWidth = 70;

    private int imageHigh = 26;

    public void createImage(HttpServletRequest request, HttpServletResponse response, String time) {
        log.info("登录图片验证码存Redis有效时间：{}分钟", loginCodeTimeOut);
        try {
            BufferedImage image = new BufferedImage(imageWidth, imageHigh, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            createBackground(g);
            // 生成min至max质检的随机整数
            int min = 1;
            int max = 9;
            int i1 = new Random().nextInt(max - min + 1) + min;
            int i2 = new Random().nextInt(max - min + 1) + min;
            createCharacter(g, i1, i2);
            // 验证码存入Redis的key
            String captchaCodeKey = Constant.PICTURE_VERIFICATION + time;
            // 存入Redis（单位分钟）
            redisUtil.set(captchaCodeKey, String.valueOf(i1 + i2), loginCodeTimeOut, TimeUnit.MINUTES);
            g.dispose();
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.close();
        } catch (IOException e) {
            log.error("生成图片验证码时发生异常：{}", e);
        }
    }

    private void createBackground(Graphics g) {
        // 填充背景
        g.setColor(getRandColor(220, 250));
        g.fillRect(0, 0, imageWidth, imageHigh);
        // 加入干扰线条
        for (int i = 0; i < 3; i++) {
            g.setColor(getRandColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(imageWidth);
            int y = random.nextInt(imageHigh);
            int x1 = random.nextInt(imageWidth);
            int y1 = random.nextInt(imageHigh);
            g.drawLine(x, y, x1, y1);
        }
    }

    // 颜色
    private Color getRandColor(int fc, int bc) {
        int f = fc;
        int b = bc;
        Random random = new Random();
        if (f > 255) {
            f = 255;
        }
        if (b > 255) {
            b = 255;
        }
        return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f), f + random.nextInt(b - f));
    }

    private void createCharacter(Graphics g, int i1, int i2) {
        String[] fontTypes = {"\u5b8b\u4f53", "\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66"};
        Random random = new Random();
        String r = i1 + "+" + i2 + "=?";
        g.setColor(new Color(50 + random.nextInt(100), 50 + random.nextInt(100), 50 + random.nextInt(100)));
        g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], Font.BOLD, 18));
        g.drawString(r, 1, 20);
    }
}
