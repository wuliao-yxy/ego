package com.ego.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.item.pojo.PortalMenu;
import com.ego.item.pojo.PortalMenuNode;
import com.ego.item.service.TbItemCatService;
import com.ego.pojo.TbItemCat;
import com.ego.service.TbItemCatDubboService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {
    @Reference
    private TbItemCatDubboService tbItemCatDubboServiceImpl;

    @Override
    public PortalMenu showCatMenu() {
        //查询出所有一级菜单
        List<TbItemCat> list = tbItemCatDubboServiceImpl.show(0);
        PortalMenu portalMenu = new PortalMenu();
        portalMenu.setData(selAllMenu(list));
        return portalMenu;
    }

    /**
     * 最终返回所有查询到的结果
     * @param list
     * @return
     */
    public List<Object> selAllMenu(List<TbItemCat> list) {
        List<Object> nodeList = new ArrayList<>();
        for (TbItemCat tbItemCat:list) {
            if (tbItemCat.getIsParent()) {
                PortalMenuNode portalMenuNode = new PortalMenuNode();
                portalMenuNode.setU("/products/" + tbItemCat.getId() + ".html");
                portalMenuNode.setN("<a href='/products/" + tbItemCat.getId()+ ".html'>" + tbItemCat.getName() + "</a>");
                portalMenuNode.setI(selAllMenu(tbItemCatDubboServiceImpl.show(tbItemCat.getId())));
                nodeList.add(portalMenuNode);
            } else {
                nodeList.add("/products/" + tbItemCat.getId() + ".html" + tbItemCat.getName());
            }
        }
        return nodeList;
    }

}
