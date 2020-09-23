package com.ego.order.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemchild;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.IDUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.order.pojo.MyOrderParam;
import com.ego.order.service.TbOrderService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;
import com.ego.redis.dao.JedisDao;
import com.ego.service.TbItemService;
import com.ego.service.TbOrderDubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class TbOrderServiceImpl implements TbOrderService {
    @Resource
    private JedisDao jedisDaoImpl;
    @Reference
    private TbItemService tbItemServiceImpl;
    @Reference
    private TbOrderDubboService tbOrderDubboServiceImpl;
    @Value("${cart.key}")
    private String cartKey;
    @Value("${passport.url}")
    private String passportUrl;

    @Override
    public List<TbItemchild> showOrderCart(List<Long> ids, HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        String result = HttpClientUtil.doPost(passportUrl + token);

        EgoResult egoResult = JsonUtils.jsonToPojo(result, EgoResult.class);

        String key = cartKey+((LinkedHashMap)egoResult.getData()).get("username");
        String json = jedisDaoImpl.get(key);
        List<TbItemchild> list = JsonUtils.jsonToList(json, TbItemchild.class);

        List<TbItemchild> listNew = new ArrayList<>();
        for (TbItemchild tbItemchild : list) {
            for (Long id:ids) {
                if ((long)tbItemchild.getId()==(long)id) {
                    TbItem tbItem = tbItemServiceImpl.selById(id);
                    if (tbItem.getNum()>tbItemchild.getNum()) {
                        tbItemchild.setEnough(true);
                    } else {
                        tbItemchild.setEnough(false);
                    }
                    listNew.add(tbItemchild);
                }
            }
        }

        return listNew;
    }

    @Override
    public EgoResult create(MyOrderParam param, HttpServletRequest request) {
        //订单表数据
        TbOrder order = new TbOrder();
        order.setPayment(param.getPayment());
        order.setPaymentType(param.getPaymentType());
        long id = IDUtils.genItemId();
        order.setOrderId(id+"");
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);

        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        String result = HttpClientUtil.doPost(passportUrl + token);
        EgoResult egoResult = JsonUtils.jsonToPojo(result, EgoResult.class);
        Map user = (LinkedHashMap)egoResult.getData();

        order.setUserId(Long.parseLong(user.get("id").toString()));
        order.setBuyerNick(user.get("username").toString());
        order.setBuyerRate(0);

        //订单-商品表数据
        for (TbOrderItem orderItem : param.getOrderItems()) {
            orderItem.setId(IDUtils.genItemId()+"");
            orderItem.setOrderId(id+"");
        }

        //收货人信息
        TbOrderShipping tbOrderShipping = new TbOrderShipping();
        tbOrderShipping.setOrderId(id+"");
        tbOrderShipping.setUpdated(date);
        tbOrderShipping.setCreated(date);

        EgoResult er = new EgoResult();

        try {
            int index = tbOrderDubboServiceImpl.insOrder(order, param.getOrderItems(), tbOrderShipping);
            if (index>0) {
                er.setStatus(200);
                //删除购买的商品
                String json = jedisDaoImpl.get(cartKey + user.get("username"));
                List<TbItemchild> tbItemchildren = JsonUtils.jsonToList(json, TbItemchild.class);
                List<TbItemchild> tbItemchildList = new ArrayList<>();
                for (TbItemchild tbItemchild : tbItemchildren) {
                    for (TbOrderItem orderItem : param.getOrderItems()) {
                        if ((long)tbItemchild.getId()==Long.parseLong(orderItem.getItemId())) {
                            tbItemchildList.add(tbItemchild);
                        }
                    }
                }

                //删除
                for (TbItemchild tbItemchild : tbItemchildList) {
                    tbItemchildren.remove(tbItemchild);
                }
                jedisDaoImpl.set(cartKey+user.get("username"), JsonUtils.objectToJson(tbItemchildren));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return er;
    }
}
