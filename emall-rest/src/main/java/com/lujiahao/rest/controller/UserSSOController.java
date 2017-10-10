package com.lujiahao.rest.controller;

import com.lujiahao.common.pojo.CommonResult;
import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.mapping.pojo.TbUser;
import com.lujiahao.rest.domain.EDataType;
import com.lujiahao.rest.domain.UserVO;
import com.lujiahao.rest.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 内容分类
 * Created by lujiahao on 2017/10/10.
 */
@Controller
@RequestMapping(value = "/user")
public class UserSSOController {
    @Autowired
    private UserService userService;

    /**
     * 校验数据是否可用
     *
     * @param param 需要校验的数据内容
     * @param type  数据类型
     *              callback    是个回调地址,就是这个进入这个页面之前访问的地址
     * @return
     */
    @RequestMapping(value = "/check/{param}/{type}")
    @ResponseBody
    public Object checkData(HttpServletRequest request, @PathVariable String param, @PathVariable Integer type) {
        String callback = request.getParameter("callback");
        CommonResult result = null;
        // 参数有效性校验
        if (StringUtils.isBlank(param)) {
            result = CommonResult.build(400, "校验内容不能为空");
        }
        if (type == null) {
            result = CommonResult.build(400, "校验内容类型不能为空");
        }
        if (type != EDataType.USERNAME.getValue() && type != EDataType.PHONE.getValue() && type != EDataType.EMAIL.getValue()) {
            result = CommonResult.build(400, "校验内容类型错误");
        }
        // 校验出错
        if (result != null) {
            return checkCallBack(callback, result);
        }
        try {
            // 调用服务
            result = userService.checkData(param, type);
        } catch (Exception e) {
            result = CommonResult.build(500, ExceptionUtil.getStackTrace(e));
            e.printStackTrace();
        }
        return checkCallBack(callback, result);
    }

    /**
     * 解决JSONP跨域请求
     *
     * @param callback 回调地址
     * @param result   返回的JavaBean
     * @return
     */
    private Object checkCallBack(String callback, CommonResult result) {
        if (callback != null) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        } else {
            return result;
        }
    }

    /**
     * 创建用户
     * TODO 更好的用户体验是登录之后能把用户名带过去,这个以前的那个管理后台可以做到  仿照一下看看
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createUser(UserVO userVO) {
        int resultCount = userService.createUser(userVO);
        if (resultCount > 0) {
            return CommonResult.ok();
        } else {
            return CommonResult.build(500, "用户注册失败");
        }
    }

    /**
     * 用户登录
     * TODO JSONP跨域没有解决
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object userLogin(UserVO userVO, HttpServletRequest request, HttpServletResponse response,String callback) {
        try {
            CommonResult commonResult = userService.userLogin(userVO, request, response);
            // 支持JSONP跨域
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(commonResult);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    /**
     * 通过token查询用户信息
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback) {
        CommonResult result = null;
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = CommonResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        // 判断是否为jsonp调用
        if (StringUtils.isBlank(callback)) {
            return result;// 不是jsonp调用
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
    }
}
