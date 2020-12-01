package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import org.colin.code.BaseResponseCode;
import org.colin.constants.Constant;
import org.colin.exception.ServiceException;
import org.colin.mapper.SysMessageMapper;
import org.colin.mapper.yw.BbsPostMapper;
import org.colin.model.entity.SysMessage;
import org.colin.model.entity.SysSuggestion;
import org.colin.model.ro.MessageRO;
import org.colin.model.vo.resp.MessageVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.service.MessageService;
import org.colin.utils.PageUtils;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * @Description: MessageServiceImpl
 * @ClassName: MessageServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/4 15:36
 * @Version: 1.1.0
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private SysMessageMapper sysMessageMapper;

    @Autowired
    private BbsPostMapper bbsPostMapper;

    @Override
    public void hasReadMsg(String userId, int status, String target) {
        sysMessageMapper.hasReadMsg(userId, status, target);//target消息目标（1：平台；2：北方社区）
    }

    @Override
    public void setMessageStatus(String messageId, int status) {
        sysMessageMapper.setMessageStatus(messageId, status);
    }

    @Override
    public List<MessageVO> getUserMsg(String userId, int status, String target) {
        return sysMessageMapper.getUserMsg(userId, status, target);//target消息目标（1：平台；2：北方社区）
    }

    @Override
    public void addSuggestion(SysSuggestion obj) {
        obj.setId(Tool.getPrimaryKey());
        obj.setUpdateTime(new Date());
        obj.setSource("1");//来源（1：平台；2：北方社区）
        bbsPostMapper.addSuggestion(obj);
    }

    @Override
    public void insertMessage(String userId, MessageRO ro) {
        String[] receiveIdList = ro.getReceiveUserJson().split(",");
        if (receiveIdList != null && receiveIdList.length > 0) {
            List<SysMessage> list = new ArrayList<>();
            SysMessage msg = null;
            for (int i = 0; i < receiveIdList.length; i++) {
                msg = new SysMessage();
                String receiveId = receiveIdList[i];
                if (!Tool.isBlank(receiveId)) {
                    msg.setId(Tool.getPrimaryKey());
                    msg.setCreateTime(new Date());
                    msg.setCreateUserId(userId);
                    msg.setReceiveUserId(receiveId);
                    msg.setMsgContent(ro.getMsgContent());
                    msg.setMsgTitle(ro.getMsgTitle());
                    msg.setReadStatus(0);//0：未读；1：已读；
                    msg.setTarget(ro.getTarget());//消息目标（1：平台；2：北方社区）
                    list.add(msg);
                }
            }
            int result = sysMessageMapper.insertForeach(list);
            if (result <= 0) {
                throw new ServiceException(BaseResponseCode.DATA_ERROR);
            }
        } else {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
    }

    @Override
    public void deleteMessage(List<String> ids) {
        if (ids != null && !ids.isEmpty()) {
            int result = sysMessageMapper.batchDeletedMessage(ids);
            if (result <= 0) {
                throw new ServiceException(BaseResponseCode.DATA_ERROR);
            }
        } else {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
    }

    @Override
    public PageVO<MessageVO> pageInfo(MessageRO ro) {
        PageHelper.startPage(ro.getPageNum(), ro.getPageSize());
        List<MessageVO> voList = sysMessageMapper.selectAll(ro);
        return PageUtils.getPageVO(voList);
    }
}
