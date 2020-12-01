package org.colin.service;

import org.colin.model.entity.yw.YwStore;
import org.colin.model.vo.req.YwStorePageVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.YwStoreVO;

import java.util.List;

public interface YwStoreService {

    void addObject(YwStore obj);

    void deletedObject(String id);

    PageVO<YwStoreVO> pageInfo(YwStorePageVO vo);

    void updateObject(YwStore obj);

    void updateWeixinMoney(YwStore obj);

    List<YwStore> getStoreByCommunityId(String communityId);

    YwStore getStoreById(String storeId);
}
