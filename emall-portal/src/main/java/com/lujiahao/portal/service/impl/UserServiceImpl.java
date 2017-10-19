package com.lujiahao.portal.service.impl;


import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.common.utils.HttpClientUtil;
import com.lujiahao.mapping.pojo.TbUser;
import com.lujiahao.portal.service.UserService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 获取用户信息
 * Created by lujiahao on 2016/11/1.
 */
@Service
public class UserServiceImpl implements UserService {
    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;
    @Value("${SSO_PAGE_LOGIN}")
    public String SSO_PAGE_LOGIN;

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    @Override
    public TbUser getUserByToken(String token) {
        try {
            // 调用sso系统服务,根据token获取用户信息
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            // 把json转换成TaoTaoResult
            ServerResponse serverResponse = ServerResponse.formatToPojo(json, TbUser.class);
            if (serverResponse.getStatus() == 200) {
                TbUser tbUser = (TbUser) serverResponse.getData();
                return tbUser;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
