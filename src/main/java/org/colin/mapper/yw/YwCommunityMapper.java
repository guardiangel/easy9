package org.colin.mapper.yw;

import org.colin.model.entity.yw.YwCommunity;
import org.colin.model.vo.req.YwCommunityPageVO;

import java.util.List;
/**
 * @Description: YwCommunityMapper
 * @ClassName: YwCommunityMapper
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/6 11:16
 * @Version: 1.1.0
 */
public interface YwCommunityMapper {

    int deleteStoreByCommunityId(String communityId);

    int deleteByPrimaryKey(String id);

    int insert(YwCommunity record);

    int insertSelective(YwCommunity record);

    List<YwCommunity> selectAll(YwCommunityPageVO vo);

    List<YwCommunity> selectAllCommunity();

    YwCommunity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YwCommunity record);

    int updateByPrimaryKey(YwCommunity record);
}
