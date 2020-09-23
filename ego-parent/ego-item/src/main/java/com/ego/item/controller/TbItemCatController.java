package com.ego.item.controller;

import com.ego.item.service.TbItemCatService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TbItemCatController {
    @Resource
    private TbItemCatService tbItemCatServiceImpl;

    @RequestMapping("rest/itemcat/all")
    @ResponseBody
    public MappingJacksonValue show(String callback) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(tbItemCatServiceImpl.showCatMenu());
        mappingJacksonValue.setJsonpFunction(callback);
        return  mappingJacksonValue;
    }
}
