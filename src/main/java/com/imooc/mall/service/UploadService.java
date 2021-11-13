package com.imooc.mall.service;

import java.io.File;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 描述：     上传服务
 */
public interface UploadService {

    String uploadImage(MultipartFile file) throws IOException;

    String getNewFileName(@RequestParam("file") MultipartFile multipartFile);

    String uploadFile(MultipartFile file);

    void createFile(@RequestParam("file") MultipartFile file, File fileDirectory, File destFile);
}
