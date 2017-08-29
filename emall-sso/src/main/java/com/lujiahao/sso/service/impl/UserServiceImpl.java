package com.lujiahao.sso.service.impl;


import com.lujiahao.common.pojo.TaotaoResult;
import com.lujiahao.common.utils.CookieUtils;
import com.lujiahao.common.utils.JsonUtils;
import com.lujiahao.mapping.mapper.TbUserMapper;
import com.lujiahao.mapping.pojo.TbUser;
import com.lujiahao.mapping.pojo.TbUserExample;
import com.lujiahao.sso.dao.JedisClientDao;
import com.lujiahao.sso.domain.EDataType;
import com.lujiahao.sso.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户管理Service
 * Created by lujiahao on 2016/10/31.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private JedisClientDao jedisClientDao;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;
    @Value("${COOKIE_TOKEN}")
    private String COOKIE_TOKEN;

    /**
     * 校验数据
     *
     * @param content 数据内容
     * @param type    数据类型
     */
    @Override
    public TaotaoResult checkData(String content, Integer type) {
        // 创建查询条件
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        // 对数据进行校验 1/2/3分别代表username/phone/email
        // 用户名校验
        if (EDataType.USERNAME.getValue() == type) {
            criteria.andUsernameEqualTo(content);
        } else if (EDataType.PHONE.getValue() == type) {
            criteria.andPhoneEqualTo(content);// 手机号校验
        } else if (EDataType.EMAIL.getValue() == type) {
            criteria.andEmailEqualTo(content);// email校验
        }
        // 执行查询
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return TaotaoResult.ok(true);
        }
        return TaotaoResult.ok(false);
    }

    /**
     * 创建用户
     *
     * @param tbUser 用户信息
     */
    @Override
    public TaotaoResult createUser(TbUser tbUser) {
        Date nowDate = new Date();
        tbUser.setUpdated(nowDate);
        tbUser.setCreated(nowDate);
        // spring框架中的工具类  md5加密  这个加密是用来防止内部人员的,为了不能直接看出密码来
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
        tbUserMapper.insert(tbUser);
        return TaotaoResult.ok();
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @param response @return
     */
    @Override
    public TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = tbUserMapper.selectByExample(example);
        // 如果没有此用户名
        if (list == null || list.size() == 0) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }
        TbUser tbUser = list.get(0);
        // 对比密码
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }
        // 生成token
        String token = UUID.randomUUID().toString();
        // 保存用户信息前先把密码清除,为了安全起见
        tbUser.setPassword(null);
        // 把用户信息写入redis
        jedisClientDao.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(tbUser));
        // 设置session过期时间
        jedisClientDao.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 添加写cookie的逻辑  cookie有效期是关闭浏览器失效
        CookieUtils.setCookie(request, response, COOKIE_TOKEN, token);
        // 返回token
        return TaotaoResult.ok(token);
    }

    /**
     * 根据token查询用户信息
     */
    @Override
    public TaotaoResult getUserByToken(String token) {
        // 根据token从redis中查询用户信息
        String json = jedisClientDao.get(REDIS_USER_SESSION_KEY + ":" + token);
        if (StringUtils.isBlank(json)) {
            return TaotaoResult.build(400, "此Session已经过期,请重新登录");
        }
        // 更新过期时间
        jedisClientDao.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 返回用户信息
        return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
    }
}
