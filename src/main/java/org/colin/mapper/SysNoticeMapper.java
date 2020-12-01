package org.colin.mapper;

import org.colin.model.ro.SysNotice;
import org.colin.model.vo.resp.SysNoticeRespVO;

import java.util.List;

public interface SysNoticeMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysNotice record);

    int insertSelective(SysNotice record);

    SysNoticeRespVO selectByPrimaryKey(String id);

    SysNoticeRespVO getNoticeByType(String type);

    List<SysNoticeRespVO> selectAll();

    int updateByPrimaryKeySelective(SysNotice record);

    int updateByPrimaryKey(SysNotice record);
}