package org.colin.service;

import org.colin.model.entity.YwCbfq;
import org.colin.model.vo.resp.PageVO;

public interface YwCbfqService {

    void addObject(YwCbfq obj);

    void deleteObject(String id);

    PageVO<YwCbfq> selectPageInfo(YwCbfq obj);

    YwCbfq selectById(String id);

    void updateObj(YwCbfq obj);
}
