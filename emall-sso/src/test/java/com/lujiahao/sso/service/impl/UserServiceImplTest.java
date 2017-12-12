package com.lujiahao.sso.service.impl;

import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.sso.SsoApplication;
import com.lujiahao.sso.domain.UserDTO;
import com.lujiahao.sso.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;
    @Test
    public void userLogin() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("lujiahao");
        userDTO.setPassword("123456");
        ServerResponse serverResponse = userService.userLogin(userDTO);
        System.out.println(serverResponse.toString());
    }

    @Test
    public void userLogout() throws Exception {
        ServerResponse serverResponse = userService.userLogout("hahaha");
        System.out.println(serverResponse.toString());
    }

}