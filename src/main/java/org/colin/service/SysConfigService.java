package org.colin.service;

import org.colin.model.ro.SysConfig;
import org.colin.model.vo.req.SysConfigReqVO;
import org.colin.model.vo.resp.SysConfigRespVO;
import java.util.List;

public interface SysConfigService {

    //新增系统配置
    void addConfig(SysConfig obj);

    //删除系统配置
    void deleteConfig(String id);

    //根据ID查询指定系统参数信息
    SysConfigRespVO selectById(String id);

    //根据key查询指定系统参数信息
    SysConfig queryByKey(String key);

    //查询所有系统配置信息
    List<SysConfigRespVO> selectAllConfig(SysConfigReqVO vo);

    //修改系统配置参数
    void updateConfig(SysConfig vo);
}
