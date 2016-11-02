package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品搜索
 * Created by lujiahao on 2016/10/30.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;
    @Override
    public SearchResult search(String queryString, int page) {
        Map<String,String> param = new HashMap<>();
        param.put("q",queryString);
        param.put("page",page + "");
        try {
            // 调用taotao-search服务
            String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
            if (200 == taotaoResult.getStatus()) {
                SearchResult searchResult = (SearchResult) taotaoResult.getData();
                return searchResult;
            }
        } catch (Exception e){
            e.printStackTrace();
            ExceptionUtil.getStackTrace(e);
        }
        return null;
    }
}
