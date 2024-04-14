package com.codeyuaiiao.controller;

import com.codeyuaiiao.config.RestResult;
import com.codeyuaiiao.config.ResultGenerator;
import com.codeyuaiiao.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {
    @Autowired
    AliOSSUtils aliOSSUtils;
    @Autowired
    private ResultGenerator generator;
    @PostMapping("/upload")
    public RestResult upload(MultipartFile image) throws IOException {
        //阿里云返回存储到bucket的图片的url
        String url = aliOSSUtils.upload(image);
        return generator.getSuccessResult("success", (Object) url);
    }
}
