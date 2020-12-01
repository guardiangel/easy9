package org.colin.mapper.yw;

import org.colin.model.entity.yw.YwStore;
import org.colin.model.vo.req.YwStorePageVO;
import org.colin.model.vo.resp.YwStoreVO;

import java.util.List;

public interface YwStoreMapper {

    int deleteByPrimaryKey(String id);

    int deleteGoodsByStoreId(String storeId);

    int insert(YwStore record);

    int insertSelective(YwStore record);

    YwStore selectByPrimaryKey(String id);

    List<YwStoreVO> selectAll(YwStorePageVO vo);

    List<YwStore> getStoreByCommunityId(String communityId);

    int updateByPrimaryKeySelective(YwStore record);

    int updateByPrimaryKeyWithBLOBs(YwStore record);

    int updateByPrimaryKey(YwStore record);
}
