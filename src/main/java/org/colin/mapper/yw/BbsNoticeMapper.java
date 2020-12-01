package org.colin.mapper.yw;

import org.colin.model.ro.BbsNotice;
import org.colin.model.vo.resp.BbsNoticeVO;

import java.util.List;

public interface BbsNoticeMapper {
    int deleteByPrimaryKey(String id);

    int insert(BbsNotice record);

    int insertSelective(BbsNotice record);

    BbsNoticeVO selectByPrimaryKey(String id);

    List<BbsNoticeVO> getAllNotice();

    int updateByPrimaryKeySelective(BbsNotice record);

    int updateByPrimaryKey(BbsNotice record);

    void addReadCount(String id);
}