package com.ego.service.impl;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.service.TbContentDubboService;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

public class TbContentDubboServiceImpl implements TbContentDubboService {
    @Resource
    private TbContentMapper tbContentMapper;

    @Override
    public EasyUIDataGrid selContentByPage(long categoryId, int page, int rows) {
        PageHelper.startPage(page, rows);

        TbContentExample example = new TbContentExample();
        if (categoryId != 0) {
            example.createCriteria().andCategoryIdEqualTo(categoryId);
        }

        List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbContent> pi = new PageInfo<>(list);
        EasyUIDataGrid grid = new EasyUIDataGrid();

        grid.setRows(pi.getList());
        grid.setTotal(pi.getTotal());

        return grid;
    }

    @Override
    public int insertContent(TbContent tbContent) {
        return tbContentMapper.insertSelective(tbContent);
    }

    @Override
    public int delContentById(long ids) {
        return 0;
    }

    @Override
    public int updContent(TbContent content) {
        return 0;
    }

    @Override
    public List<TbContent> selByCount(int count, boolean isSort) {
        System.out.println("selByCount");
        TbContentExample example = new TbContentExample();

        //排序
        if (isSort) {
            example.setOrderByClause("updated desc");
        }

        if (count != 0) {
            PageHelper.startPage(1, count);
            List<TbContent> listCount = tbContentMapper.selectByExampleWithBLOBs(example);
            PageInfo<TbContent> pi = new PageInfo<>(listCount);
            return pi.getList();
        }else {
            return tbContentMapper.selectByExampleWithBLOBs(example);
        }
    }
}
