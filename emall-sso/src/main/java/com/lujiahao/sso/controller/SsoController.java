package com.lujiahao.sso.controller;

import com.lujiahao.common.utils.CookieUtils;
import com.lujiahao.sso.domain.Const;
import com.lujiahao.sso.domain.EDataType;
import com.lujiahao.sso.domain.UserDTO;
import com.lujiahao.sso.service.IUserService;
import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.common.utils.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * 登录
 *
 * @author lujiahao
 * @date 2017/10/17
 */
@Controller
@RequestMapping(value = "/user")
public class SsoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SsoController.class);

    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse userLogin(@RequestBody UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ServerResponse.error("用户名或密码为空");
        }
        ServerResponse serverResponse = iUserService.userLogin(username, password);
        if (serverResponse.isSuccess()) {
            // 添加写cookie的逻辑  cookie有效期是关闭浏览器失效
            CookieUtils.setCookie(request, response, Const.COOKIE_TOKEN, serverResponse.getData().toString());
        }
        return serverResponse;
    }

    /**
     * 注销登录
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public ServerResponse userLogout(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtils.getCookieValue(request, Const.COOKIE_TOKEN);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.error("用户信息不存在");
        }
        ServerResponse serverResponse = iUserService.userLogout(token);
        if (serverResponse.isSuccess()) {
            CookieUtils.deleteCookie(request, response, Const.COOKIE_TOKEN);
        }
        return serverResponse;
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse createUser(UserDTO userDTO) {
        int resultCount = iUserService.createUser(userDTO);
        if (resultCount > 0) {
            return ServerResponse.success();
        } else {
            return ServerResponse.build(500, "用户注册失败");
        }
    }

    /**
     * 通过token查询用户信息
     */
    @RequestMapping(value = "/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback) {
        ServerResponse result = null;
        try {
            result = iUserService.getUserByToken(token);
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
    @RequestMapping(value = "/check/{param}/{type}")
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
        result = iUserService.checkData(param, type);
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
    @RequestMapping("/getPwdQuestion")
    @ResponseBody
    public ServerResponse<String> getPwdQuestion(String username) {
        if (StringUtils.isNoneBlank(username)) {
            return iUserService.selectQuestionByUsername(username);
        }
        return ServerResponse.error("用户名未空");
    }

    /**
     * 2.校验找回密码答案是否正确
     */
    @RequestMapping(value = "/validPwdAnswer",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> validPwdAnswer(String username, String question, String answer) {
        if (StringUtils.isNoneBlank(username) && StringUtils.isNoneBlank(question) && StringUtils.isNoneBlank(answer)) {
            return iUserService.validPwdAnswer(username, question, answer);
        }
        return ServerResponse.error("参数不正确");
    }

    /**
     * 3.修改密码
     */
    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPwd(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isNoneBlank(username) && StringUtils.isNoneBlank(passwordNew) && StringUtils.isNoneBlank(forgetToken)) {
            return iUserService.resetPwd(username, passwordNew, forgetToken);
        }
        return ServerResponse.error("参数传递错误");
    }
}
