package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.mapper.yw.YwStoreMapper;
import org.colin.model.entity.yw.YwStore;
import org.colin.model.vo.req.YwStorePageVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.model.vo.resp.YwStoreVO;
import org.colin.service.YwStoreService;
import org.colin.utils.PageUtils;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 * @Description: YwStoreServiceImpl
 * @ClassName: YwStoreServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/3/6 15:40
 * @Version: 1.1.0
 */
@Service
public class YwStoreServiceImpl implements YwStoreService {

    @Autowired
    private YwStoreMapper ywStoreMapper;

    @Override
    public void addObject(YwStore obj) {
        obj.setId(Tool.getPrimaryKey());
        obj.setCreateTime(new Date());
        obj.setStoreState(1);//状态（0：禁用；1：正常；）
        int result = ywStoreMapper.insert(obj);
        if (result != 1) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
    }

    @Override
    public void deletedObject(String id) {
        int result1 = ywStoreMapper.deleteByPrimaryKey(id);
        if (result1 != 1) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
        //根据商家ID删除其下所有商品
        int result2 = ywStoreMapper.deleteGoodsByStoreId(id);
    }

    @Override
    public PageVO<YwStoreVO> pageInfo(YwStorePageVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<YwStoreVO> objList = ywStoreMapper.selectAll(vo);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public void updateObject(YwStore obj) {
        obj.setCreateTime(new Date());
        int result = ywStoreMapper.updateByPrimaryKeySelective(obj);
        if (result != 1) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
    }

    @Override
    public void updateWeixinMoney(YwStore obj) {
        int result = ywStoreMapper.updateByPrimaryKeySelective(obj);
        if (result != 1) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
    }

    @Override
    public List<YwStore> getStoreByCommunityId(String communityId) {
        List<YwStore> list = ywStoreMapper.getStoreByCommunityId(communityId);
        return list;
    }

    @Override
    public YwStore getStoreById(String storeId) {
        YwStore obj = ywStoreMapper.selectByPrimaryKey(storeId);
        return obj;
    }
}
