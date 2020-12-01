package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * @Description: HomeRespVO
 * @ClassName: HomeRespVO
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 20:09
 * @Version: 1.1.0
 */
public class HomeRespVO {

    @ApiModelProperty(value = "当前用户总消息数")
    private Integer msgCount;

    @ApiModelProperty(value = "当前用户未读消息总数")
    private Integer unReadMsgCount;

    @ApiModelProperty(value = "用户信息")
    private UserInfoRespVO userInfo;

    @ApiModelProperty(value = "目录菜单")
    private List<PermissionRespNode> menus;

    @Override
    public String toString() {
        return "HomeRespVO{" +
                "msgCount=" + msgCount +
                ", unReadMsgCount=" + unReadMsgCount +
                ", userInfo=" + userInfo +
                ", menus=" + menus +
                '}';
    }

    public Integer getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(Integer msgCount) {
        this.msgCount = msgCount;
    }

    public Integer getUnReadMsgCount() {
        return unReadMsgCount;
    }

    public void setUnReadMsgCount(Integer unReadMsgCount) {
        this.unReadMsgCount = unReadMsgCount;
    }

    public UserInfoRespVO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoRespVO userInfo) {
        this.userInfo = userInfo;
    }

    public List<PermissionRespNode> getMenus() {
        return menus;
    }

    public void setMenus(List<PermissionRespNode> menus) {
        this.menus = menus;
    }
}
