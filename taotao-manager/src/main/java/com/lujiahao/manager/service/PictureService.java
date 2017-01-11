package com.lujiahao.manager.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片上传的service
 * Created by lujiahao
 * Created at 2016/9/1 19:05
 */
public interface PictureService {
    Map uploadPicture(MultipartFile uploadFile);
}
