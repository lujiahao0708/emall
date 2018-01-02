package com.lujiahao.sso.controller;

import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.common.utils.CookieUtils;
import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.sso.domain.Const;
import com.lujiahao.sso.domain.EDataType;
import com.lujiahao.sso.domain.UserDTO;
import com.lujiahao.sso.service.IUserPwdService;
import com.lujiahao.sso.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 *
 * @author lujiahao
 * @date 2017/10/17
 */
@RestController
@CrossOrigin
public class SsoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SsoController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserPwdService userPwdService;

    /**
     * 用户登录
     */
    @PostMapping("/user/login")
    public ServerResponse userLogin(UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {
        ServerResponse serverResponse = userService.userLogin(userDTO);
        if (serverResponse.isSuccess()) {
            // 添加写cookie的逻辑  cookie有效期是关闭浏览器失效
            CookieUtils.setCookie(request, response, Const.COOKIE_TOKEN, serverResponse.getData().toString());
        }
        return serverResponse;
    }

    /**
     * 注销登录
     */
    @PostMapping("/user/logout")
    public ServerResponse userLogout(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtils.getCookieValue(request, Const.COOKIE_TOKEN);
        ServerResponse serverResponse = userService.userLogout(token);
        if (serverResponse.isSuccess()) {
            CookieUtils.deleteCookie(request, response, Const.COOKIE_TOKEN);
        }
        return serverResponse;
    }

    /**
     * 注册
     */
    @PostMapping("/user/register")
    public ServerResponse createUser(UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    /**
     * 通过token查询用户信息
     */
    @RequestMapping(value = "/user/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback) {
        ServerResponse result = null;
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServerResponse.build(500, ExceptionUtil.getStackTrace(e));
        }
        return jsonpCallback(callback, result);
    }

    /**
     * 校验数据是否可用
     *
     * @param param 需要校验的数据内容
     * @param type  数据类型 详见枚举:EDataType
     *              callback    是个回调地址,就是这个进入这个页面之前访问的地址
     */
    @RequestMapping(value = "/user/check/{param}/{type}")
    @ResponseBody
    public Object checkData(HttpServletRequest request, @PathVariable String param, @PathVariable Integer type) {
        String callback = request.getParameter("callback");
        ServerResponse result = null;
        // 参数有效性校验
        if (StringUtils.isBlank(param)) {
            return jsonpCallback(callback, ServerResponse.error(400, "校验内容不能为空"));
        }
        if (type == null) {
            return jsonpCallback(callback, ServerResponse.error(400, "校验内容类型不能为空"));
        }
        if (type != EDataType.USERNAME.getValue() && type != EDataType.PHONE.getValue() && type != EDataType.EMAIL.getValue()) {
            return jsonpCallback(callback, ServerResponse.error(400, "校验内容类型错误"));
        }
        result = userService.checkData(param, type);
        return jsonpCallback(callback, result);
    }

    /**
     * 解决JSONP跨域请求
     */
    private Object jsonpCallback(String callback, ServerResponse result) {
        if (callback != null) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        } else {
            return result;
        }
    }

    /**
     * 1.获取找回密码问题
     */
    @GetMapping("/user/getPwdQuestion")
    public ServerResponse<String> getPwdQuestion(String username) {
        return userPwdService.selectQuestionByUsername(username);
    }

    /**
     * 2.校验找回密码答案是否正确
     */
    @PostMapping("/user/validPwdAnswer")
    public ServerResponse<String> validPwdAnswer(String username, String question, String answer) {
        if (StringUtils.isNoneBlank(username) && StringUtils.isNoneBlank(question) && StringUtils.isNoneBlank(answer)) {
            return userPwdService.validPwdAnswer(username, question, answer);
        }
        return ServerResponse.error("参数不正确");
    }

    /**
     * 3.修改密码
     */
    @PostMapping("/user/resetPassword")
    public ServerResponse<String> resetPwd(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isNoneBlank(username) && StringUtils.isNoneBlank(passwordNew) && StringUtils.isNoneBlank(forgetToken)) {
            return userPwdService.resetPwd(username, passwordNew, forgetToken);
        }
        return ServerResponse.error("参数传递错误");
    }
}
