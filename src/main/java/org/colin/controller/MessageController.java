package org.colin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.colin.aop.annotation.LogAnnotation;
import org.colin.constants.Constant;
import org.colin.model.entity.SysSuggestion;
import org.colin.model.entity.SysUser;
import org.colin.model.ro.MessageRO;
import org.colin.model.vo.resp.MessageVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.rabbitmq.Sender;
import org.colin.service.MessageService;
import org.colin.service.UserService;
import org.colin.utils.DataResult;
import org.colin.utils.JwtTokenUtil;
import org.colin.utils.Tool;
import org.colin.websocket.BroadcastMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/sys")
@Api(tags = "系统模块-系统消息管理")
@RestController
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private Sender sender;

    @Autowired
    private UserService userService;

    @PostMapping("/suggestion/insertSuggestion")
    @ApiOperation(value = "新增意见反馈操作")
    @LogAnnotation(title = "平台", action = "提交意见反馈信息")
    public DataResult insertSuggestion(@RequestBody SysSuggestion obj, HttpServletRequest request) {
        String user_id = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        obj.setUpdateUserId(user_id);
        messageService.addSuggestion(obj);
        return DataResult.success();
    }

    @PostMapping("/message/broadcast/{broadcastContent}")
    @ApiOperation(value = "发送广播消息")
    @LogAnnotation(title = "系统消息管理", action = "发送广播消息")
    public DataResult broadcast(@PathVariable("broadcastContent") String broadcastContent, HttpServletRequest request) {
        //查询所有用户信息
        List<SysUser> userList = userService.getAllUserInfo();
        String currentUserName = JwtTokenUtil.getUserName(request.getHeader(Constant.ACCESS_TOKEN));
        if (userList != null && !userList.isEmpty()) {
            List<String> userIds = new ArrayList<>();
            userList.forEach(user -> {
                userIds.add(user.getId());
            });
            BroadcastMessage vo = new BroadcastMessage();
            vo.setBussinessKey(Tool.getPrimaryKey());
            vo.setDate(Tool.getCurrentTimeString());
            vo.setMessage(broadcastContent);
            vo.setUserIds(userIds);
            vo.setUsername(currentUserName);
            sender.send(vo);
        }
        return DataResult.success();
    }

    @PostMapping("/message/hasReadMsg")
    @ApiOperation(value = "将当前用户的所有系统消息置为已读状态")
    @LogAnnotation(title = "系统消息管理", action = "已读所有系统消息")
    public DataResult hasReadMsg(HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        messageService.hasReadMsg(userId, 1, "1");
        return DataResult.success();
    }

    @GetMapping("/message/query")
    @ApiOperation(value = "获取当前用户的所有消息记录")
    public DataResult<List<MessageVO>> query(HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        List<MessageVO> msgList = messageService.getUserMsg(userId, 2, "1");//target消息目标（1：平台；2：北方社区）
        DataResult<List<MessageVO>> result = DataResult.success();
        result.setData(msgList);
        return result;
    }

    @PostMapping("/message/add")
    @ApiOperation(value = "新增系统消息接口")
    @RequiresPermissions("sys:message:add")
    @LogAnnotation(title = "系统消息管理", action = "新增系统消息")
    public DataResult add(@RequestBody @Valid MessageRO ro, HttpServletRequest request) {
        String userId = JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        messageService.insertMessage(userId, ro);
        return DataResult.success();
    }

    @PostMapping("/message/list")
    @ApiOperation(value = "分页查询所有系统消息")
    @RequiresPermissions("sys:message:list")
    public DataResult<PageVO<MessageVO>> pageInfo(@RequestBody MessageRO ro) {
        PageVO<MessageVO> pageVO = messageService.pageInfo(ro);
        DataResult<PageVO<MessageVO>> result = DataResult.success();
        result.setData(pageVO);
        return result;
    }

    @DeleteMapping("/message")
    @ApiOperation(value = "删除系统消息")
    @LogAnnotation(title = "系统消息管理", action = "删除系统消息")
    @RequiresPermissions("sys:message:deleted")
    public DataResult deleted(@RequestBody List<String> ids) {
        messageService.deleteMessage(ids);
        return DataResult.success();
    }
}
