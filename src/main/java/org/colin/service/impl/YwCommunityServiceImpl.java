package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.mapper.yw.YwCommunityMapper;
import org.colin.mapper.yw.YwStoreMapper;
import org.colin.model.entity.yw.YwCommunity;
import org.colin.model.vo.req.YwCommunityPageVO;
import org.colin.model.vo.req.YwStorePageVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.YwCommStoreTreeVO;
import org.colin.model.vo.resp.YwStoreVO;
import org.colin.service.YwCommunityService;
import org.colin.utils.PageUtils;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @Description: YwCommunityServiceImpl
 * @ClassName: YwCommunityServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/6 11:33
 * @Version: 1.1.0
 */
@Service
public class YwCommunityServiceImpl implements YwCommunityService {

    @Autowired
    private YwCommunityMapper ywCommunityMapper;

    @Autowired
    private YwStoreMapper ywStoreMapper;

    @Override
    public void addObject(YwCommunity obj) {
        obj.setId(Tool.getPrimaryKey());
        obj.setCreateTime(new Date());
        obj.setComState(1);//状态（0：禁用；1：正常；）
        int result = ywCommunityMapper.insert(obj);
        if (result != 1) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
    }

    @Override
    public void deletedObject(String id) {
        int result1 = ywCommunityMapper.deleteByPrimaryKey(id);
        if (result1 != 1) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
        //根据社区ID删除其下所有商家
        int result2 = ywCommunityMapper.deleteStoreByCommunityId(id);
    }

    @Override
    public PageVO<YwCommunity> pageInfo(YwCommunityPageVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<YwCommunity> objList = ywCommunityMapper.selectAll(vo);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public List<YwCommunity> selectAll() {
        List<YwCommunity> objList = ywCommunityMapper.selectAllCommunity();
        return objList;
    }

    @Override
    public void updateObject(YwCommunity obj) {
        obj.setCreateTime(new Date());
        int result = ywCommunityMapper.updateByPrimaryKeySelective(obj);
        if (result != 1) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
    }

    @Override
    public List<YwCommStoreTreeVO> commStoreTree() {
        List<YwCommStoreTreeVO> returnList = new ArrayList<YwCommStoreTreeVO>();
        //查询所有社区
        YwCommunityPageVO commVO = new YwCommunityPageVO();
        List<YwCommunity> commList = ywCommunityMapper.selectAll(commVO);
        YwCommStoreTreeVO entity = null;
        if (commList != null && !commList.isEmpty()) {
            for (int i = 0; i < commList.size(); i++) {
                YwCommunity obj = commList.get(i);
                entity = new YwCommStoreTreeVO();
                entity.setId(obj.getId());
                entity.setNodeName(obj.getComName());
                entity.setNodeType(1);
                entity.setPid("0");
                returnList.add(entity);
            }
        }
        //查询所有商家
        YwStorePageVO storeVO = new YwStorePageVO();
        List<YwStoreVO> storeList = ywStoreMapper.selectAll(storeVO);
        if (storeList != null && !storeList.isEmpty()) {
            for (int i = 0; i < storeList.size(); i++) {
                YwStoreVO obj = storeList.get(i);
                entity = new YwCommStoreTreeVO();
                entity.setId(obj.getId());
                entity.setNodeName(obj.getStoreName());
                entity.setNodeType(2);
                entity.setPid(obj.getCommunityId());
                returnList.add(entity);
            }
        }
        return returnList;
    }

}
