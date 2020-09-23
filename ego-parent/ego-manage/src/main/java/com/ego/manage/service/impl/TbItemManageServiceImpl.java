package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.utils.IDUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.manage.service.TbItemManageService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;
import com.ego.redis.dao.JedisDao;
import com.ego.service.TbItemDescDubboService;
import com.ego.service.TbItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TbItemManageServiceImpl implements TbItemManageService {
    @Reference
    private TbItemService tbItemServiceImpl;
    @Reference
    private TbItemDescDubboService tbItemDescDubboServiceImpl;
    @Value("${search.url}")
    private String url;
    @Resource
    private JedisDao jedisDaoImpl;
    @Value("${redis.item.key}")
    private String itemKey;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        return tbItemServiceImpl.show(page, rows);
    }

    @Override
    public int update(String ids, byte status) {
        int index = 0;
        TbItem tbItem = new TbItem();
        String[] id = ids.split(",");
        for (String idstr: id) {
            tbItem.setId(Long.parseLong(idstr));
            tbItem.setStatus(status);
            index += tbItemServiceImpl.updateStatus(tbItem);
            if (status == 2 || status == 3) {
                jedisDaoImpl.del(itemKey+id);
            }
        }
        if (index == id.length) {
            return 1;
        }
        return 0;
    }

    //	// 不采用事务回滚新增商品和描述
//	@Override
//	public int insert(TbItem item, String desc) {
//		long id = IDUtils.genItemId();
//		item.setId(id);
//		Date date = new Date();
//		item.setCreated(date);
//		item.setUpdated(date);
//		item.setStatus((byte) 1);
//		int index = tbItemDubboServiceImpl.insert(item);
//		if (index > 0) {
//			TbItemDesc itemDesc = new TbItemDesc();
//			itemDesc.setItemDesc(desc);
//			itemDesc.setItemId(id);
//			itemDesc.setCreated(date);
//			itemDesc.setUpdated(date);
//			index += tbItemDescDubboServiceImpl.insertDesc(itemDesc);
//		}
//		if (index == 2) {
//			return 1;
//		}
//		return 0;
//	}

    // 采用事务回滚添加
    // 因为使用solr搜索,是从已初始化的solr内进行搜索，添加商品后，因为要保证前台使用solr搜索的时候能完成同步，就要在实行商品新增方法sava(),添加后使用httpClient技术
    // 访问ego-search项目中的TbItemController类的add控制器,在ego-search中要新建一个添加的方法,添加到solr搜索内，这样前台使用solr搜索的时候就能搜到刚刚添加的商品
    @Override
    public int save(TbItem item, String desc, String itemParams) throws Exception {
        long id = IDUtils.genItemId();
        item.setId(id);
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        item.setStatus((byte) 1);

        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(id);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);

        TbItemParamItem record = new TbItemParamItem();
        record.setCreated(date);
        record.setItemId(id);
        record.setParamData(itemParams);
        record.setUpdated(date);

        int index = 0;
        index = tbItemServiceImpl.insTbItmDesc(item, itemDesc, record);

        final TbItem itemFinal = item;
        final String descFinal = desc;
        new Thread() {
            public void run() {
                Map<String, Object> map = new HashMap<>();
                map.put("item", itemFinal);
                map.put("desc", descFinal);
                // 使用java代码调用其他项目的控制器
                HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
            };
        }.start();

        return index;
    }
}
