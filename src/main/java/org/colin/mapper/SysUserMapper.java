package org.colin.mapper;

import org.apache.ibatis.annotations.Param;
import org.colin.model.dto.UserPhotoTemp;
import org.colin.model.entity.SysUser;
import org.colin.model.ro.SysUserAttac;
import org.colin.model.ro.SysUserPhoto;
import org.colin.model.vo.req.UserPageReqVO;
import org.colin.model.vo.resp.BbsUserVO;
import org.colin.model.vo.resp.EchartsVO;
import org.colin.model.vo.resp.TableStructureVO;
import org.colin.model.vo.resp.TableVO;
import java.util.List;

/**
 * @Description: SysUserMapper
 * @ClassName: SysUserMapper
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:10
 * @Version: 1.1.0
 */
public interface SysUserMapper {

    void updateUserPoint(@Param(value = "userId") String userId, @Param(value = "point") String point);

    List<TableStructureVO> selectTabelByName(String tableName);

    List<TableVO> selectAllTable();

    SysUser getUserInfoByName(String username);

    SysUser selectByPrimaryKey(String id);

    BbsUserVO getBbsUserInfoByPkId(String id);

    //获取某用户的总发帖数量(只算审核通过后的记录)
    int selectPublishPostCount(String userId);

    //获取某用户的总评论数量(只算审核通过后的记录)
    int selectCommentCount(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateBySysUserAttacPrimaryKeySelective(SysUserAttac record);

    List<SysUser> selectAll(UserPageReqVO vo);

    List<SysUser> selectAllByParam(UserPageReqVO vo);

    //查询所有用户
    List<SysUser> selectAllUser();

    //新增用户主表信息
    int insertUser(SysUser record);

    //新增用户附表信息
    int insertUserAttac(SysUserAttac record);

    //新增用户头像信息
    int insertUserPhoto(SysUserPhoto record);

    //删除用户
    int deletedUsers(@Param("sysUser") SysUser sysUser, @Param("list") List<String> list);

    List<SysUser> selectUserInfoByDeptIds(List<String> deptIds);

    //批量插入测试图像
    void batchInsertUserPhotoTemp(UserPhotoTemp obj);

    //查询所有测试头像
    public List<String> selectAllUserPhotoTemp();

    //根据用户ID修改用户头像
    void updateUserPhotoByUserId(@Param("userId") String userId, @Param("userPhoto") String userPhoto);

    //根据用户邮箱修改用户登录密码
    void updateUserPasswordByEmail(@Param("email") String email, @Param("password") String password, @Param("salt") String salt);

    //根据用户ID修改用户登录密码
    void updateUserPasswordByUserId(@Param("id") String id, @Param("password") String password, @Param("salt") String salt);

    //统计相关******************************************************开始
    //统计各部门总人数
    List<EchartsVO> selectDeptUserCount();

    //统计各性别总人数
    List<EchartsVO> selectUserSexCount();

    //统计各角色总人数
    List<EchartsVO> selectUserRoleCount();

    //统计各工资段人数
    List<EchartsVO> selectSalaryUserCount();

    //数据看版-用户登录浏览器分析
    List<EchartsVO> loginBrowser();

    //获取用户登录归属地信息
    List<EchartsVO> getIpAddressInfo();
    //统计相关******************************************************结束
}