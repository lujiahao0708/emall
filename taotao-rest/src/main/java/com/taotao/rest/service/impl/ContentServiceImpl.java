package com.taotao.rest.service.impl;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.dao.JedisClientDao;
import com.taotao.rest.service.ContentService;
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
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-12 15:37
 */
@Service
public class ContentServiceImpl implements ContentService {
    private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);
    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClientDao jedisClientDao;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> getContentList(long categoryId) {
        // 从缓存中取内容
        try {
            String result = jedisClientDao.hget(INDEX_CONTENT_REDIS_KEY, categoryId + "");
            if (!StringUtils.isBlank(result)) {
                List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
                logger.debug("使用Redis缓存");
                return resultList;
            }
        } catch (Exception e){
            e.printStackTrace();
            ExceptionUtil.getStackTrace(e);
        }

        // 根据内容分类id查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        // 执行查询
        List<TbContent> list = tbContentMapper.selectByExample(example);

        // 向缓存中添加内容
        try {
            String cacheStr = JsonUtils.objectToJson(list);
            long result = jedisClientDao.hset(INDEX_CONTENT_REDIS_KEY, categoryId + "", cacheStr);
            logger.debug("向Redis添加缓存--->"+result);//向Redis添加缓存--->1
        } catch (Exception e){
            e.printStackTrace();
            ExceptionUtil.getStackTrace(e);
        }
        return list;
    }
}
