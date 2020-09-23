package com.ego.search.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.TbItemchild;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemDesc;
import com.ego.search.service.TbItemSearchService;
import com.ego.service.TbContentCategoryDubboService;
import com.ego.service.TbItemCatDubboService;
import com.ego.service.TbItemDescDubboService;
import com.ego.service.TbItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbItemSearchServiceImpl implements TbItemSearchService {
    @Reference
    private TbItemService tbItemDubboServiceImpl;
    @Reference
    private TbContentCategoryDubboService tbContentCategoryDubboServiceImpl;
    @Reference
    private TbItemDescDubboService tbItemDescDubboServiceImpl;
    @Reference
    private TbItemCatDubboService tbItemCatDubboServiceImpl;
    @Resource
    private CloudSolrClient solrClient;

    @Override
    public void init() throws IOException, SolrServerException {
        //查询所有正常的商品
        List<TbItem> listItem = tbItemDubboServiceImpl.selAllByStatus((byte) 1);
        for (TbItem item:listItem) {
            //商品对应类目信息
            TbItemCat cat = tbItemCatDubboServiceImpl.selById(item.getCid());
            //商品对应的描述信息
            TbItemDesc desc = tbItemDescDubboServiceImpl.selByItemid(item.getId());

            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", item.getId());
            doc.addField("item_title", item.getTitle());
            doc.addField("item_sell_point", item.getSellPoint());
            doc.addField("item_price", item.getPrice());
            doc.addField("item_image", item.getImage());
            doc.addField("item_category_name", cat.getName());
            doc.addField("item_desc", desc.getItemDesc());
            solrClient.add(doc);
        }
    }

    @Override
    public Map<String, Object> selByQuery(String query, int page, int rows) throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        //设置分页条件
        solrQuery.setStart(rows*(page-1));
        solrQuery.setRows(rows);
        //设置条件
        solrQuery.setQuery("item_keywords:"+query);
        //设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");

        //添加排序
        solrQuery.setSort("item_updated", SolrQuery.ORDER.desc);

        QueryResponse queryResponse = solrClient.query(solrQuery);

        List<TbItemchild> listChild = new ArrayList<>();

        //未高亮内容
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //高亮内容
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

        for (SolrDocument doc:solrDocumentList) {
            TbItemchild tbItemchild = new TbItemchild();

            tbItemchild.setId(Long.parseLong(doc.getFieldValue("id").toString()));
            List<String> list = highlighting.get(doc.getFieldValue("id")).get("item_title");
            if (list!=null&&list.size()>0) {
                tbItemchild.setTitle(list.get(0));
            } else {
                tbItemchild.setTitle(doc.getFieldValue("item_title").toString());
            }

            tbItemchild.setPrice((long)doc.getFieldValue("item_price"));
            Object images = doc.getFieldValue("item_images");
            tbItemchild.setImages(images==null||images.equals("")?new String[1]:images.toString().split(","));

            listChild.add(tbItemchild);
        }

        Map<String, Object>  resultMap = new HashMap<>();
        resultMap.put("itemList", listChild);
        resultMap.put("totalPages", solrDocumentList.getNumFound()%rows==0?solrDocumentList.getNumFound()/rows:solrDocumentList.getNumFound()/rows+1);

        return resultMap;
    }

    @Override
    public int add(Map<String, Object> map, String desc) throws SolrServerException, IOException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id", map.get("id"));
        doc.setField("item_title", map.get("title"));
        doc.setField("item_sell_point", map.get("sellPoint"));
        doc.setField("item_price", map.get("price"));
        doc.setField("item_image", map.get("image"));
        doc.setField("item_category_name", tbContentCategoryDubboServiceImpl.selById((Integer) map.get("cid")).getName());
        doc.setField("item_desc", desc);
        UpdateResponse response = solrClient.add(doc);
        //千万不要忘了提交
        solrClient.commit();
        if (response.getStatus() == 0) {
            return 1;
        }
        return 0;
    }
}
