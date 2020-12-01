package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.colin.mapper.SysDicInfoMapper;
import org.colin.mapper.SysDicTypeMapper;
import org.colin.model.dto.DictionaryTree;
import org.colin.model.entity.DeptUserTree;
import org.colin.model.entity.SysDicType;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.SysDicTypeVO;
import org.colin.service.SysDicTypeService;
import org.colin.utils.PageUtils;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: SysDicTypeServiceImpl
 * @ClassName: SysDicTypeServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020-05-21 14:46:32
 * @Version: 1.1.0
 */
@Service
@Slf4j
public class SysDicTypeServiceImpl implements SysDicTypeService {

    @Autowired
    private SysDicTypeMapper sysDicTypeMapper;

    @Autowired
    private SysDicInfoMapper sysDicInfoMapper;

    @Override
    public void add(SysDicType sysDicType, String userId) {
        sysDicType.setId(Tool.getPrimaryKey());
        sysDicType.setUpdateUserId(userId);
        sysDicType.setUpdateTime(new Date());
        sysDicTypeMapper.insertSelective(sysDicType);
    }

    @Override
    public void deleteById(String id) {
        sysDicTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByTypeCode(String typeCode) {
        //根据字典类型编码删除数据字典类型和数据字典信息
        sysDicTypeMapper.deleteByTypeCode(typeCode);
        sysDicInfoMapper.deleteByTypeCode(typeCode);
    }

    @Override
    public SysDicType findById(String id) {
        return sysDicTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageVO<SysDicTypeVO> pageInfo(SysDicType sysDicType) {
        PageHelper.startPage(sysDicType.getPageNum(), sysDicType.getPageSize());
        List<SysDicTypeVO> sysDicTypeList = sysDicTypeMapper.selectAll(sysDicType);
        return PageUtils.getPageVO(sysDicTypeList);
    }

    @Override
    public List<SysDicTypeVO> getAll(SysDicType sysDicType) {
        return sysDicTypeMapper.selectAll(sysDicType);
    }

    @Override
    public void update(SysDicType sysDicType, String userId) {
        sysDicType.setUpdateTime(new Date());
        sysDicType.setUpdateUserId(userId);
        sysDicTypeMapper.updateByPrimaryKeySelective(sysDicType);
    }

    @Override
    public List<DictionaryTree> getAllDicTree() {
        //返回数据
        List<DictionaryTree> result = new ArrayList<>();
        DictionaryTree parentNode = new DictionaryTree();//根节点
        String parentId = Tool.getPrimaryKey();
        parentNode.setId(parentId);
        parentNode.setPid("000");
        parentNode.setName("根节点");
        parentNode.setChecked(false);
        parentNode.setIcon("/images/topTree.png");
        parentNode.setTypeCode("1010101010");
        parentNode.setBak("");
        parentNode.setStatus(1);
        parentNode.setTitle("根节点");
        result.add(parentNode);
        List<SysDicTypeVO> dicTypeList = sysDicTypeMapper.selectAll(new SysDicType());
        if(CollectionUtils.isNotEmpty(dicTypeList)){
            DictionaryTree tree = null;
            for(int i=0;i <dicTypeList.size(); i++){
                tree = new DictionaryTree();
                SysDicTypeVO vo = dicTypeList.get(i);
                tree.setId(vo.getId());
                tree.setPid(parentId);
                tree.setName(vo.getTypeName());
                tree.setChecked(false);
                tree.setIcon("/images/dicType.png");
                tree.setTypeCode(vo.getTypeCode());
                tree.setBak(vo.getBak());
                tree.setStatus(vo.getStatus());
                String status = vo.getStatus() == 1 ? "可用" : "不可用";
                tree.setTitle("字典类型编码："+vo.getTypeCode()+"；字典类型名称："+vo.getTypeName()+"；状态："+status);
                result.add(tree);
            }
        }
        return result;
    }
}
