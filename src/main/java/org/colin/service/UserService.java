package org.colin.service;

import org.colin.model.entity.DeptUserTree;
import org.colin.model.entity.SysUser;
import org.colin.model.entity.UserTree;
import org.colin.model.vo.req.*;
import org.colin.model.vo.resp.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    List<TableStructureVO> selectTabelByName(String tableName);

    List<TableVO> selectAllTable();

    //查询所有用户信息
    List<SysUser> getAllUserInfo();

    //获取所有部门用户树型结构数据
    List<DeptUserTree> getAllDeptUserTree();

    //用户登录平台
    LoginRespVO login(LoginReqVO vo, HttpServletRequest request);

    //BBS登录
    LoginRespVO bbsLogin(LoginReqVO vo);

    //用户退出系统
    public void logout(String accessToken, String refreshToken);

    //根据用户ID查询用户详情
    SysUser detailInfo(String userId);

    //根据用户ID查询BBS用户详情
    BbsUserVO getBbsUserInfoByPkId(String userId);

    //修改用户信息
    public void updateUserInfo(UserUpdateReqVO vo, String operationId);

    //用户修改自己个人信息
    public void updateUserInfoSelf(UserUpdateReqVO vo, String operationId);

    //用户修改自己个人信息
    public void updateBbsUserInfoSelf(BbsUserUpdateReqVO vo, String userId);

    //根据用户邮箱修改用户密码
    public void updateUserPasswordByEmail(String email, String password);

    //根据用户ID修改用户密码
    public void updateUserPasswordByUserId(String userId, String password);

    public void setUserOwnRole(String userId, List<String> roleIds);

    public PageVO<SysUser> pageInfo(UserPageReqVO vo);

    public String refreshToken(String refreshToken, String accessToken);

    //注册用户
    public void registerUser(RegisterUserReqVO vo, HttpServletRequest request);

    //新增用户
    public void addUser(UserAddReqVO vo, String userId);

    //删除用户
    public void deletedUsers(List<String> userIds, String userId);

    public UserOwnRoleRespVO getUserOwnRole(String userId);

    public PageVO<SysUser> selectUserInfoByDeptIds(int pageNum, int pageSize, List<String> deptIds);

    public List<SysUser> getUserListByDeptIds(List<String> deptIds);

    //查询所有测试头像
    public List<String> selectAllUserPhotoTemp();

    //根据用户ID修改用户头像
    public void updateUserPhotoByUserId(String userId, String userPhoto);

    //获取用户部门树型数据
    public List<UserTree> selectAllByTree();

    //获取用户相关统计图数据
    public EchartsRespVO userEcharts(String type);

    //数据看版-用户登录浏览器分析
    public EchartsRespVO loginBrowser();
}
