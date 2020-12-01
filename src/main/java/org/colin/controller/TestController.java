package org.colin.controller;

import io.swagger.annotations.Api;
import org.colin.model.dto.UserPhotoTemp;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 测试类
 * @ClassName: TestController
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/18 10:48
 * @Version: 1.1.0
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试模块")
public class TestController {

    @PostMapping("/hello")
    public String hello(@RequestBody UserPhotoTemp user) {
        System.out.println(user.toString());
        return user.getUserPhoto();
    }
}
