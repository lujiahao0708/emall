package com.lujiahao.sso.service.impl;

import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.sso.cache.ILocalCache;
import com.lujiahao.sso.dao.UserPwdMapper;
import com.lujiahao.sso.domain.Const;
import com.lujiahao.sso.domain.EDataType;
import com.lujiahao.sso.service.IUserPwdService;
import com.lujiahao.sso.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * 用户密码相关service
 * @author lujiahao
 * @date 2017-12-11 下午6:55
 */
@Service
public class UserPwdServiceImpl implements IUserPwdService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPwdServiceImpl.class);

    @Autowired
    private UserPwdMapper userPwdMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private ILocalCache localCache;

    // 找回密码流程 start
    /**
     * 1.根据用户名获取找回密码问题
     */
    @Override
    public ServerResponse selectQuestionByUsername(String username) {
        try {
            if (StringUtils.isBlank(username)) {
                LOGGER.warn("获取找回密码问题 用户名为空");
                return ServerResponse.error("用户名为空");
            }
            ServerResponse validResponse = userService.checkData(username, EDataType.USERNAME.getValue());
            if (validResponse.isSuccess()) {
                return ServerResponse.error("用户不存在");
            }
            String question = userPwdMapper.selectQuestionByUsername(username);
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
        int result = userPwdMapper.validPwdAnswer(username, question, answer);
        if (result > 0) {
            // 答案正确
            String forgetToken = UUID.randomUUID().toString();
            localCache.setCache(Const.CACHE_TOKEN + username, forgetToken);
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
            ServerResponse validResponse = userService.checkData(username, EDataType.USERNAME.getValue());
            if (validResponse.isSuccess()) {
                return ServerResponse.error("用户名不存在");
            }
            String token = (String) localCache.getCache(Const.CACHE_TOKEN + username);
            if (StringUtils.isBlank(token)) {
                return ServerResponse.error("token无效或过期");
            }
            if (StringUtils.equals(forgetToken, token)) {
                String md5Pwd = DigestUtils.md5DigestAsHex(passwordNew.getBytes());
                int result = userPwdMapper.updatePwdByUsername(username, md5Pwd);
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
