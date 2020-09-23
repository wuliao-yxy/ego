package com.ego.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cart.service.CartService;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemchild;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.JsonUtils;
import com.ego.pojo.TbItem;
import com.ego.redis.dao.JedisDao;
import com.ego.service.TbItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private JedisDao jedisDaoImpl;
    @Reference
    private TbItemService tbItemServiceImpl;
    @Value("${passport.url}")
    private String passportUrl;
    @Value(("${cart.key}"))
    private String cartKey;

    @Override
    public void addCart(long id, int num, HttpServletRequest request) {
        //集合中存放所有购物车商品
        List<TbItemchild> list = new ArrayList<>();

        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        String jsonUser = HttpClientUtil.doPost(passportUrl + token);
        EgoResult egoResult = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);

        //redis中的key
        String key = cartKey + ((LinkedHashMap) egoResult.getData()).get("username");

        //如果redis中存在key
        if (jedisDaoImpl.exists(key)) {
            String json = jedisDaoImpl.get(key);
            if (json!=null&&!json.equals("")) {
                list = JsonUtils.jsonToList(json, TbItemchild.class);
                for (TbItemchild tbItemchild:list) {
                    if ((long)tbItemchild.getId()==id) {
                        tbItemchild.setNum(tbItemchild.getNum()+num);

                        //把信息重新添加到redis
                        jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
                        return;
                    }
                }
            }
        }

        //redis对应的value为空或不存在
        TbItem tbItem = tbItemServiceImpl.selById(id);
        TbItemchild tbItemchild = new TbItemchild();

        tbItemchild.setId(tbItem.getId());
        tbItemchild.setTitle(tbItem.getTitle());
        tbItemchild.setImages(tbItem.getImage()==null||tbItem.getImage().equals("")?new String[1]:tbItem.getImage().split(","));
        tbItemchild.setNum(num);
        tbItemchild.setPrice(tbItem.getPrice());
         
        list.add(tbItemchild);

        jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
    }

    @Override
    public List<TbItemchild> showCart(HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        String jsonUser = HttpClientUtil.doPost(passportUrl + token);
        EgoResult egoResult = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);

        //redis中的key
        String key = cartKey + ((LinkedHashMap) egoResult.getData()).get("username");

        String json = jedisDaoImpl.get(key);
        List<TbItemchild> list = JsonUtils.jsonToList(json, TbItemchild.class);
        return list;
    }

    @Override
    public EgoResult update(long id, int num, HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        String jsonUser = HttpClientUtil.doPost(passportUrl + token);
        EgoResult egoResult = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);

        //redis中的key
        String key = cartKey + ((LinkedHashMap) egoResult.getData()).get("username");

        String json = jedisDaoImpl.get(key);
        List<TbItemchild> list = JsonUtils.jsonToList(json, TbItemchild.class);

        for (TbItemchild tbItemchild : list) {
            if ((long)tbItemchild.getId()==id) {
                tbItemchild.setNum(num);
            }
        }

        String set = jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
        EgoResult result = new EgoResult();
        if (set.equals("OK")) {
            result.setStatus(200);
        }

        return result;
    }

    @Override
    public EgoResult delete(long id, HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        String jsonUser = HttpClientUtil.doPost(passportUrl + token);
        EgoResult egoResult = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);

        //redis中的key
        String key = cartKey + ((LinkedHashMap) egoResult.getData()).get("username");

        String json = jedisDaoImpl.get(key);
        List<TbItemchild> list = JsonUtils.jsonToList(json, TbItemchild.class);
        TbItemchild itemchild = null;

        for (TbItemchild tbItemchild : list) {
            if ((long)tbItemchild.getId()==id) {
                itemchild = tbItemchild;
            }
        }

        list.remove(itemchild);
        String set = jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
        EgoResult result = new EgoResult();
        if (set.equals("OK")) {
            result.setStatus(200);
        }

        return result;
    }
}
