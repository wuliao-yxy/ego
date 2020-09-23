package com.ego.passport.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisDao;
import com.ego.service.TbUserDubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Reference
    private TbUserDubboService tbUserDubboServiceImpl;
    @Resource
    private JedisDao jedisDaoImpl;

    @Override
    public EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response) {
        EgoResult egoResult = new EgoResult();
        TbUser user = tbUserDubboServiceImpl.selByUser(tbUser);
        if (user!=null) {
            egoResult.setStatus(200);
            //用户登录成功后把信息存储到redis中
            String key = UUID.randomUUID().toString();
            jedisDaoImpl.set(key, JsonUtils.objectToJson(user));
            jedisDaoImpl.expire(key, 60*60*24*7);
            //参数Cookie
            CookieUtils.setCookie(request, response, "TT_TOKEN", key, 60*60*24*7);
        } else {
            egoResult.setMsg("用户名或密码错误");
        }
        return egoResult;
    }

    @Override
    public EgoResult getUserInfoByToken(String token) {
        String json = jedisDaoImpl.get(token);
        EgoResult egoResult = new EgoResult();

        if (json!=null&&!json.equals("")){
            TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
            //清空密码
            tbUser.setPassword(null);
            egoResult.setStatus(200);
            egoResult.setMsg("ok");
            egoResult.setData(tbUser);
        } else {
            egoResult.setMsg("获取失败");
        }
        return egoResult;
    }

    @Override
    public EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
        jedisDaoImpl.del(token);
        CookieUtils.deleteCookie(request, response, token);
        EgoResult result = new EgoResult();
        result.setStatus(200);
        result.setMsg("OK");
        return result;
    }
}
