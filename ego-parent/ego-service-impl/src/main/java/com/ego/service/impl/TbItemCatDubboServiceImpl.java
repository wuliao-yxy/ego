package com.ego.service.impl;

import com.ego.service.TbItemCatDubboService;
import com.ego.mapper.TbItemCatMapper;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemCatExample;

import javax.annotation.Resource;
import java.util.List;

public class TbItemCatDubboServiceImpl implements TbItemCatDubboService {
    @Resource
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> show(long pid) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        tbItemCatExample.createCriteria().andParentIdEqualTo(pid);
        return tbItemCatMapper.selectByExample(tbItemCatExample);
    }

    @Override
    public TbItemCat selById(long id) {
        return tbItemCatMapper.selectByPrimaryKey(id);
    }
}
