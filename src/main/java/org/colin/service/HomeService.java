package org.colin.service;

import org.colin.model.vo.resp.*;

import java.util.List;

public interface HomeService {

    List<TableStructureVO> selectTabelByName(String tableName);

    List<TableVO> selectAllTable();

    HomeRespVO getHomeInfo(String userId);

    //获取用户登录归属地信息
    List<EchartsVO> getIpAddressInfo();

    //获取main.html页面中的数据信息
    MainVO getMainInfo();
}
