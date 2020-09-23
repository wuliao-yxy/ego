package com.ego.service.impl;

import com.ego.mapper.TbUserMapper;
import com.ego.pojo.TbUser;
import com.ego.pojo.TbUserExample;
import com.ego.service.TbUserDubboService;

import javax.annotation.Resource;
import java.util.List;

public class TbUserDubboServiceImpl implements TbUserDubboService {
    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser selByUser(TbUser tbUser) {
        TbUserExample example = new TbUserExample();
        example.createCriteria().andUsernameEqualTo(tbUser.getUsername()).andPasswordEqualTo(tbUser.getPassword());
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if (list!=null && list.size()>0) {
            return list.get(0);
        }
        return null;
    }
}
