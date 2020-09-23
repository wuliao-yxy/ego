package com.ego.cart.interceptor;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//购物车拦截器
public class LoginInterceptor implements HandlerInterceptor {
    @Value("http://localhost:8084/user/token/")
    private String passportUrl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断有没有登录，登录了并且登录成功，就return true，放行
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        if (!StringUtils.isEmpty(token)) {
            String json = HttpClientUtil.doPost(passportUrl + token);
            EgoResult egoResult = JsonUtils.jsonToPojo(json, EgoResult.class);
            if (egoResult.getStatus()==200) {
                return true;
            }
        }

        //如果没有登录，则重定向到登录页面
        String num = request.getParameter("num");
        response.sendRedirect("http://localhost:8084/user/showLogin?interurl="+request.getRequestURL()+"%3Fnum=" +num);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
