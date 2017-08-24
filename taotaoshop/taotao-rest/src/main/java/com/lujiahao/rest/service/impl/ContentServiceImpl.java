package com.lujiahao.rest.service.impl;

import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.common.utils.JsonUtils;
import com.lujiahao.mapping.mapper.TbContentMapper;
import com.lujiahao.mapping.pojo.TbContent;
import com.lujiahao.mapping.pojo.TbContentExample;
import com.lujiahao.rest.dao.JedisClientDao;
import com.lujiahao.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容服务
 *
 * @author lujiahao
 * @version V1.0
 * @email xinruodingshui@gmail.com
 * @create 2016-09-12 15:37
 */
@Service
public class ContentServiceImpl implements ContentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentServiceImpl.class);
    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClientDao jedisClientDao;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;
    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    @Override
    public List<TbContent> getContentList(long contentCategoryId) {
        // 从缓存中取内容
        try {
            String result = jedisClientDao.hget(INDEX_CONTENT_REDIS_KEY, contentCategoryId + "");
            if (!StringUtils.isBlank(result)) {
                // 将从redis中取得的字符串转换成list
                List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
                LOGGER.debug("========== 使用Redis缓存 ==========" );
                LOGGER.debug(resultList.toString());
                return resultList;
            }
        } catch (Exception e){
            e.printStackTrace();
            ExceptionUtil.getStackTrace(e);
        }

        // 根据内容分类id查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCategoryId);
        // 执行查询
        List<TbContent> list = tbContentMapper.selectByExample(example);

        // 向缓存中添加内容
        try {
            String cacheStr = JsonUtils.objectToJson(list);
            long result = jedisClientDao.hset(INDEX_CONTENT_REDIS_KEY, contentCategoryId + "", cacheStr);
            LOGGER.debug("========== 向Redis添加缓存 ==========" );
            LOGGER.debug(cacheStr);
        } catch (Exception e){
            e.printStackTrace();
            ExceptionUtil.getStackTrace(e);
        }
        return list;
    }
}
