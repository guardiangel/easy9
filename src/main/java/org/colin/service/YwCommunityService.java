package org.colin.service;

import org.colin.model.entity.yw.YwCommunity;
import org.colin.model.vo.req.YwCommunityPageVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.YwCommStoreTreeVO;

import java.util.List;

public interface YwCommunityService {

    void addObject(YwCommunity obj);

    void deletedObject(String id);

    PageVO<YwCommunity> pageInfo(YwCommunityPageVO vo);

    List<YwCommunity> selectAll();

    void updateObject(YwCommunity obj);

    //查询所有社区-商家树型数据
    List<YwCommStoreTreeVO> commStoreTree();

}
