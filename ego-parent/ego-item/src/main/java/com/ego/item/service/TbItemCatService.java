package com.ego.item.service;

import com.ego.item.pojo.PortalMenu;

public interface TbItemCatService {
    /**
     * 查询所有分类类目并转换为正确的数据类型
     * @return
     */
    PortalMenu showCatMenu();
}
