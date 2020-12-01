package org.colin.mapper;

import org.apache.ibatis.annotations.Param;
import org.colin.model.entity.SysDept;

import java.util.List;
/**
 * @Description: SysDeptMapper
 * @ClassName: SysDeptMapper
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 20:20
 * @Version: 1.1.0
 */
public interface SysDeptMapper {

    SysDept selectByPrimaryKey(String id);

    List<SysDept> selectAll();

    List<String> selectChildIds(String relationCode);

    List<SysDept> selectAllByNotContainChild(List<String> list);

    int insertSelective(SysDept record);

    int updateByPrimaryKeySelective(SysDept record);

    int updateRelationCode(@Param("oldStr") String oldStr, @Param("newStr") String newStr, @Param("relationCode") String relationCode);
}
