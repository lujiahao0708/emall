package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by lujiahao on 2016/10/24.
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws Exception;
}
