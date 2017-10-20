package com.lujiahao.manager.service.impl;


import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.common.utils.HttpClientUtil;
import com.lujiahao.manager.service.ContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 内容管理服务
 *
 * @author lujiahao
 * @version V1.0
 * @email xinruodingshui@gmail.com
 * @create 2016-09-12 17:13
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

    /**
     * 添加首页广告轮播图内容
     * @param tbContent
     * @return
     */
    @Override
    public ServerResponse insertContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        tbContentMapper.insert(tbContent);

        // 添加缓存同步逻辑
        try {
            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + tbContent.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
            // 通知管理员 缓存同步失败  发短信or发邮件
        }
        return ServerResponse.success();
    }
}
