package org.colin.service;

import org.colin.model.entity.tree.ATree;
import org.colin.model.ro.*;
import org.colin.model.vo.req.*;
import org.colin.model.vo.resp.*;
import java.util.List;

public interface JxcService {

    //获取下拉列表中所有商品信息
    List<ATree> getAllGoodsTreeSelect();

    //获取下拉框列表中所有仓库信息
    List<WareHouseRespVO> getAllWareHouseSelect();



    //新增仓库信息
    void addWareHouse(JxcWarehouse obj);

    //删除仓库信息
    void deleteWareHouse(String id);

    //分页获取[仓库信息]列表数据
    PageVO<WareHouseRespVO> getAllWareHousePageInfo(CommonVo vo);

    //查询某个仓库详情信息
    WareHouseRespVO getWareHouseById(String id);

    //更新仓库信息
    void updateWareHouse(JxcWarehouse obj);






    //新增商品信息
    void addGoods(JxcGoods obj);

    //删除商品信息
    void deleteGoods(String id);

    //分页获取[商品信息]列表数据
    PageVO<JxcGoodsRespVO> getAllGoodsPageInfo(JxcGoodsReqVO vo);

    //查询某个商品详情信息
    JxcGoodsRespVO getGoodsById(String id);

    //更新商品信息
    void updateGoods(JxcGoods obj);







    //新增商品入库信息
    void addInGoods(JxcInStore obj);

    //删除商品入库信息
    void deleteInGoods(String id);

    //分页获取[商品入库信息]列表数据
    PageVO<JxcInStoreRespVO> getAllInGoodsPageInfo(JxcInStoreReqVo vo);

    //查询某个入库详情
    JxcInStoreReqVo getInGoodsById(String id);








    //新增商品出库信息
    void addOutGoods(JxcOutStore obj);

    //删除商品出库信息
    void deleteOutGoods(String id);

    //分页获取[商品出库信息]列表数据
    PageVO<JxcOutStoreRespVO> getAllOutGoodsPageInfo(JxcOutStoreReqVO vo);

    //查询某个出库详情
    JxcOutStoreRespVO getOutGoodsById(String id);






    //分页获取[库存信息]列表数据
    PageVO<JxcStoreRespVO> getAllStorePageInfo(JxcStoreReqVO vo);

    //更新库存信息
    void updateStore(JxcStore obj);
}
