package com.lujiahao.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
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
	 * 展示其他界面
	 */
	@RequestMapping(value="/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
}
