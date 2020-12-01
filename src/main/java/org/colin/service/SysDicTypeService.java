package org.colin.service;

import org.colin.model.dto.DictionaryTree;
import org.colin.model.entity.SysDicType;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.SysDicTypeVO;
import java.util.List;

/**
 * @Description: SysDicTypeService
 * @ClassName: SysDicTypeService
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020-05-21 14:46:32
 * @Version: 1.1.0
 */
public interface SysDicTypeService {

    //新增
    void add(SysDicType sysDicType, String userId);

    //删除
    void deleteById(String id);

    //根据字典类型编码删除数据字典类型和数据字典信息
    void deleteByTypeCode(String typeCode);

    //详情
    SysDicType findById(String id);

    //分页查询
    PageVO<SysDicTypeVO> pageInfo(SysDicType sysDicType);

    //查询所有(不分页)
    List<SysDicTypeVO> getAll(SysDicType sysDicType);

    //修改
    void update(SysDicType sysDicType, String userId);

    //获取数据字典类型树型数据
    List<DictionaryTree> getAllDicTree();

}
