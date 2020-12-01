package org.colin.mapper.yw;

import org.apache.ibatis.annotations.Param;
import org.colin.model.ro.BbsUser;
import java.util.List;

public interface BbsUserMapper {

    int deleteByPrimaryKey(String id);

    int insert(BbsUser record);

    int insertSelective(BbsUser record);

    BbsUser selectByPrimaryKey(String id);

    //查询[北方社区]用户数量
    int selectAllBbsUser(@Param(value = "sex") String sex);

    int updateByPrimaryKeySelective(BbsUser record);

    int updateByPrimaryKey(BbsUser record);
}