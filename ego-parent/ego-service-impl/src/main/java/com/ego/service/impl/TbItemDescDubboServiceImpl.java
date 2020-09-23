package com.ego.service.impl;

import com.ego.mapper.TbItemDescMapper;
import com.ego.pojo.TbItemDesc;
import com.ego.service.TbItemDescDubboService;

import javax.annotation.Resource;

public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {
	@Resource
	private TbItemDescMapper tbItemDescMapper;
	@Override
	public int insertDesc(TbItemDesc desc) {
		return tbItemDescMapper.insert(desc);
	}
	@Override
	public TbItemDesc selByItemid(long itemid) {
		return tbItemDescMapper.selectByPrimaryKey(itemid);
	}

}
