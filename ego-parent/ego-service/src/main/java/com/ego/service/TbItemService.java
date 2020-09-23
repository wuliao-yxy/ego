package com.ego.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;

import java.util.List;

public interface TbItemService {
    /**
     * 商品分页查询
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid show(int page, int rows);

    /**
     * 根据id修改状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(TbItem tbItem);

    /**
     * 商品新增
     * @param tbItem
     * @return
     */
    int insert(TbItem item);

    /**
     * 新增包含商品表和商品描述表
     * @param tbItem
     * @param desc
     * @return
     */
    int insTbItmDesc(TbItem tbItem, TbItemDesc desc, TbItemParamItem record) throws Exception;
    /**
     * 通过状态查询全部可用数据
     * @param status
     * @return
     */
    List<TbItem> selAllByStatus(byte status);
    /**
     * 根据主键id查询商品信息
     * @param id
     * @return
     */
    TbItem selById(Long id);
}
