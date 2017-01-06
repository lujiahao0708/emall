package com.taotao.sso.dao;

import com.taotao.pojo.TbUser;

/**
 * 用户管理的dao
 * Created by lujiahao on 2017/1/6.
 */
public interface UserDao {
    TbUser getUserByUsername(String username);
}
