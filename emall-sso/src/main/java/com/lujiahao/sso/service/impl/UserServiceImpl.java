package com.lujiahao.sso.service.impl;

import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.common.enums.EResponseCode;
import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.sso.cache.ILocalCache;
import com.lujiahao.sso.dao.UserMapper;
import com.lujiahao.sso.domain.EDataType;
import com.lujiahao.sso.domain.UserDTO;
import com.lujiahao.sso.domain.pojo.User;
import com.lujiahao.sso.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * 用户管理Service
 *
 * @author lujiahao
 * @date 2016/10/20
 */
@Service
public class UserServiceImpl implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ILocalCache localCache;

    @Override
    public ServerResponse userLogin(UserDTO userDTO) {
        try {
            String username = userDTO.getUsername();
            String password = userDTO.getPassword();
            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                return ServerResponse.error("用户名或密码为空");
            }
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            User userLogin = userMapper.userLogin(username, md5Password);
            if (userLogin == null) {
                // 不能返回没有此用户名 没用用户名也返回这个信息是因为防止猜测用户名
                return ServerResponse.error("用户名或密码错误");
            }
            // 保存用户信息前先把密码清除,为了安全起见
            userLogin.setPassword(StringUtils.EMPTY);
            String token = UUID.randomUUID().toString();
            // 这里采用接口编程的方式,到底用redis还是用guava
            boolean isSaveSuccess = localCache.setCache(token, userLogin);
            if (isSaveSuccess) {
                return ServerResponse.success(token);
            } else {
                LOGGER.info("========== 用户信息保存缓存失败 ==========");
                return ServerResponse.error("登录失败,请重试!");
            }
        } catch (Exception e) {
            ExceptionUtil.getStackTrace(e);
            return ServerResponse.error("服务器异常");
        }
    }

    @Override
    public ServerResponse userLogout(String token) {
        try {
            if (StringUtils.isBlank(token)) {
                return ServerResponse.error("用户信息不存在");
            }
            Object cache = localCache.getCache(token);
            if (cache == null) {
                return ServerResponse.error("用户信息不存在");
            }
            boolean isCleanSuccess = localCache.cleanCache(token);
            if (isCleanSuccess) {
                return ServerResponse.success("注销成功");
            } else {
                LOGGER.error("注销失败 token:{}", token);
                return ServerResponse.error("注销失败");
            }
        } catch (Exception e) {
            LOGGER.error("注销异常", ExceptionUtil.getStackTrace(e));
            return ServerResponse.error("注销失败");
        }
    }

    /**
     * 注册
     */
    @Override
    public ServerResponse<String> createUser(UserDTO userDTO) {
        try {
            ServerResponse validResponse = this.checkData(userDTO.getUsername(), EDataType.USERNAME.getValue());
            if (!validResponse.isSuccess()) {
                return validResponse;
            }
            validResponse = this.checkData(userDTO.getPhone(), EDataType.PHONE.getValue());
            if (!validResponse.isSuccess()) {
                return validResponse;
            }
            validResponse = this.checkData(userDTO.getEmail(), EDataType.EMAIL.getValue());
            if (!validResponse.isSuccess()) {
                return validResponse;
            }
            User user = new User();
            user.setUsername(userDTO.getUsername());
            // spring框架中的工具类  md5加密  这个加密是用来防止内部人员的,为了不能直接看出密码来
            user.setPassword(DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()));
            user.setPhone(userDTO.getPhone());
            user.setEmail(userDTO.getEmail());
            user.setQuestion(userDTO.getQuestion());
            user.setAnswer(userDTO.getAnswer());
            int resultCount = userMapper.insertSelective(user);
            if (resultCount > 0) {
                return ServerResponse.success("注册成功");
            } else {
                return ServerResponse.error("注册失败");
            }
        } catch (Exception e) {
            LOGGER.error("用户注册 异常:",e);
            return ServerResponse.error(EResponseCode.SERVER_ERROR.getCode(), "注册失败");
        }
    }

    /**
     * 校验数据
     *
     * @param content 数据内容
     * @param type    数据类型
     */
    @Override
    public ServerResponse checkData(String content, Integer type) {
        try {
            int result;
            if (EDataType.USERNAME.getValue() == type) {
                result = userMapper.checkUsername(content);
                if (result > 0) {
                    return ServerResponse.error("用户名已存在");
                }
            } else if (EDataType.PHONE.getValue() == type) {
                result = userMapper.checkPhone(content);
                if (result > 0) {
                    return ServerResponse.error("手机号已存在");
                }
            } else if (EDataType.EMAIL.getValue() == type) {
                result = userMapper.checkEmail(content);
                if (result > 0) {
                    return ServerResponse.error("邮箱已存在");
                }
            }
            return ServerResponse.success(true);
        } catch (Exception e) {
            return ServerResponse.error();
        }
    }

    /**
     * 根据token查询用户信息
     */
    @Override
    public ServerResponse getUserByToken(String token) {
        try {
            User user = (User) localCache.getCache(token);
            if (user == null) {
                return ServerResponse.error("用户信息已过期,请重新登录!");
            }
            return ServerResponse.success(user);
        } catch (Exception e) {
            return ServerResponse.error("用户信息异常,请重新登录!");
        }
    }
}
