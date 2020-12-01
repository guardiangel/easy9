package org.colin.service.impl;

import org.colin.mapper.SysUserMapper;
import org.colin.mapper.yw.BbsPostMapper;
import org.colin.mapper.yw.BbsUserMapper;
import org.colin.model.entity.SysUser;
import org.colin.model.vo.resp.*;
import org.colin.service.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: HomeServiceImpl
 * @ClassName: HomeServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 20:11
 * @Version: 1.1.0
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private MessageService messageService;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private BbsUserMapper bbsUserMapper;

    @Resource
    private BbsPostMapper bbsPostMapper;

    @Override
    public List<TableStructureVO> selectTabelByName(String tableName) {
        return userService.selectTabelByName(tableName);
    }

    @Override
    public List<TableVO> selectAllTable() {
        return userService.selectAllTable();
    }

    @Override
    public HomeRespVO getHomeInfo(String userId) {
        SysUser sysUser = userService.detailInfo(userId);
        UserInfoRespVO vo = new UserInfoRespVO();
        if (sysUser != null) {
            vo.setId(sysUser.getId());
            vo.setUsername(sysUser.getUsername());
            vo.setPhone(sysUser.getPhone());
            vo.setNickName(sysUser.getNickName());
            vo.setRealName(sysUser.getRealName());
            vo.setDeptId(sysUser.getDeptId());
            vo.setDeptName(sysUser.getDeptName());
            vo.setPhoto(sysUser.getPhoto());
        }
        List<PermissionRespNode> menus = permissionService.permissionTreeList(userId);
        HomeRespVO respVO = new HomeRespVO();
        //获取当前用户的消息总数(状态（0：未读；1：已读；2：全部；）)
        List<MessageVO> msgCountList = messageService.getUserMsg(userId, 2, "1");
        List<MessageVO> unReadMsgCountList = messageService.getUserMsg(userId, 0, "1");
        respVO.setMsgCount(msgCountList == null ? 0 : msgCountList.size());
        respVO.setUnReadMsgCount(unReadMsgCountList == null ? 0 : unReadMsgCountList.size());
        respVO.setMenus(menus);
        respVO.setUserInfo(vo);
        return respVO;
    }

    @Override
    public List<EchartsVO> getIpAddressInfo() {
        return sysUserMapper.getIpAddressInfo();
    }

    @Override
    public MainVO getMainInfo() {
        MainVO vo = new MainVO();
        vo.setBbsUserCount(bbsUserMapper.selectAllBbsUser(""));//[北方社区]用户总数量
        vo.setBbsUserCountNan(bbsUserMapper.selectAllBbsUser("1"));//[北方社区]男用户总数量
        vo.setBbsUserCountNv(bbsUserMapper.selectAllBbsUser("2"));//[北方社区]女用户总数量
        vo.setPostCount(bbsPostMapper.selectPostCount(""));//[北方社区]发帖总数量
        vo.setPostCountPass(bbsPostMapper.selectPostCount("2"));//[北方社区]发帖审核通过数量
        vo.setPostCountChecking(bbsPostMapper.selectPostCount("1"));//[北方社区]发帖审核中数量
        vo.setReplyCount(bbsPostMapper.selectReplyCount(""));//[北方社区]评论总数量
        vo.setReplyCountPass(bbsPostMapper.selectReplyCount("2"));//[北方社区]评论审核通过数量
        vo.setReplyCountChecking(bbsPostMapper.selectReplyCount("1"));
        return vo;
    }
}
