package com.ego.item.controller;

import com.ego.item.service.TbItemItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class TbItemController {
    @Resource
    private TbItemItemService tbItemItemServiceImpl;
    @RequestMapping("item/{id}.html")
    public String showDetails(@PathVariable long id, Model model) {
        model.addAttribute("item", tbItemItemServiceImpl.show(id));
        return "item";
    }
}
