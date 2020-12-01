package org.colin.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.colin.mapper.SysDicInfoMapper;
import org.colin.mapper.SysDicTypeMapper;
import org.colin.model.ro.SysDicInfo;
import org.colin.model.vo.resp.DicSelectInfoVO;
import org.colin.model.vo.resp.SysDicInfoVO;
import org.colin.model.vo.resp.SysDicTypeVO;
import org.colin.service.SysDicInfoService;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @Description: SysDicInfoServiceImpl
 * @ClassName: SysDicInfoServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/5/21 17:32
 * @Version: 1.1.0
 */
@Service
public class SysDicInfoServiceImpl implements SysDicInfoService {

    @Autowired
    private SysDicInfoMapper sysDicInfoMapper;

    @Autowired
    private SysDicTypeMapper sysDicTypeMapper;

    @Override
    public List<SysDicInfoVO> getAllDicByTypeCode(String typeCode) {
        return sysDicInfoMapper.selectAll(typeCode);
    }

    @Override
    public List<SysDicInfoVO> getAllOKDicByTypeCode(String typeCode) {
        return sysDicInfoMapper.selectAllOK(typeCode);
    }

    @Override
    public List<DicSelectInfoVO> selectDicByTypeCode() {
        //查询所有数据字典类型
        List<SysDicTypeVO> dicTypeList = sysDicTypeMapper.selectAllDicType();
        List<DicSelectInfoVO> returnList = new ArrayList<DicSelectInfoVO>();
        if(CollectionUtils.isNotEmpty(dicTypeList)){
            DicSelectInfoVO vo = null;
            for(int i=0; i<dicTypeList.size(); i++){
                vo = new DicSelectInfoVO();
                SysDicTypeVO dicType = dicTypeList.get(i);
                vo.setTypeCode(dicType.getTypeCode());
                vo.setDicInfo(sysDicInfoMapper.selectDicByTypeCode(dicType.getTypeCode()));
                returnList.add(vo);
            }
        }
        return returnList;
    }

    @Override
    public void add(SysDicInfo obj, String userId) {
        obj.setId(Tool.getPrimaryKey());
        obj.setUpdateTime(new Date());
        obj.setUpdateUserId(userId);
        sysDicInfoMapper.insertSelective(obj);
    }

    @Override
    public void deleteById(String id) {
        sysDicInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SysDicInfo obj, String userId) {
        obj.setUpdateTime(new Date());
        obj.setUpdateUserId(userId);
        sysDicInfoMapper.updateByPrimaryKeySelective(obj);
    }
}
