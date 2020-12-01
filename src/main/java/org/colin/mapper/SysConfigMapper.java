package org.colin.mapper;

import org.colin.model.ro.SysConfig;
import org.colin.model.vo.req.SysConfigReqVO;
import org.colin.model.vo.resp.SysConfigRespVO;
import java.util.List;

public interface SysConfigMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(String id);

    //根据ID查询指定系统参数信息
    SysConfigRespVO selectById(String id);

    //根据key查询指定系统参数信息
    SysConfig queryByKey(String sysKey);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

    //查询所有系统参数配置
    List<SysConfigRespVO> selectAllConfig(SysConfigReqVO vo);
}