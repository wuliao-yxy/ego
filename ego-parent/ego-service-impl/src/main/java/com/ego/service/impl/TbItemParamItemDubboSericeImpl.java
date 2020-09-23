package com.ego.service.impl;

import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.TbItemParamItem;
import com.ego.pojo.TbItemParamItemExample;
import com.ego.service.TbItemParamItemDubboService;

import javax.annotation.Resource;
import java.util.List;

public class TbItemParamItemDubboSericeImpl implements TbItemParamItemDubboService {
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Override
	public TbItemParamItem selByItemid(long itemid) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(itemid);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
