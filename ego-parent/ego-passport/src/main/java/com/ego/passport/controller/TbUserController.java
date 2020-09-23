package com.ego.passport.controller;

import com.ego.commons.pojo.EgoResult;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TbUserController {
    @Resource
    private TbUserService tbUserServiceImpl;

    /**
     * 显示登陆界面
     *
     * @param url
     * @param model
     * @return
     */
    @RequestMapping("user/showLogin")
    public String showLogin(@RequestHeader("Referer") String url, Model model,String interurl) {
        if (interurl != null && !interurl.equals("")) {
            model.addAttribute("redirect", interurl);
        }else if (url != null && !url.equals("")) {
            model.addAttribute("redirect", url);
        }
        return "login";
    }

    /**
     * 登录
     */
    @RequestMapping("user/login")
    @ResponseBody
    public EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response) {
        return tbUserServiceImpl.login(tbUser, request, response);
    }

    /**
     * 通过token获取用户信息
     * @param token
     * @param callback
     * @return
     */
    @RequestMapping("user/token/{token}")
    @ResponseBody
    public Object getUserInfo(@PathVariable String token, String callback) {
        EgoResult egoResult = tbUserServiceImpl.getUserInfoByToken(token);
        if (egoResult!=null&&!egoResult.equals("")) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(egoResult);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }

        return egoResult;
    }

    /**
     * 退出
     * @param token
     * @param callback
     * @return
     */
    @RequestMapping("user/logout/{token}")
    @ResponseBody
    public Object logout(@PathVariable String token, String callback, HttpServletRequest request, HttpServletResponse response) {
        EgoResult egoResult = tbUserServiceImpl.logout(token, request, response);
        if (egoResult!=null&&!egoResult.equals("")) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(egoResult);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }

        return egoResult;
    }
}
