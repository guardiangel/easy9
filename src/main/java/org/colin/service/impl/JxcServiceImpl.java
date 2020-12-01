package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.colin.code.BaseResponseCode;
import org.colin.exception.ServiceException;
import org.colin.mapper.SysDicInfoMapper;
import org.colin.mapper.yw.*;
import org.colin.model.entity.tree.ATree;
import org.colin.model.ro.*;
import org.colin.model.vo.req.*;
import org.colin.model.vo.resp.*;
import org.colin.service.JxcService;
import org.colin.utils.PageUtils;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JxcServiceImpl implements JxcService {

    @Autowired
    private JxcWarehouseMapper jxcWarehouseMapper;

    @Autowired
    private JxcGoodsMapper jxcGoodsMapper;

    @Autowired
    private JxcInStoreMapper jxcInStoreMapper;

    @Autowired
    private JxcOutStoreMapper jxcOutStoreMapper;

    @Autowired
    private JxcStoreMapper jxcStoreMapper;

    @Autowired
    private SysDicInfoMapper sysDicInfoMapper;

    @Override
    public List<ATree> getAllGoodsTreeSelect() {
        List<ATree> returnList = new ArrayList<ATree>();
        List<ATree> aList = new ArrayList<ATree>();
        List<ATree> treeNodeList = null;
        //查询所有商品类型作为一级节点
        List<DicInfo> dicList = sysDicInfoMapper.selectDicByTypeCode("GOODSTYPE");
        if(CollectionUtils.isNotEmpty(dicList)){
            ATree tree = null;
            for(int i=0; i<dicList.size(); i++){
                tree = new ATree();
                DicInfo dic = dicList.get(i);
                tree.setId(dic.getDicValue());
                tree.setTitle(dic.getDicValue());
                tree.setPid("000000");
                List<JxcGoodsRespVO> list = jxcGoodsMapper.getAllGoodsByGoodsType(dic.getDicCode());
                if(CollectionUtils.isNotEmpty(list)){
                    ATree treeNode = null;
                    treeNodeList = new ArrayList<ATree>();
                    for(int j=0; j<list.size(); j++) {
                        treeNode = new ATree();
                        JxcGoodsRespVO vo = list.get(j);
                        treeNode.setId(vo.getGoodsCode());
                        treeNode.setTitle(vo.getGoodsName() + "-" + vo.getGoodsModel());
                        treeNode.setPid(dic.getDicCode());
                        treeNodeList.add(treeNode);
                    }
                    tree.setChildren(treeNodeList);
                }
                aList.add(tree);
            }
            ATree treeRoot = new ATree();
            treeRoot.setId("000000");
            treeRoot.setTitle("默认顶级菜单");
            treeRoot.setChildren(aList);
            returnList.add(treeRoot);
        }
        return returnList;
    }

    @Override
    public List<WareHouseRespVO> getAllWareHouseSelect() {
        JxcWarehouse obj = new JxcWarehouse();
        List<WareHouseRespVO> list = jxcWarehouseMapper.getAllWareHouse(obj);
        return list;
    }

    @Override
    public void addWareHouse(JxcWarehouse obj) {
        JxcWarehouse wh = jxcWarehouseMapper.selectByWhName(obj.getWhName());
        if(wh != null){
            //仓库名已经存在了
            throw new ServiceException(BaseResponseCode.WAREHOUSE_NAME_EXIST);
        }
        obj.setId(Tool.getPrimaryKey());
        obj.setWhCode(Tool.getCode(7));
        obj.setCreateTime(new Date());
        obj.setUpdateTime(new Date());
        jxcWarehouseMapper.insertSelective(obj);
    }

    @Override
    public void deleteWareHouse(String id) {
        jxcWarehouseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageVO<WareHouseRespVO> getAllWareHousePageInfo(CommonVo vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<WareHouseRespVO> objList = jxcWarehouseMapper.getAllWareHouse(new JxcWarehouse());
        return PageUtils.getPageVO(objList);
    }

    @Override
    public WareHouseRespVO getWareHouseById(String id) {
        return jxcWarehouseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateWareHouse(JxcWarehouse obj) {
        JxcWarehouse wh = new JxcWarehouse();
        wh.setWhName(obj.getWhName());
        List<WareHouseRespVO> objList = jxcWarehouseMapper.getAllWareHouse(wh);
        if(objList != null && objList.size() > 1){
            //仓库名已经存在了
            throw new ServiceException(BaseResponseCode.WAREHOUSE_NAME_EXIST);
        }
        obj.setUpdateTime(new Date());
        jxcWarehouseMapper.updateByPrimaryKeySelective(obj);
    }





    @Override
    public void addGoods(JxcGoods obj) {
        JxcGoodsRespVO vo = jxcGoodsMapper.selectByNameAndModel(obj.getGoodsName(), obj.getGoodsModel());
        if(vo != null){
            //该型号的商品名称以及存在了
            throw new ServiceException(BaseResponseCode.GOODS_NAME_MODEL_EXIST);
        }
        obj.setId(Tool.getPrimaryKey());
        obj.setCreateTime(new Date());
        obj.setUpdateTime(new Date());
        obj.setGoodsCode(Tool.getCode(8));
        jxcGoodsMapper.insertSelective(obj);
    }

    @Override
    public void deleteGoods(String id) {
        jxcGoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageVO<JxcGoodsRespVO> getAllGoodsPageInfo(JxcGoodsReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<JxcGoodsRespVO> objList = jxcGoodsMapper.getAllGoods(vo);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public JxcGoodsRespVO getGoodsById(String id) {
        return jxcGoodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateGoods(JxcGoods obj) {
        JxcGoodsReqVO vo = new JxcGoodsReqVO();
        vo.setGoodsName(obj.getGoodsName());
        vo.setGoodsModel(obj.getGoodsModel());
        List<JxcGoodsRespVO> objList = jxcGoodsMapper.getAllGoods(vo);
        if(objList != null && objList.size() > 1){
            //该型号的商品名称以及存在了
            throw new ServiceException(BaseResponseCode.GOODS_NAME_MODEL_EXIST);
        }
        obj.setUpdateTime(new Date());
        jxcGoodsMapper.updateByPrimaryKeySelective(obj);
    }







    @Override
    public void addInGoods(JxcInStore obj) {
        //新增商品入库信息
        obj.setCreateTime(new Date());
        obj.setId(Tool.getPrimaryKey());
        obj.setGoodsTotalPrice(obj.getGoodsPrice().multiply(obj.getInCount()));//计算商品入库总价
        jxcInStoreMapper.insertSelective(obj);
        //根据[商品编号和仓库编号]查询库存信息，有库存就更新，没有就新增库存信息
        JxcStore storeVO = jxcStoreMapper.selectByGoodsCodeWhCode(obj.getGoodsCode(), obj.getWhCode());
        if(storeVO != null){
            //更新库存信息
            storeVO.setGoodsUnit(obj.getGoodsUnit());
            storeVO.setStoreCount(storeVO.getStoreCount().add(obj.getInCount()));//计算库存总数量
            jxcStoreMapper.updateByPrimaryKeySelective(storeVO);
        }else{
            //新增库存信息
            JxcStore store= new JxcStore();
            store.setId(Tool.getPrimaryKey());
            store.setUpdateUser(obj.getCreateUser());
            store.setUpdateTime(new Date());
            store.setGoodsCode(obj.getGoodsCode());
            store.setGoodsUnit(obj.getGoodsUnit());
            store.setStoreCount(obj.getInCount());
            store.setWarnCount(obj.getInCount().subtract(new BigDecimal(1)));//默认库存预警数量=第一次入库数量-1
            store.setWhCode(obj.getWhCode());
            jxcStoreMapper.insertSelective(store);
        }
    }

    @Override
    public void deleteInGoods(String id) {
        jxcInStoreMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageVO<JxcInStoreRespVO> getAllInGoodsPageInfo(JxcInStoreReqVo vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<JxcInStoreRespVO> objList = jxcInStoreMapper.selectAll(vo);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public JxcInStoreReqVo getInGoodsById(String id) {
        return jxcInStoreMapper.selectByPrimaryKey(id);
    }






    //库存出库
    @Override
    public synchronized void addOutGoods(JxcOutStore obj) {
        //根据商品编号查询对应库存信息
        JxcStore store = jxcStoreMapper.selectByGoodsCodeWhCode(obj.getGoodsCode(), obj.getWhCode());
        if(store != null){
            BigDecimal storeCount = store.getStoreCount();//总库存数量
            BigDecimal outCount = obj.getOutCount();//出库数量
            /**
             * result的值
             * -1：前小于后
             *  0：相等
             *  1：前大于后
             */
            int result = storeCount.compareTo(outCount);//比较总库存数量与出库数量
            if(result == -1){
                //库存不足
                throw new ServiceException(BaseResponseCode.STORE_NOT_ENOUGH);
            }else{
                //出库操作
                obj.setId(Tool.getPrimaryKey());
                obj.setCreateTime(new Date());
                jxcOutStoreMapper.insertSelective(obj);
                //更新库存数量
                store.setStoreCount(store.getStoreCount().subtract(obj.getOutCount()));//新的库存数量=总库存-出库数量
                jxcStoreMapper.updateByPrimaryKeySelective(store);
            }
        }else{
            throw new ServiceException(BaseResponseCode.GOODS_CODE_NO_EXIST);
        }
    }

    @Override
    public void deleteOutGoods(String id) {
        jxcOutStoreMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageVO<JxcOutStoreRespVO> getAllOutGoodsPageInfo(JxcOutStoreReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<JxcOutStoreRespVO> objList = jxcOutStoreMapper.getAllOutStore(vo);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public JxcOutStoreRespVO getOutGoodsById(String id) {
        return jxcOutStoreMapper.selectByPrimaryKey(id);
    }








    @Override
    public PageVO<JxcStoreRespVO> getAllStorePageInfo(JxcStoreReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<JxcStoreRespVO> objList = jxcStoreMapper.selectAll(vo);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public void updateStore(JxcStore obj) {
        JxcStore store = new JxcStore();
        store.setId(obj.getId());
        store.setWarnCount(obj.getWarnCount());
        store.setUpdateTime(new Date());
        jxcStoreMapper.updateByPrimaryKeySelective(store);
    }
}
