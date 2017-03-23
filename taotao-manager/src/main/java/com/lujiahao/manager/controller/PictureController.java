package com.lujiahao.manager.controller;

import com.lujiahao.common.utils.HttpClientUtil;
import com.lujiahao.common.utils.JsonUtils;
import com.lujiahao.manager.service.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传controller
 * Created by ljh on 2017/3/17.
 */
@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        Map result = pictureService.uploadPicture(uploadFile);
        // 如果直接返回map的话,在firefox中是无法使用的 侧面看出此插件的兼容性是有问题的
        String json = JsonUtils.objectToJson(result);
        return json;
    }

    @RequestMapping(value = "/pic/upload1")
    @ResponseBody
    public String pictureUpload1(MultipartFile uploadFile) throws IOException {
        String url = "http://up.imgapi.com";
        Map<String,String> param = new HashMap<String,String>();
        param.put("Token","6f6581feb2d8f88769fe70d05b4856c307a43ea0:BOXF2wi0kh29-v-6U2ONyZ5JnKE=:eyJkZWFkbGluZSI6MTQ5MDA3NTc0OCwiYWN0aW9uIjoiZ2V0IiwidWlkIjoiNTg4NzU4IiwiYWlkIjoiMTI4NzYxNyIsImZyb20iOiJmaWxlIn0=");
        param.put("file",uploadFile.getInputStream().toString());
        String s = HttpClientUtil.doPost(url, param);
        return s;
    }
}
