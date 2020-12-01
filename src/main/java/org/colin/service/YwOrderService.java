package org.colin.service;

import org.colin.model.ro.OrderRO;
import org.colin.model.vo.resp.YwOrderDetailVO;
import org.colin.model.vo.resp.YwOrderVO;

import java.util.List;

public interface YwOrderService {

    YwOrderVO addOrder(OrderRO ro);

    List<YwOrderDetailVO> getAllOrderByOrderNum(String orderNum);
}
