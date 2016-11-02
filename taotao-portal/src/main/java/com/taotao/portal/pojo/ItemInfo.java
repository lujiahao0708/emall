package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * 商品的pojo
 * 因为item中没有TbItem的getImages方法  这个是个临时补救的方法
 * Created by lujiahao on 2016/10/31.
 */
public class ItemInfo extends TbItem {
    public String[] getImages(){
        String image = getImage();
        if (image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
