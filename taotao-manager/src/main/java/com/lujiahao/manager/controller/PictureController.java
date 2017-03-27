package com.lujiahao.manager.controller;

import com.lujiahao.common.utils.JsonUtils;
import com.lujiahao.manager.service.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片上传controller
 * Created by ljh on 2017/3/17.
 */
@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;


    // 原系统中的ftp上传内容   但是这一点我在docker上面没法实现  搞不定呢
    @RequestMapping(value = "/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        Map result = pictureService.uploadPicture(uploadFile);
        // 如果直接返回map的话,在firefox中是无法使用的 侧面看出此插件的兼容性是有问题的
        String json = JsonUtils.objectToJson(result);
        return json;
    }

}
