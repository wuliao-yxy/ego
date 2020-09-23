package com.ego.passport.service;

import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TbUserService {
    /**
     * 用户登录
     * @param tbUser
     * @return
     */
    EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response);

    /**
     * 通过token查询用户信息
     * @param token
     * @return
     */
    EgoResult getUserInfoByToken(String token);

    /**
     * 退出
     * @param token
     * @param request
     * @param response
     * @return
     */
    EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response);
}
