package com.lujiahao.portal.service.impl;


import com.lujiahao.common.pojo.CommonResult;
import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.common.utils.HttpClientUtil;
import com.lujiahao.common.utils.JsonUtils;
import com.lujiahao.mapping.pojo.TbItemDesc;
import com.lujiahao.mapping.pojo.TbItemParamItem;
import com.lujiahao.portal.pojo.ItemInfo;
import com.lujiahao.portal.service.ItemService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lujiahao on 2016/10/31.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;
    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;
    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;

    /**
     * 根据商品id查询商品基本信息
     *
     * @param itemId
     * @return
     */
    @Override
    public ItemInfo getItemById(Long itemId) {
        try {
            // 调用rest服务
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            if (!StringUtils.isBlank(json)) {
                CommonResult commonResult = CommonResult.formatToPojo(json, ItemInfo.class);
                if (commonResult.getStatus() == 200) {
                    ItemInfo item = (ItemInfo) commonResult.getData();
                    return item;
                }
            }
        } catch (Exception e) {
            ExceptionUtil.getStackTrace(e);
        }

        return null;
    }

    /**
     * 通过商品id查询商品的描述信息
     *
     * @param itemId
     * @return
     */
    @Override
    public String getItemDescById(Long itemId) {
        try {
            // 查询商品描述
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
            // 转换成java对象
            CommonResult commonResult = CommonResult.formatToPojo(json, TbItemDesc.class);
            if (commonResult.getStatus() == 200) {
                TbItemDesc tbItemDesc = (TbItemDesc) commonResult.getData();
                // 取出商品描述信息
                String itemDesc = tbItemDesc.getItemDesc();
                return itemDesc;
            }
        } catch (Exception e) {
            ExceptionUtil.getStackTrace(e);
        }
        return null;
    }

    /**
     * 根据商品id查询商品规格参数
     *
     * @param itemId
     * @return
     */
    @Override
    public String getItemParamById(Long itemId) {
        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            CommonResult commonResult = CommonResult.formatToPojo(json, TbItemParamItem.class);
            if (commonResult.getStatus() == 200) {
                TbItemParamItem itemParamItem = (TbItemParamItem) commonResult.getData();
                String paramData = itemParamItem.getParamData();
                //生成html
                // 把规格参数json数据转换成java对象
                List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
                sb.append("    <tbody>\n");
                for(Map m1:jsonList) {
                    sb.append("        <tr>\n");
                    sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
                    sb.append("        </tr>\n");
                    List<Map> list2 = (List<Map>) m1.get("params");
                    for(Map m2:list2) {
                        sb.append("        <tr>\n");
                        sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                        sb.append("            <td>"+m2.get("v")+"</td>\n");
                        sb.append("        </tr>\n");
                    }
                }
                sb.append("    </tbody>\n");
                sb.append("</table>");
                //返回html片段
                return sb.toString();
            }
        } catch (Exception e) {
            ExceptionUtil.getStackTrace(e);
        }
        return "";
    }
}
