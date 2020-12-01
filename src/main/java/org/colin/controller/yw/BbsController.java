package org.colin.controller.yw;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.aop.annotation.LoginCheck;
import org.colin.code.BaseResponseCode;
import org.colin.constants.Constant;
import org.colin.constants.DicTypeCode;
import org.colin.exception.ServiceException;
import org.colin.mapper.SysUserMapper;
import org.colin.model.dto.BbsInitVO;
import org.colin.model.entity.SysSuggestion;
import org.colin.model.entity.SysUser;
import org.colin.model.entity.yw.BbsPostReply;
import org.colin.model.ro.BbsPost;
import org.colin.model.ro.BbsReply;
import org.colin.model.vo.req.*;
import org.colin.model.vo.resp.*;
import org.colin.service.*;
import org.colin.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
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
 * @Description: 北方社区接口
 * @ClassName: BbsController
 * @Author: wujiangbo
 * @Date: 2020/6/22 0022 14:43
 * @Version: 1.1.0
 */
//@RestController
//@Api(tags = "北方社区接口")
//@RequestMapping("/bbs")
//@Slf4j
//@EnableAsync
public class BbsController {

    @Autowired
    private BbsNoticeService bbsNoticeService;

    @Autowired
    private BbsPostService bbsPostService;

    @Autowired
    private RedisService redisUtil;

    @Autowired
    private UserService userService;

    @Resource
    private SysDicInfoService sysDicInfoService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Resource
    private SysBbsAccessLogService sysBbsAccessLogService;

    @Resource
    private ThirdTool thirdTool;

    @Autowired
    private Producer producer;

    @Autowired
    private RedisCheck redisCheck;

    @Value("${bbs.postMonth}")
    private String postMonth;

    @Value("${system.loginCodeTimeOut}")
    private Integer loginCodeTimeOut;

    //*********************北方社区-前端页面接口-开始*********************

    @GetMapping("/getCurrentTime")
    @ApiOperation(value = "BBS论坛欢迎页面调用的方法")
    public DataResult<BbsInitVO> getCurrentTime(HttpServletRequest request) {
        List<SysDicInfoVO> dicInfoList = sysDicInfoService.getAllOKDicByTypeCode(DicTypeCode.TYPE_CODE_1);//获取所有心灵鸡汤
        String xljt = "";
        if(CollectionUtils.isNotEmpty(dicInfoList)){
            xljt = dicInfoList.get(Integer.valueOf(Tool.getRandomString(0, dicInfoList.size()-1, 1))).getDicValue();
        }
        sysBbsAccessLogService.addLog(request);
        //封装返回数据
        BbsInitVO vo = new BbsInitVO();
        vo.setCurrentTime(Tool.getCurrentTimeString());
        vo.setXljt(xljt);
        DataResult<BbsInitVO> result = DataResult.success();
        result.setData(vo);
        return result;
    }

    @PostMapping("/uploadUserPhoto")
    @ApiOperation(value = "上传个人大头照")
    @LogAnnotation(title = "北方社区", action = "上传个人大头照")
    @LoginCheck
    public DataResult uploadUserPhoto(MultipartFile file, HttpServletRequest request) {
        //获取当前登录者ID
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        //限制：30天只能更新头像1次
        redisCheck.operFrequencyCheck(Constant.UPDATE_USER_PHOTO + user_id, user_id, 30, TimeUnit.DAYS, "每月只能修改一次头像");
        String userPhoto = Tool.MutipartFileToBase64(file);
        userService.updateUserPhotoByUserId(user_id, userPhoto);
        return DataResult.success();
    }

    @PutMapping("/updateUserPassword")
    @ApiOperation(value = "用户重置登录密码")
    public DataResult updateUserPassword(@RequestBody @Valid UpdatePasswordReqVO vo) {
        //限制：30天只能更新一次登录密码
        redisCheck.operFrequencyCheck(Constant.UPDATE_PWD_EMAIL_CODE + vo.getEmail(), vo.getEmail(), 30, TimeUnit.DAYS, "每月只能修改1次登录密码");
        //获取redis中的验证码
        String emailCode = (String) redisUtil.get(Constant.UPDATE_PWD_EMAIL_CODE + vo.getEmail());
        if (!emailCode.equalsIgnoreCase(vo.getEmailCode())) {
            throw new ServiceException(BaseResponseCode.REGISTER_CODE_ERROR);
        }
        userService.updateUserPasswordByEmail(vo.getEmail(), vo.getPassword());
        //重置密码成功后，将redis中的验证码删除
        redisUtil.set(Constant.UPDATE_PWD_EMAIL_CODE + vo.getEmail(), "", 1, TimeUnit.MILLISECONDS);
        return DataResult.success();
    }

    @ApiOperation(value = "北方社区-修改登录密码时发送邮件验证码")
    @PostMapping("/sendUpdatePwdEmail")
    public DataResult sendUpdatePwdEmail(@RequestBody @Valid UpdatePwdVO vo, HttpServletRequest request) {
        String key = Constant.UPDATE_PWD_EMAIL_CODE + vo.getEmail();
        //验证Redis中的验证码是否已失效，防止重复发送验证码
        String redisEmailCode = (String) redisUtil.get(key);
        if(redisEmailCode != null){
            throw new ServiceException(BaseResponseCode.REGISTER_EMAIL_CODE_ERROR);
        }
        //获取Redis中的图片验证码
        String redisCode = (String) redisUtil.get(Constant.PICTURE_VERIFICATION_NORTH + vo.getTime());
        //和前端提交过来的验证码进行对比
        if (!redisCode.equalsIgnoreCase(vo.getCode())) {
            throw new ServiceException(BaseResponseCode.PICTURE_CODE_ERROR);
        }
        //验证邮箱格式
        if(!Tool.checkEmail(vo.getEmail())){
            throw new ServiceException(BaseResponseCode.REGISTER_EMAIL_ERROR);
        }
        //验证邮箱是否已存在
        UserPageReqVO checkVO = new UserPageReqVO();
        checkVO.setEmail(vo.getEmail());
        List<SysUser> list = sysUserMapper.selectAll(checkVO);
        if(list.size() == 0){
            throw new ServiceException(BaseResponseCode.EMAIL_NOT_EXIST);
        }
        //发送邮件验证码操作
        String emailCode = Tool.getCode(6);//生成验证码
        thirdTool.sendResetPwdCode(emailCode, vo.getEmail());
        // 将验证码存入Redis（5分钟有效）
        redisUtil.set(key, emailCode, 5, TimeUnit.MINUTES);
        return DataResult.success();
    }

    @PutMapping("/updateUserInfoSelf")
    @ApiOperation(value = "用户更新自己个人信息")
    @LogAnnotation(title = "北方社区", action = "更新个人信息")
    @LoginCheck
    public DataResult updateUserInfoSelf(@RequestBody @Valid BbsUserUpdateReqVO vo, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        //限制：30天只能更新个人信息1次
        redisCheck.operFrequencyCheck(Constant.UPDATE_USER_INFO, user_id, 30, TimeUnit.DAYS, "每月只能修改1次个人信息");
        //验证手机号格式
        if(!Tool.checkPhone(vo.getPhone())){
            throw new ServiceException(BaseResponseCode.PHONE_ERROR);
        }
        vo.setId(user_id);
        userService.updateBbsUserInfoSelf(vo, user_id);
        return DataResult.success();
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录接口")
    public DataResult<LoginRespVO> login(@RequestBody @Valid LoginReqVO vo, HttpServletRequest request) {
        //获取Redis中的图片验证码
        String redisCode = (String) redisUtil.get(Constant.PICTURE_VERIFICATION + vo.getTime());
        //和前端提交过来的验证码进行对比
        if (Tool.isBlank(redisCode) || !redisCode.equalsIgnoreCase(vo.getCode())) {
            throw new ServiceException(BaseResponseCode.PICTURE_CODE_ERROR);
        }
        DataResult<LoginRespVO> result = DataResult.success();
        result.setData(userService.bbsLogin(vo));
        return result;
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "用户注册接口")
    public DataResult<LoginRespVO> register(@RequestBody @Valid RegisterUserReqVO vo, HttpServletRequest request) {
        //验证输入的两次密码是否一致
        if(!vo.getPassword().equals(vo.getCheckPassword())){
            throw new ServiceException(BaseResponseCode.REGISTER_PASSWORD_ERROR);
        }
        //获取Redis中的注册验证码
        String registerEmailCode = (String) redisUtil.get(Constant.REGISTER_EMAIL_CODE + vo.getEmail());
        if(registerEmailCode == null){
            throw new ServiceException(BaseResponseCode.REGISTER_CODE_ERROR);
        }
        //和前端提交过来的验证码进行对比
        if (!registerEmailCode.equalsIgnoreCase(vo.getEmailCode())) {
            throw new ServiceException(BaseResponseCode.REGISTER_CODE_ERROR);
        }
        //验证账号
        if(!Tool.checkNumChar(vo.getUsername())){
            throw new ServiceException(BaseResponseCode.REGISTER_USERNAME_STYLE_ERROR);
        }
        //验证密码
        if(!Tool.checkPassword(vo.getPassword())){
            throw new ServiceException(BaseResponseCode.REGISTER_PASSWORD_STYLE_ERROR);
        }
        //验证邮箱格式
        if(!Tool.checkEmail(vo.getEmail())){
            throw new ServiceException(BaseResponseCode.REGISTER_EMAIL_ERROR);
        }
        //验证邮箱是否已注册
        UserPageReqVO checkVO1 = new UserPageReqVO();
        checkVO1.setEmail(vo.getEmail());
        List<SysUser> list1 = sysUserMapper.selectAllByParam(checkVO1);
        if(list1 != null && list1.size() != 0){
            throw new ServiceException(BaseResponseCode.REGISTER_EXIST);
        }
        //验证登录账号是否已存在
        UserPageReqVO checkVO2 = new UserPageReqVO();
        checkVO2.setUsername(vo.getUsername());
        List<SysUser> list2 = sysUserMapper.selectAllByParam(checkVO2);
        if(list2 != null && list2.size() != 0){
            throw new ServiceException(BaseResponseCode.USERNAME_EXIST);
        }
        //设置访问终端类型
        vo.setCreateWhere(IPUtils.getAccessType(request));
        //进行注册操作
        userService.registerUser(vo, request);
        //注册成功后，将redis中的注册验证码删除
        redisUtil.set(Constant.REGISTER_EMAIL_CODE + vo.getEmail(), "", 1, TimeUnit.MILLISECONDS);
        return DataResult.success();
    }

    @ApiOperation(value = "北方社区-注册时发送邮件验证码")
    @PostMapping("/sendRegisterEmail")
    public DataResult sendRegisterEmail(@RequestBody @Valid SendEmailReqVO vo, HttpServletRequest request) {
        String key = Constant.REGISTER_EMAIL_CODE + vo.getEmail();
        //验证Redis中的验证码是否已失效，防止重复发送验证码
        String redisEmailCode = (String) redisUtil.get(key);
        if(redisEmailCode != null){
            throw new ServiceException(BaseResponseCode.REGISTER_EMAIL_CODE_ERROR);
        }
        //获取Redis中的图片验证码
        String redisCode = (String) redisUtil.get(Constant.PICTURE_VERIFICATION_NORTH + vo.getTime());
        //和前端提交过来的验证码进行对比
        if (!redisCode.equalsIgnoreCase(vo.getCode())) {
            throw new ServiceException(BaseResponseCode.PICTURE_CODE_ERROR);
        }
        //验证邮箱格式
        if(!Tool.checkEmail(vo.getEmail())){
            throw new ServiceException(BaseResponseCode.REGISTER_EMAIL_ERROR);
        }
        //验证邮箱是否已注册
        UserPageReqVO checkVO = new UserPageReqVO();
        checkVO.setEmail(vo.getEmail());
        List<SysUser> list = sysUserMapper.selectAll(checkVO);
        if(list.size() != 0){
            throw new ServiceException(BaseResponseCode.REGISTER_EXIST);
        }
        //发送邮件验证码操作
        String emailCode = Tool.getCode(6);//生成验证码
        thirdTool.sendRegisterCode(emailCode, vo.getEmail());
        // 将验证码存入Redis（5分钟有效）
        redisUtil.set(key, emailCode, 5, TimeUnit.MINUTES);
        return DataResult.success();
    }

    @ApiOperation(value = "北方社区-注册时获取图片验证码")
    @GetMapping("/getPictureVerification")
    public void captcha(HttpServletResponse response, @RequestParam(value = "time") String time) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        // 验证码存入Redis的key
        String captchaCodeKey = Constant.PICTURE_VERIFICATION_NORTH + time;
        // 存入Redis（单位分钟）
        redisUtil.set(captchaCodeKey, text, loginCodeTimeOut, TimeUnit.MINUTES);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "BBS用户退出系统接口")
    @LogAnnotation(title = "北方社区", action = "退出北方社区")
    @LoginCheck
    public DataResult logout(HttpServletRequest request) {
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String refreshToken = request.getHeader(Constant.REFRESH_TOKEN);
        userService.logout(accessToken, refreshToken);
        return DataResult.success();
    }

    @GetMapping("/userDetailInfo")
    @ApiOperation(value = "查询用户详情接口")
    @LoginCheck
    public DataResult<BbsUserVO> userDetailInfo(HttpServletRequest request) {
        String id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        DataResult<BbsUserVO> result = DataResult.success();
        result.setData(userService.getBbsUserInfoByPkId(id));
        return result;
    }

    @PostMapping("/insertSuggestion")
    @ApiOperation(value = "新增意见反馈操作")
    @LogAnnotation(title = "北方社区", action = "提交意见反馈信息")
    @LoginCheck
    public DataResult insertSuggestion(@RequestBody SysSuggestion obj, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        obj.setUpdateUserId(user_id);
        bbsPostService.addSuggestion(obj);
        return DataResult.success();
    }

    @PostMapping("/insertPost")
    @ApiOperation(value = "发帖操作")
    @LogAnnotation(title = "北方社区", action = "发帖操作")
    @LoginCheck
    public DataResult insertPost(@RequestBody BbsPost post, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        post.setPublishUserId(user_id);
        bbsPostService.addPost(post, "");
        return DataResult.success();
    }

    @GetMapping("/getAllNotice")
    @ApiOperation(value = "查询所有通知信息(只查前50条记录)")
    public DataResult getAllNotice() {
        DataResult<List<BbsNoticeVO>> result = DataResult.success();
        List<BbsNoticeVO> list = bbsNoticeService.getAllNotice();
        result.setData(list);
        return result;
    }

    @GetMapping("/getAllPost/{postType}")
    @ApiOperation(value = "查询所有帖子信息(只查距离当前n个月的所有记录)")
    public DataResult getAllPost(@PathVariable("postType") String postType) {
        DataResult<List<BbsPostVO>> result = DataResult.success();
        List<BbsPostVO> list = bbsPostService.selectAllPost(postType, postMonth);
        result.setData(list);
        return result;
    }

    @GetMapping("/getNoticeById/{id}")
    @ApiOperation(value = "根据主键ID查询通告详情")
    public DataResult getNoticeById(@PathVariable("id") String id) {
        DataResult<BbsNoticeVO> result = DataResult.success();
        BbsNoticeVO notice = bbsNoticeService.getNoticeById(id);
        result.setData(notice);
        return result;
    }

    @GetMapping("/getPostById/{id}")
    @ApiOperation(value = "根据主键ID查询帖子详情及所有评论")
    public DataResult getPostById(@PathVariable("id") String id) {
        DataResult<BbsPostReplyVOResp> result = DataResult.success();
        BbsPostReplyVOResp notice = bbsPostService.selectPostById(id);
        result.setData(notice);
        return result;
    }

    @PostMapping("/addPostGood/{id}")
    @ApiOperation(value = "给帖子点赞")
    @LogAnnotation(title = "北方社区", action = "给帖子点赞")
    @LoginCheck
    public DataResult addPostGood(@PathVariable("id") String id, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        bbsPostService.addPostGood(id, user_id);
        return DataResult.success();
    }

    @PostMapping("/addPostReply")
    @ApiOperation(value = "添加帖子评论信息")
    @LogAnnotation(title = "北方社区", action = "给帖子评论")
    @LoginCheck
    public DataResult addPostReply(@RequestBody BbsPostReply postReply, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        postReply.setPublishUserId(user_id);
        bbsPostService.addPostReply(postReply, "");
        return DataResult.success();
    }

    @GetMapping("/message/queryAll")
    @ApiOperation(value = "获取当前用户的所有消息记录（默认只查询前100条）")
    @LoginCheck
    public DataResult<List<MessageVO>> query(HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        List<MessageVO> msgList = messageService.getUserMsg(user_id, 2, "2");//target消息目标（1：平台；2：北方社区）
        DataResult<List<MessageVO>> result = DataResult.success();
        result.setData(msgList);
        return result;
    }

    @PostMapping("/message/setMessageStatus/{id}")
    @ApiOperation(value = "将当前用户的指定消息置为已读状态")
    @LoginCheck
    public DataResult setMessageStatus(@PathVariable("id") String id) {
        messageService.setMessageStatus(id, 1);//0：未读；1：已读；
        return DataResult.success();
    }

    @GetMapping("/message/getUnReadMsgCount")
    @ApiOperation(value = "获取当前用户所有未读消息总数量")
    @LoginCheck
    public DataResult getUnReadMsgCount(HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        //状态（0：未读；1：已读；2：全部；）
        List<MessageVO> unReadMsgCountList = messageService.getUserMsg(user_id, 0, "2");//target消息目标（1：平台；2：北方社区）
        int unReadCount = unReadMsgCountList == null ? 0 :unReadMsgCountList.size();
        return DataResult.success(unReadCount);
    }
    //*********************北方社区-前端页面接口-结束*********************

    //*********************北方社区-后台管理接口-开始*********************
    @PostMapping("/reply/list")
    @ApiOperation(value = "分页获取所有评论信息")
    @LogAnnotation(title = "评论管理", action = "分页获取所有评论信息")
    @RequiresPermissions("bbs:post:list")
    public DataResult<PageVO<ReplyVO>> replyPageInfo(@RequestBody BbsReplyPageReqVO vo) {
        DataResult<PageVO<ReplyVO>> result = DataResult.success();
        result.setData(bbsPostService.replyPageInfo(vo));
        return result;
    }
    @PostMapping("/post/list")
    @ApiOperation(value = "分页获取所有帖子信息")
    @LogAnnotation(title = "帖子管理", action = "分页获取所有帖子信息")
    @RequiresPermissions("bbs:post:list")
    public DataResult<PageVO<BbsPostVO>> pageInfo(@RequestBody BbsPostPageReqVO vo) {
        DataResult<PageVO<BbsPostVO>> result = DataResult.success();
        result.setData(bbsPostService.pageInfo(vo));
        return result;
    }

    @DeleteMapping("/deletePost/{id}")
    @ApiOperation(value = "删除帖子信息")
    @LogAnnotation(title = "帖子管理", action = "删除帖子信息")
    @RequiresPermissions("bbs:post:deletePost")
    public DataResult deletePost(@PathVariable("id") String id) {
        bbsPostService.deleteByPkId(id);
        return DataResult.success();
    }

    @DeleteMapping("/deleteReply/{id}")
    @ApiOperation(value = "删除评论信息")
    @LogAnnotation(title = "评论管理", action = "删除评论信息")
    @RequiresPermissions("bbs:post:deleteReply")
    public DataResult deleteReply(@PathVariable("id") String id) {
        bbsPostService.deleteByPkId(id);
        return DataResult.success();
    }

    @PutMapping("/post/updatePost")
    @ApiOperation(value = "更新帖子信息")
    @LogAnnotation(title = "帖子管理", action = "更新帖子信息")
    @RequiresPermissions("bbs:post:updatePost")
    public DataResult updatePost(@RequestBody BbsPost post, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        post.setUpdateUserId(user_id);
        bbsPostService.updatePost(post);
        return DataResult.success();
    }

    @PutMapping("/post/updateReply")
    @ApiOperation(value = "更新评论信息")
    @LogAnnotation(title = "评论管理", action = "更新评论信息")
    @RequiresPermissions("bbs:post:updateReply")
    public DataResult updateReply(@RequestBody BbsReply obj, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        obj.setUpdateUserId(user_id);
        bbsPostService.updateReply(obj);
        return DataResult.success();
    }
    //*********************北方社区-后台管理接口-结束*********************
}
