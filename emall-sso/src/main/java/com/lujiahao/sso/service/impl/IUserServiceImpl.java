package com.lujiahao.sso.service.impl;

import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.common.enums.EResponseCode;
import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.mapping.mapper.EmallUserMapper;
import com.lujiahao.mapping.pojo.EmallUser;
import com.lujiahao.sso.domain.Const;
import com.lujiahao.sso.domain.EDataType;
import com.lujiahao.sso.domain.UserDTO;
import com.lujiahao.sso.service.IUserService;
import com.lujiahao.sso.utils.cache.ILocalCache;
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
public class IUserServiceImpl implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IUserServiceImpl.class);

    @Autowired
    private EmallUserMapper emallUserMapper;

    @Autowired
    private ILocalCache iLocalCache;

    @Override
    public ServerResponse userLogin(String username, String password) {
        try {
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            EmallUser emallUser = emallUserMapper.userLogin(username, md5Password);
            if (emallUser == null) {
                // 不能返回没有此用户名 没用用户名也返回这个信息是因为防止猜测用户名
                return ServerResponse.error("用户名或密码错误");
            }
            // 保存用户信息前先把密码清除,为了安全起见
            emallUser.setPassword(StringUtils.EMPTY);
            String token = UUID.randomUUID().toString();
            // 这里采用接口编程的方式,到底用redis还是用guava
            boolean isSaveSuccess = iLocalCache.setCache(token, emallUser);
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
            boolean isCleanSuccess = iLocalCache.cleanCache(token);
            if (isCleanSuccess) {
                return ServerResponse.success("注销成功");
            } else {
                LOGGER.info("========== 注销失败 ==========");
                return ServerResponse.error("注销失败");
            }
        } catch (Exception e) {
            LOGGER.error("========== 注销异常 ==========", ExceptionUtil.getStackTrace(e));
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
            EmallUser emallUser = new EmallUser();
            emallUser.setUsername(userDTO.getUsername());
            // spring框架中的工具类  md5加密  这个加密是用来防止内部人员的,为了不能直接看出密码来
            emallUser.setPassword(DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()));
            emallUser.setPhone(userDTO.getPhone());
            emallUser.setEmail(userDTO.getEmail());
            emallUser.setQuestion(userDTO.getQuestion());
            emallUser.setAnswer(userDTO.getAnswer());
            int resultCount = emallUserMapper.insertSelective(emallUser);
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
                result = emallUserMapper.checkUsername(content);
                if (result > 0) {
                    return ServerResponse.error("用户名已存在");
                }
            } else if (EDataType.PHONE.getValue() == type) {
                result = emallUserMapper.checkPhone(content);
                if (result > 0) {
                    return ServerResponse.error("手机号已存在");
                }
            } else if (EDataType.EMAIL.getValue() == type) {
                result = emallUserMapper.checkEmail(content);
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
            EmallUser user = (EmallUser) iLocalCache.getCache(token);
            if (user == null) {
                return ServerResponse.error("用户信息已过期,请重新登录!");
            }
            return ServerResponse.success(user);
        } catch (Exception e) {
            return ServerResponse.error("用户信息异常,请重新登录!");
        }
    }

    // 找回密码流程 start

    /**
     * 1.根据用户名获取找回密码问题
     */
    @Override
    public ServerResponse selectQuestionByUsername(String username) {
        try {
            ServerResponse validResponse = this.checkData(username, EDataType.USERNAME.getValue());
            if (validResponse.isSuccess()) {
                return ServerResponse.error("用户不存在");
            }
            String question = emallUserMapper.selectQuestionByUsername(username);
            if (StringUtils.isNoneBlank(question)) {
                return ServerResponse.success(question);
            }
            return ServerResponse.error("未查询到找回密码问题");
        } catch (Exception e) {
            return ServerResponse.error();
        }
    }

    /**
     * 2.校验找回密码答案是否正确
     */
    @Override
    public ServerResponse validPwdAnswer(String username, String question, String answer) {
        int result = emallUserMapper.validPwdAnswer(username, question, answer);
        if (result > 0) {
            // 答案正确
            String forgetToken = UUID.randomUUID().toString();
            iLocalCache.setCache(Const.CACHE_TOKEN + username, forgetToken);
            return ServerResponse.success(forgetToken);
        }
        return ServerResponse.error("答案错误");
    }

    /**
     * 3.修改密码
     */
    @Override
    public ServerResponse resetPwd(String username, String passwordNew, String forgetToken) {
        try {
            ServerResponse validResponse = this.checkData(username, EDataType.USERNAME.getValue());
            if (validResponse.isSuccess()) {
                return ServerResponse.error("用户名不存在");
            }
            String token = (String) iLocalCache.getCache(Const.CACHE_TOKEN + username);
            if (StringUtils.isBlank(token)) {
                return ServerResponse.error("token无效或过期");
            }
            if (StringUtils.equals(forgetToken, token)) {
                String md5Pwd = DigestUtils.md5DigestAsHex(passwordNew.getBytes());
                int result = emallUserMapper.updatePwdByUsername(username, md5Pwd);
                if (result > 0) {
                    return ServerResponse.success("密码修改成功");
                } else {
                    return ServerResponse.error("密码修改失败");
                }
            } else {
                return ServerResponse.error("token信息不匹配,请重新获取重置密码token");
            }
        } catch (Exception e) {
            LOGGER.error("========== 修改密码 异常 ==========username:" + username, e);
            return ServerResponse.error("密码修改失败,请重试!");
        }
    }

    // 找回密码流程 end
}
