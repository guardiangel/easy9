package org.colin.controller.login;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.CheckLicense;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.aop.annotation.LoginCheck;
import org.colin.captcha.ArithmeticCaptcha;
import org.colin.code.BaseResponseCode;
import org.colin.constants.Constant;
import org.colin.exception.ServiceException;
import org.colin.face.FaceMatch;
import org.colin.model.entity.DeptUserTree;
import org.colin.model.entity.SysUser;
import org.colin.model.entity.UserTree;
import org.colin.model.vo.req.*;
import org.colin.model.vo.resp.LoginRespVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.UserOwnRoleRespVO;
import org.colin.service.RedisService;
import org.colin.service.UserService;
import org.colin.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: UserController
 * @ClassName: UserController
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/16 21:09
 * @Version: 1.1.0
 */
@RestController
@Api(tags = "组织模块-用户管理")
@RequestMapping("/sys")
@Slf4j
public class UserController {

    @Autowired
    private Producer producer;

    @Autowired
    private ArithmeticCaptcha captcha;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisUtil;

    @Autowired
    private RedisCheck redisCheck;

    @Value("${system.loginCodeTimeOut}")
    private Integer loginCodeTimeOut;

    @Value("${face.system_img}")
    private String system_img;

    @Value("${system.authorization_expired_date}")
    private String authorization_expired_date;

    @PutMapping("/updateUserPassword")
    @ApiOperation(value = "用户重置登录密码")
    public DataResult updateUserPassword(@RequestBody @Valid UpdatePasswordVO vo, HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        SysUser currentUser = userService.detailInfo(userId);
        String salt = currentUser.getSalt();
        String oldPassword = PasswordUtils.encode(vo.getOldPassword(), salt);
        if(!currentUser.getPassword().equals(oldPassword)){
            throw new ServiceException(BaseResponseCode.OLD_PASSWORD_ERROR);
        } else if (!vo.getNewPassword().equals(vo.getNewPassword2())){
            throw new ServiceException(BaseResponseCode.REGISTER_PASSWORD_ERROR);
        }
        userService.updateUserPasswordByUserId(userId, vo.getNewPassword());
        return DataResult.success();
    }

    @GetMapping("/user/deptUserTree")
    @ApiOperation(value = "获取所有部门用户树型结构数据")
    public DataResult<List<DeptUserTree>> getDeptUserTree() {
        DataResult<List<DeptUserTree>> result = DataResult.success();
        result.setData(userService.getAllDeptUserTree());
        return result;
    }

    @GetMapping("/user/userTree")
    @ApiOperation(value = "赋予角色-获取所有角色接口")
    public DataResult<List<UserTree>> userTree() {
        DataResult<List<UserTree>> result = DataResult.success();
        result.setData(userService.selectAllByTree());
        return result;
    }

    @GetMapping("/user/token")
    @ApiOperation(value = "用户刷新token接口")
    public DataResult<String> refreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader(Constant.REFRESH_TOKEN);
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        DataResult<String> result = DataResult.success();
        result.setData(userService.refreshToken(refreshToken, accessToken));
        return result;
    }

    @PutMapping("/user/roles/{userId}")
    @ApiOperation(value = "用户管理-用户赋予角色")
    @LogAnnotation(title = "用户管理", action = "用户管理-用户赋予角色")
    @RequiresPermissions("sys:user:update:role")
    public DataResult<UserOwnRoleRespVO> setUserOwnRole(@PathVariable("userId") String userId, @RequestBody List<String> roleIds) {
        DataResult result = DataResult.success();
        userService.setUserOwnRole(userId, roleIds);
        return result;
    }

    @GetMapping("/user/roles/{userId}")
    @ApiOperation(value = "赋予角色-获取所有角色接口")
    @RequiresPermissions("sys:user:role:detail")
    public DataResult<UserOwnRoleRespVO> getUserOwnRole(@PathVariable("userId") String userId) {
        DataResult<UserOwnRoleRespVO> result = DataResult.success();
        result.setData(userService.getUserOwnRole(userId));
        return result;
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户接口")
    @RequiresPermissions("sys:user:add")
    @LogAnnotation(title = "用户管理", action = "新增用户")
    public DataResult addUser(@RequestBody @Valid UserAddReqVO vo, HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.addUser(vo, userId);
        return DataResult.success();
    }

    @PostMapping("/uploadUserPhoto")
    @ApiOperation(value = "上传大头照")
    @LogAnnotation(title = "用户管理", action = "上传大头照")
    public DataResult uploadUserPhoto(MultipartFile file, HttpServletRequest request) {
        //获取当前登录者ID
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        String userPhoto = Tool.MutipartFileToBase64(file);
        userService.updateUserPhotoByUserId(userId, userPhoto);
        return DataResult.success();
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "删除用户接口")
    @LogAnnotation(title = "用户管理", action = "删除用户")
    @RequiresPermissions("sys:user:deleted")
    public DataResult deletedUser(@RequestBody @ApiParam(value = "用户id集合") List<String> userIds, HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.deletedUsers(userIds, userId);
        return DataResult.success();
    }

    @PostMapping("/users")
    @ApiOperation(value = "分页获取用户列表接口")
    @RequiresPermissions("sys:user:list")
    @LogAnnotation(title = "用户管理", action = "分页获取用户列表")
    public DataResult<PageVO<SysUser>> pageInfo(@RequestBody UserPageReqVO vo) {
        DataResult<PageVO<SysUser>> result = DataResult.success();
        result.setData(userService.pageInfo(vo));
        return result;
    }

    @GetMapping("/user")
    @ApiOperation(value = "查询当前登录者详情信息接口")
    public DataResult<SysUser> youSelfInfo(HttpServletRequest request) {
        String id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        DataResult<SysUser> result = DataResult.success();
        result.setData(userService.detailInfo(id));
        return result;
    }

    @GetMapping("/getUserInfoByUserId/{userId}")
    @ApiOperation(value = "查询当前登录者详情信息接口")
    @LoginCheck
    public DataResult<SysUser> getUserInfoByUserId(@PathVariable("userId") String userId) {
        DataResult<SysUser> result = DataResult.success();
        result.setData(userService.detailInfo(userId));
        return result;
    }

    @PutMapping("/user")
    @ApiOperation(value = "更新用户信息接口")
    @LogAnnotation(title = "用户管理", action = "更新用户信息")
    @RequiresPermissions("sys:user:update")
    public DataResult updateUserInfo(@RequestBody @Valid UserUpdateReqVO vo, HttpServletRequest request) {
        log.info("更新用户信息：UserUpdateReqVO={}", vo);
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        userService.updateUserInfo(vo, userId);
        return DataResult.success();
    }

    @PutMapping("/user/updateUserInfoSelf")
    @ApiOperation(value = "用户更新自己个人信息")
    @LogAnnotation(title = "用户管理", action = "用户更新自己个人信息")
    public DataResult updateUserInfoSelf(@RequestBody @Valid UserUpdateReqVO vo, HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        vo.setId(userId);
        userService.updateUserInfoSelf(vo, userId);
        return DataResult.success();
    }

    @GetMapping("/user/logout")
    @LogAnnotation(title = "用户管理", action = "退出")
    @ApiOperation(value = "退出系统接口")
    public DataResult logout(HttpServletRequest request) {
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String refreshToken = request.getHeader(Constant.REFRESH_TOKEN);
        userService.logout(accessToken, refreshToken);
        return DataResult.success();
    }

    @PostMapping(value = "/user/login")
    @ApiOperation(value = "用户账号密码登录接口")
    @CheckLicense
    public DataResult<LoginRespVO> login(@RequestBody @Valid LoginReqVO vo, HttpServletRequest request) {
        if(Tool.dateExpired(authorization_expired_date)){
            throw new ServiceException(BaseResponseCode.AUTHORIZATION_EXPIRED_ERROR);
        }
        //获取Redis中的图片验证码
        String redisCode = (String) redisUtil.get(Constant.PICTURE_VERIFICATION + vo.getTime());
        //和前端提交过来的验证码进行对比
        if (redisCode == null || !redisCode.equalsIgnoreCase(vo.getCode())) {
            throw new ServiceException(BaseResponseCode.PICTURE_CODE_ERROR);
        }
        DataResult<LoginRespVO> result = DataResult.success();
        result.setData(userService.login(vo, request));
        return result;
    }

    @PostMapping(value = "/user/faceLogin")
    @ApiOperation(value = "用户人脸登录接口")
    @CheckLicense
    public DataResult faceLogin(@RequestBody LoginReqVO vo, HttpServletRequest request) {
        DataResult result = null;
        /** 人脸照片 */
        String loginFaceImage = vo.getCode();
        double compareResult = FaceMatch.matchLoginImage(loginFaceImage, system_img);//对比人脸
        log.info("人脸比对结果={}", compareResult);
        if(compareResult < 80){
            result = DataResult.getResult(BaseResponseCode.FACE_LOGIN_FAILED);
        }else{
            result = DataResult.success();
            vo.setUsername("colin");
            vo.setPassword("123456");
            vo.setType("1");
            result.setData(userService.login(vo, request));
        }
        return result;
    }

    @ApiOperation(value = "算术图片验证码")
    @GetMapping(value = "/getPictureVerification2")
    @ApiImplicitParams({@ApiImplicitParam(name = "time", value = "时间戳", required = true, paramType = "query", dataType = "String")})
    public void getPictureVerification(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "time") String time) {
        log.info("time={}", time);
        captcha.createImage(request, response, time);
        String redisCode = (String) redisUtil.get(Constant.PICTURE_VERIFICATION + time);
        log.info("redisCode={}", redisCode);
    }

    @ApiOperation(value = "图片验证码")
    @GetMapping("/getPictureVerification")
    @ApiImplicitParams({@ApiImplicitParam(name = "time", value = "时间戳", required = true, paramType = "query", dataType = "String")})
    public void captcha(HttpServletResponse response, @RequestParam(value = "time") String time) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        // 验证码存入Redis的key
        String captchaCodeKey = Constant.PICTURE_VERIFICATION + time;
        // 存入Redis（单位分钟）
        redisUtil.set(captchaCodeKey, text, loginCodeTimeOut, TimeUnit.MINUTES);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }
}
