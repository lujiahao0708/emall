package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * 搜索服务
 * Created by lujiahao on 2016/10/30.
 */
public interface SearchService {
    SearchResult search(String queryString,int page);
}
