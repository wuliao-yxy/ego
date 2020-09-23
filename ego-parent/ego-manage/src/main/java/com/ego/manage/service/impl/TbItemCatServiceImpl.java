package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUITree;
import com.ego.manage.service.TbItemCatService;
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
	public List<EasyUITree> show(long pid) {
		List<TbItemCat> list = tbItemCatDubboServiceImpl.show(pid);
		List<EasyUITree> listTree = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			EasyUITree tree = new EasyUITree();
			tree.setId(tbItemCat.getId());
			tree.setText(tbItemCat.getName());
			tree.setState(tbItemCat.getIsParent()?"closed":"open");
			listTree.add(tree);
		}
		return listTree;
	}

}
