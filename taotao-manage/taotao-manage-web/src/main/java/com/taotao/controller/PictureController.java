package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 上传图片处理
 *
 * @author lujiahao
 * @version V1.0
 * @email jiahao.lu@qtparking.com
 * @create 2016-09-02 18:05
 */
@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping(value="/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        Map result = pictureService.uploadPicture(uploadFile);
        // 为了保证功能的兼容性,要把map转换成json字符串
        String json = JsonUtils.objectToJson(result);
        return json;
    }
}
