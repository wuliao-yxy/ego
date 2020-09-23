package com.ego.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.TbItemchild;
import com.ego.commons.utils.JsonUtils;
import com.ego.item.service.TbItemItemService;
import com.ego.pojo.TbItem;
import com.ego.redis.dao.JedisDao;
import com.ego.service.TbItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TbItemItemServiceImpl implements TbItemItemService {
    @Reference
    private TbItemService tbItemServiceImpl;
    @Resource
    private JedisDao jedisDaoImpl;
    @Value("${redis.item.key}")
    private String itemKey;
    @Override
    public TbItemchild show(long id) {
        String key = itemKey+id;

        if (jedisDaoImpl.exists(key)) {
            String json = jedisDaoImpl.get(key);
            if (json!=null&&!json.equals("")) {
                return JsonUtils.jsonToPojo(json, TbItemchild.class);
            }
        }
            TbItem tbItem = tbItemServiceImpl.selById(id);
            TbItemchild tbItemchild = new TbItemchild();
            tbItemchild.setId(tbItem.getId());
            tbItemchild.setPrice(tbItem.getPrice());
            tbItemchild.setSellPoint(tbItem.getSellPoint());
            tbItemchild.setTitle(tbItem.getTitle());
            tbItemchild.setImages(tbItem.getImage() != null && !tbItem.equals("") ? tbItem.getImage().split(",") : new String[1]);

            //存到数据库中
            jedisDaoImpl.set(key, JsonUtils.objectToJson(tbItemchild));

            return tbItemchild;
    }
}
