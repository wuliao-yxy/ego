package com.ego.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    @RequestMapping("{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    /**
     * 显示商品修改
     * @return
     */
    @RequestMapping("item-edit")
    public String showItemEdit() {
        return "item-edit";
    }
}
