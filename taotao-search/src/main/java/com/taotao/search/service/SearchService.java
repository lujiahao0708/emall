package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * Created by lujiahao on 2016/10/24.
 */
public interface SearchService {
    SearchResult search(String queryString, int page, int rows) throws Exception;
}
