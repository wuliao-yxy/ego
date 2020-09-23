package com.ego.service;

import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;

import java.util.List;

public interface TbOrderDubboService {
	/**
	 * 创建订单
	 * @param order
	 * @param list
	 * @param shipping
	 * @return
	 * @throws Exception 
	 */
	int insOrder(TbOrder order,List<TbOrderItem> list,TbOrderShipping shipping) throws Exception;
}
