package org.colin.service;

import org.colin.model.ro.BbsNotice;
import org.colin.model.vo.resp.BbsNoticeVO;
import java.util.List;

public interface BbsNoticeService {

    List<BbsNoticeVO> getAllNotice();

    BbsNoticeVO getNoticeById(String id);

    void insert(BbsNotice obj);
}
