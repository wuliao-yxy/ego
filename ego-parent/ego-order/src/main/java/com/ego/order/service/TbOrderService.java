package com.ego.order.service;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemchild;
import com.ego.order.pojo.MyOrderParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TbOrderService {
    /**
     * 确认订单信息包含的商品
     * @return
     */
    List<TbItemchild> showOrderCart(List<Long> ids, HttpServletRequest request);

    /**
     * 创建订单
     * @param param
     * @param request
     * @return
     */
    EgoResult create(MyOrderParam param, HttpServletRequest request);
}
