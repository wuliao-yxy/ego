package com.ego.manage.controller;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbItemManageService;
import com.ego.pojo.TbItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TbItemManageController {
    @Resource
    private TbItemManageService tbItemManageServiceImpl;
    /**
     * 显示商品
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("item/list")
    @ResponseBody
    public EasyUIDataGrid show(int page, int rows) {
        return tbItemManageServiceImpl.show(page, rows);
    }

    /**
     * 商品上架
     * @param ids
     * @return
     */
    @RequestMapping("rest/item/reshelf")
    @ResponseBody
    public EgoResult reshelf(String ids) {
        EgoResult egoResult = new EgoResult();
        int index = tbItemManageServiceImpl.update(ids, (byte) 1);
        if (index==1) {
            egoResult.setStatus(200);
        }
        return egoResult;
    }
    /**
     * 商品下架
     * @param ids
     * @return
     */
    @RequestMapping("rest/item/instock")
    @ResponseBody
    public EgoResult instock(String ids) {
        EgoResult egoResult = new EgoResult();
        int index = tbItemManageServiceImpl.update(ids, (byte) 2);
        if (index==1) {
            egoResult.setStatus(200);
        }
        return egoResult;
    }
    /**
     * 商品删除
     * @param ids
     * @return
     */
    @RequestMapping("rest/item/delete")
    @ResponseBody
    public EgoResult delete(String ids) {
        EgoResult egoResult = new EgoResult();
        int index = tbItemManageServiceImpl.update(ids, (byte) 3);
        if (index==1) {
            egoResult.setStatus(200);
        }
        return egoResult;
    }

    //	不考虑事务回滚的商品新增
//	@RequestMapping("item/save")
//	@ResponseBody
//	public EgoResult insert1(TbItem item,String desc) {
//		EgoResult er = new EgoResult();
//		int index = tableServiceImpl.insert(item, desc);
//		if (index == 1) {
//			er.setStatus(200);
//		}
//		return er;
//	}
    /**
     * 商品新增，考虑事务回滚
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping("item/save")
    @ResponseBody
    public EgoResult insert(TbItem item, String desc, String itemParams) {
        EgoResult er = new EgoResult();
        int index;
        try {
            index = tbItemManageServiceImpl.save(item, desc,itemParams);
            if (index == 1) {
                er.setStatus(200);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            er.setData(e.getMessage());
        }
        return er;
    }
}
