package com.ego.protal.controller;

import com.ego.protal.service.TbContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class TbContentController {
    @Resource
    private TbContentService tbContentServiceImpl;
    
    @RequestMapping("showBigPic")
    public String showBigPic(Model model) {
        model.addAttribute("ad1", tbContentServiceImpl.showBigPic());
        return "index";
    }
}
