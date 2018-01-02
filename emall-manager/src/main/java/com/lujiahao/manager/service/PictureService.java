package com.lujiahao.manager.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片上传
 * Created by ljh on 2017/3/16.
 */
public interface PictureService {
    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    Map uploadPicture(MultipartFile multipartFile);
}
