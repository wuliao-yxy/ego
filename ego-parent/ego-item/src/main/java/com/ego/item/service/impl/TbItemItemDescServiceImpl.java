package com.ego.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.item.service.TbItemItemDescService;
import com.ego.redis.dao.JedisDao;
import com.ego.service.TbItemDescDubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TbItemItemDescServiceImpl implements TbItemItemDescService {
    @Reference
    private TbItemDescDubboService tbItemDescDubboServiceImpl;
    @Resource
    private JedisDao jedisDaoImpl;
    @Value("${redis.desc.key}")
    private String descKey;

    @Override
    public String show(long itemId) {
        String key = descKey + itemId;
        if (jedisDaoImpl.exists(key)) {
            String json = jedisDaoImpl.get(key);
            if (json!=null&&!json.equals("")) {
                return json;
            }
        }

        String result = tbItemDescDubboServiceImpl.selByItemid(itemId).getItemDesc();
        jedisDaoImpl.set(key, result);
        return result;
    }
}
