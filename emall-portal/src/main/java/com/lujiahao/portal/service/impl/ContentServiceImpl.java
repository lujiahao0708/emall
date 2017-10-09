package com.lujiahao.portal.service.impl;


import com.lujiahao.common.pojo.CommonResult;
import com.lujiahao.common.utils.HttpClientUtil;
import com.lujiahao.common.utils.JsonUtils;
import com.lujiahao.mapping.pojo.TbContent;
import com.lujiahao.portal.service.ContentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lujiahao
 * @version V1.0
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-12 17:19
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;

    @Override
    public String getContentList() {
        // 调用服务层服务
        String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
        // 把字符串转换成TaotaoResult
        try {
            CommonResult commonResult = CommonResult.formatToList(result, TbContent.class);
            // 取内容列表
            List<TbContent> list = (List<TbContent>) commonResult.getData();
            List<Map> resultList = new ArrayList<>();
            // 创建一个jsp页面要求的pojo列表
            for (TbContent tbContent : list) {
                Map map = new HashMap();
                map.put("src", tbContent.getPic());
                map.put("height", 240);
                map.put("width", 670);
                map.put("srcB", tbContent.getPic2());
                map.put("widthB", 550);
                map.put("heightB", 240);
                map.put("href", tbContent.getUrl());
                map.put("alt", tbContent.getSubTitle());
                resultList.add(map);
            }
            return JsonUtils.objectToJson(resultList);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
