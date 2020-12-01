package org.colin.service;

import org.colin.model.entity.SysSuggestion;
import org.colin.model.ro.MessageRO;
import org.colin.model.vo.resp.MessageVO;
import org.colin.model.vo.resp.PageVO;
import java.util.List;

public interface MessageService {

    // 新增意见信息
    void addSuggestion(SysSuggestion obj);

    //将当前用户的所有系统消息置为已读状态(状态（0：未读；1：已读；）)
    void hasReadMsg(String userId, int status, String target);

    //将指定消息置为指定状态(状态（0：未读；1：已读；）)
    void setMessageStatus(String messageId, int status);

    //查询当前用户的消息信息(状态（0：未读；1：已读；2：全部；）)
    List<MessageVO> getUserMsg(String userId, int status, String target);

    //新增系统消息
    void insertMessage(String userId, MessageRO ro);

    //删除系统消息
    void deleteMessage(List<String> ids);

    //分页查询所有系统消息
    PageVO<MessageVO> pageInfo(MessageRO ro);
}
