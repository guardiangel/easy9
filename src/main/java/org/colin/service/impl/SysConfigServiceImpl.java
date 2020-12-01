package org.colin.service.impl;

import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.mapper.SysConfigMapper;
import org.colin.model.ro.SysConfig;
import org.colin.model.vo.req.SysConfigReqVO;
import org.colin.model.vo.resp.SysConfigRespVO;
import org.colin.service.SysConfigService;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    public void addConfig(SysConfig obj) {
        SysConfig config = sysConfigMapper.queryByKey(obj.getSysKey());
        if(config != null){
            throw new ServiceException(BaseResponseCode.SYS_CONFIG_KEY_EXIST);
        }
        obj.setId(Tool.getPrimaryKey());
        obj.setUpdateTime(new Date());
        sysConfigMapper.insertSelective(obj);
    }

    @Override
    public void deleteConfig(String id) {
        sysConfigMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysConfigRespVO selectById(String id) {
        return sysConfigMapper.selectById(id);
    }

    @Override
    public SysConfig queryByKey(String key) {
        return sysConfigMapper.queryByKey(key);
    }

    @Override
    public List<SysConfigRespVO> selectAllConfig(SysConfigReqVO vo) {
        List<SysConfigRespVO> list = sysConfigMapper.selectAllConfig(vo);
        return list;
    }

    @Override
    public void updateConfig(SysConfig vo) {
        vo.setUpdateTime(new Date());
        sysConfigMapper.updateByPrimaryKeySelective(vo);
    }
}
