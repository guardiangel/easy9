package org.colin.mapper;

import org.colin.model.entity.SysLog;
import org.colin.model.vo.req.SysLogPageReqVO;

import java.util.List;
/**
 * @Description: SysLogMapper
 * @ClassName: SysLogMapper
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 20:34
 * @Version: 1.1.0
 */
public interface SysLogMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    List<SysLog> selectAll(SysLogPageReqVO vo);

    void batchDeletedLog(List<String> logIds);
}
