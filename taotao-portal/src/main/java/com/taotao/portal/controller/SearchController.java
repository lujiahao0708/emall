package com.taotao.portal.controller;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * 商品搜索的Controller
 * Created by lujiahao on 2016/10/30.
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search")
    public String search(@RequestParam("q")String queryString, @RequestParam(defaultValue = "1")Integer page, Model model){
        if (queryString != null) {
            try {
                queryString = new String(queryString.getBytes("ISO8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        SearchResult searchResult = searchService.search(queryString, page);
        // 向页面传递参数
        model.addAttribute("query",queryString);
        model.addAttribute("totalPages",searchResult.getPageCount());
        model.addAttribute("itemList",searchResult.getItemList());
        model.addAttribute("page",page);
        return "search";
    }
}
