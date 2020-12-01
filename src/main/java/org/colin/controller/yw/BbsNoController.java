package org.colin.controller.yw;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.constants.Constant;
import org.colin.constants.DicTypeCode;
import org.colin.mapper.SysUserMapper;
import org.colin.model.dto.BbsInitVO;
import org.colin.model.entity.SysSuggestion;
import org.colin.model.entity.yw.BbsPostReply;
import org.colin.model.ro.*;
import org.colin.model.vo.req.*;
import org.colin.model.vo.resp.*;
import org.colin.service.*;
import org.colin.utils.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Description: 北方社区接口
 * @ClassName: BbsController
 * @Author: wujiangbo
 * @Date: 2020/6/22 0022 14:43
 * @Version: 1.1.0
 */
@RestController
@Api(tags = "北方社区接口")
@RequestMapping("/bbs")
@Slf4j
@EnableAsync
public class BbsNoController {

    @Resource
    private BbsNoticeService bbsNoticeService;

    @Resource
    private BbsPostService bbsPostService;

    @Resource
    private RedisService redisUtil;

    @Resource
    private UserService userService;

    @Resource
    private BbsUserService bbsUserService;

    @Resource
    private SysDicInfoService sysDicInfoService;

    @Resource
    private MessageService messageService;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysBbsAccessLogService sysBbsAccessLogService;

    @Resource
    private ThirdTool thirdTool;

    @Resource
    private Producer producer;

    @Resource
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

    //随机新增一个北方社区用户
    public BbsUser addRandomBbsUser(){
        BbsUser bbsUser = new BbsUser();
        String userName = Tool.getRandomChar(5) + "_" + Tool.getRandomNum(5);
        String userId = Tool.getPrimaryKey();
        bbsUser.setId(userId);
        bbsUser.setLoginName(Tool.getRandomNum(12));
        bbsUser.setPassword(Md5Util.string2MD5("123456"));//密码
        bbsUser.setUserName(userName);
        bbsUser.setCreateTime(new Date());
        bbsUser.setCreateUserId("fcf34b56-a7a2-4719-9236-867495e74c31");
        bbsUser.setEmail(Tool.getRandomChar(9) + "@qq.com");//随机生成邮箱
        bbsUser.setUserStatus("1");//账户状态(1.正常 2.锁定 )
        if(System.currentTimeMillis() % 2 == 0){
            bbsUser.setSex("1");//性别(1:男;2:女;)
        }else{
            bbsUser.setSex("2");//性别(1:男;2:女;)
        }
        bbsUser.setPoint(Integer.parseInt(Tool.getRandomString(1, 99999, 1)));//积分
        //新增测试用户
        bbsUserService.addBbsUser(bbsUser);
        String userPhotoStr = "";
        //查询所有测试头像
        List<String> userPhotoTempList = userService.selectAllUserPhotoTemp();
        if(CollectionUtils.isNotEmpty(userPhotoTempList)){
            userPhotoStr = userPhotoTempList.get(Integer.valueOf(Tool.getRandomString(0, userPhotoTempList.size() - 1, 1)));
        }
        //新增用户头像信息
        SysUserPhoto userPhoto = new SysUserPhoto();
        userPhoto.setId(Tool.getPrimaryKey());
        userPhoto.setUserId(userId);
        userPhoto.setPhoto(userPhotoStr);
        sysUserMapper.insertUserPhoto(userPhoto);
        log.info("随机生成北方社区用户[{}]成功", bbsUser.getUserName());
        return bbsUser;
    }

    //新增一个随机用户信息
//    public UserAddReqVO addRandomUser(){
//        List<String> roleIds = new ArrayList<String>();
//        roleIds.add("2d56198c-d14b-4d02-a625-7559815b62fb");//[普通用户]角色
//        UserAddReqVO vo = new UserAddReqVO();
//        String name = Tool.getRandomChar(5) + "_" + Tool.getRandomNum(5);
//        vo.setUsername(name);
//        vo.setMilitaryService("0");//是否服军役(0:否;1:是;)
//        vo.setGraduationSchool("武汉真牛牛大学");
//        vo.setIdentity("1001");//1001:党员;1002:预备党员;1005:群众
//        vo.setBloodType("1002");//1002:A;1003:B;1004:O
//        vo.setHeight("170");//身高
//        vo.setWeight("65");//体重
//        vo.setMajor("1001");//1001、1002、1004、1007、1008
//        vo.setMarriage("1002");//1001:已婚;1002:未婚
//        vo.setDeptId("202006301429312201997");//[北方社区]部门
//        if(System.currentTimeMillis() % 2 == 0){
//            vo.setSex("1");//性别(1:男;2:女;)
//        }else{
//            vo.setSex("2");//性别(1:男;2:女;)
//        }
//        vo.setPhone("170" + Tool.getRandomNum(8));
//        vo.setEducation("107");//本科107;大专105
//        vo.setPassword("123456");
//        vo.setCreateWhere(1);
//        vo.setEmail(Tool.getRandomNum(12) + "@qq.com");
//        vo.setRealName(name);
//        vo.setNickName(name);
//        vo.setStatus(1);
//        vo.setRoleIds(roleIds);
//        vo.setWagesNumber("001" + Tool.getRandomNum(4) + Tool.getRandomNum(4) + Tool.getRandomNum(4));
//        vo.setSocialNumber("002" + Tool.getRandomNum(4) + Tool.getRandomNum(4) + Tool.getRandomNum(4));
//        vo.setProvidentFundNumber("003" + Tool.getRandomNum(4) + Tool.getRandomNum(4) + Tool.getRandomNum(4));
//        vo.setNation("101");//汉族
//        vo.setCountry("101");//中国
//        vo.setHomeAddress("湖北省武汉市洪山区关山大道关谷新世界小区15栋2单元" + Tool.getRandomNum(6) + "号");
//        vo.setLiveAddress("湖北省武汉市洪山区关山大道关谷新世界小区15栋2单元" + Tool.getRandomNum(6) + "号");
//        vo.setIdNumber(Tool.getRandomNum(18));
//        vo.setQq(Tool.getRandomNum(10));
//        vo.setWebchat(Tool.getRandomNum(11));
//        vo.setMsn(Tool.getRandomNum(13));
//        String userPhoto = "";
//        //查询所有测试头像
//        List<String> userPhotoTempList = userService.selectAllUserPhotoTemp();
//        if(CollectionUtils.isNotEmpty(userPhotoTempList)){
//            userPhoto = userPhotoTempList.get(Integer.valueOf(Tool.getRandomString(0, userPhotoTempList.size() - 1, 1)));
//        }
//        vo.setUserPhotoTemp(userPhoto);
//        vo.setId(Tool.getPrimaryKey());
//        vo.setPoint(Tool.getRandomString(1, 99999, 1));//积分
//        userService.addUser(vo, "fcf34b56-a7a2-4719-9236-867495e74c31");
//        log.info("生成测试用户[{}]成功", name);
//        return vo;
//    }

    @PostMapping("/insertSuggestion")
    @ApiOperation(value = "新增意见反馈操作")
    @LogAnnotation(title = "北方社区", action = "提交意见反馈信息")
    public DataResult insertSuggestion(@RequestBody SysSuggestion obj) {
        obj.setUpdateUserId(addRandomBbsUser().getId());
        bbsPostService.addSuggestion(obj);
        return DataResult.success();
    }

    @PostMapping("/insertPost")
    @ApiOperation(value = "发帖操作")
    public DataResult insertPost(@RequestBody BbsPost post, HttpServletRequest request) {
        BbsUser user = addRandomBbsUser();
        String ip = IPUtils.getIpAddr(request);
        post.setPublishUserId(user.getId());
        bbsPostService.addPost(post, ip);
        thirdTool.sendNoticeEmail(user.getUserName(), "[北方社区]发布帖子", "有人发帖，快去审核吧", request);
        return DataResult.success();
    }

    @GetMapping("/getAllNotice")
    @ApiOperation(value = "查询所有通知通告信息")
    public DataResult getAllNotice() {
        DataResult<List<BbsNoticeVO>> result = DataResult.success();
        List<BbsNoticeVO> list = bbsNoticeService.getAllNotice();
        result.setData(list);
        return result;
    }

    @PostMapping("/insertBbsNotice")
    @ApiOperation(value = "新增社区通告")
    public DataResult insertBbsNotice(@RequestBody BbsNotice obj) {
        bbsNoticeService.insert(obj);
        return DataResult.success();
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
    public DataResult addPostGood(@PathVariable("id") String id) {
        String user_id = addRandomBbsUser().getId();
        bbsPostService.addPostGood(id, user_id);
        return DataResult.success();
    }

    @PostMapping("/addPostReply")
    @ApiOperation(value = "添加帖子评论信息")
    public DataResult addPostReply(@RequestBody BbsPostReply postReply, HttpServletRequest request) {
        BbsUser user = addRandomBbsUser();
        postReply.setPublishUserId(user.getId());
        String ip = IPUtils.getIpAddr(request);
        bbsPostService.addPostReply(postReply, ip);
        thirdTool.sendNoticeEmail(user.getUserName(), "[北方社区]发表评论", "有人发表评论，快去审核吧", request);
        return DataResult.success();
    }

    @GetMapping("/message/queryAll")
    @ApiOperation(value = "获取当前用户的所有消息记录（默认只查询前100条）")
    public DataResult<List<MessageVO>> query(HttpServletRequest request) {
//        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
//        List<MessageVO> msgList = messageService.getUserMsg(user_id, 2, "2");//target消息目标（1：平台；2：北方社区）
//        DataResult<List<MessageVO>> result = DataResult.success();
//        result.setData(msgList);
//        return result;
        return null;
    }

    @PostMapping("/message/setMessageStatus/{id}")
    @ApiOperation(value = "将当前用户的指定消息置为已读状态")
    public DataResult setMessageStatus(@PathVariable("id") String id) {
        messageService.setMessageStatus(id, 1);//0：未读；1：已读；
        return DataResult.success();
    }

    @GetMapping("/message/getUnReadMsgCount")
    @ApiOperation(value = "获取当前用户所有未读消息总数量")
    public DataResult getUnReadMsgCount(HttpServletRequest request) {
//        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
//        //状态（0：未读；1：已读；2：全部；）
//        List<MessageVO> unReadMsgCountList = messageService.getUserMsg(user_id, 0, "2");//target消息目标（1：平台；2：北方社区）
//        int unReadCount = unReadMsgCountList == null ? 0 :unReadMsgCountList.size();
//        return DataResult.success(unReadCount);
        return null;
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
        bbsPostService.deleteReplyByPkId(id);
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
