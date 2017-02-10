package com.lujiahao.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 淘淘商城后台管理系统
 * 页面跳转Controller
 * @author lujiahao
 *
 */
@Controller
public class PageController {

	/**
	 * 打开首页
	 */
	@RequestMapping(value="/")
	public String showIndex(){
		return "index";
	}
	
	/**
	 * 系统使用的是easyui开发
     * 这里传入页面的名称直接跳转到相应的页面
     * 展示其他界面
	 */
	@RequestMapping(value="/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
}
