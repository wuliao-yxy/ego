package com.ego.search.controller;

import com.ego.search.service.TbItemSearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class TbItemController {
    @Resource
    private TbItemSearchService tbItemSearchServiceImpl;

    /**
     * 初始化solr词典里的数据
     *
     * @return
     */
    @RequestMapping(value="solr/init",produces="text/html;charset=utf-8")
    @ResponseBody
    public String init() {
        long start = System.currentTimeMillis();
        try {
            tbItemSearchServiceImpl.init();
            long end = System.currentTimeMillis();
            return "初始化总时间:"+(end-start)/1000+"秒";
        } catch (SolrServerException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "初始化失败";
        }
    }

    @RequestMapping("login")
    public String login() {
        return "index";
    }

    /**
     * 实现商品搜索
     * @param model
     * @param q
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("search.html")
    public String search(Model model, String q, @RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="12") int rows) {
        try {
            q = new String(q.getBytes("iso-8859-1"),"utf-8");//设置编码格式，防止中文乱码
            Map<String, Object> map = tbItemSearchServiceImpl.selByQuery(q, page, rows);
            model.addAttribute("query",q);
            model.addAttribute("itemList", map.get("itemList"));
            model.addAttribute("totalPages", map.get("totalPages"));
            model.addAttribute("page", page);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "search";
    }

    @RequestMapping("solr/add")
    @ResponseBody
    public int add(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        System.out.println(map.get("item"));
        try {
            return tbItemSearchServiceImpl.add((LinkedHashMap)map.get("item"), map.get("desc").toString());
        } catch (SolrServerException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
}
