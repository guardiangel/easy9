package org.colin.mapper;

import org.apache.ibatis.annotations.Param;
import org.colin.model.entity.SysMessage;
import org.colin.model.ro.MessageRO;
import org.colin.model.vo.resp.MessageVO;

import java.util.List;
/**
 * @Description: SysMessageMapper
 * @ClassName: SysMessageMapper
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/4 15:37
 * @Version: 1.1.0
 */
public interface SysMessageMapper {

    //将当前用户的所有系统消息置为已读状态(状态（0：未读；1：已读；）)(target消息目标（1：平台；2：北方社区）)
    void hasReadMsg(@Param(value = "userId") String userId, @Param(value = "status") int status, @Param(value = "target") String target);

    //将指定消息置为指定状态
    void setMessageStatus(@Param(value = "messageId") String messageId, @Param(value = "status") int status);

    //查询当前用户的消息信息(状态（0：未读；1：已读；）)
    List<MessageVO> getUserMsg(@Param(value = "userId") String userId, @Param(value = "status") int status, @Param(value = "target") String target);

    //新增系统消息
    int insertForeach(List<SysMessage> list);

    //删除系统消息
    int batchDeletedMessage(List<String> ids);

    //分页查询所有系统消息
    List<MessageVO> selectAll(MessageRO ro);

    void insertSelective(SysMessage obj);

}
