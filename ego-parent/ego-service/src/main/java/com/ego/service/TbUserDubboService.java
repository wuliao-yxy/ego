package com.ego.service;

import com.ego.pojo.TbUser;

public interface TbUserDubboService {
    /**
     * 根据用户查询登录
     * @param tbUser
     * @return
     */
    TbUser selByUser(TbUser tbUser);
}
