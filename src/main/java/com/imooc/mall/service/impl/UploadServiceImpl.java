package com.imooc.mall.service.impl;

import com.imooc.mall.common.Constant;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.service.UploadService;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 描述：     上传服务实现类
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${file.upload.uri}")
    String uri;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        String newFileName = getNewFileName(file);
        //创建文件
        File fileDirectory = new File(Constant.FILE_UPLOAD_DIR);
        File destFile = new File(Constant.FILE_UPLOAD_DIR + newFileName);
        createFile(file, fileDirectory, destFile);
        Thumbnails.of(destFile).size(Constant.IMAGE_SIZE, Constant.IMAGE_SIZE)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(Constant.FILE_UPLOAD_DIR + Constant.WATER_MARK_JPG)), Constant.IMAGE_OPACITY)
                .toFile(new File(Constant.FILE_UPLOAD_DIR + newFileName));
        String address = uri;
        String result = "http://" + address + "/images/" + newFileName;
        return result;
    }

    @Override
    public String getNewFileName(@RequestParam("file") MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成uuid
        UUID uuid = UUID.randomUUID();
        return uuid.toString() + suffixName;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称UUID
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + suffixName;
        //创建文件
        File fileDirectory = new File(Constant.FILE_UPLOAD_DIR);
        File destFile = new File(Constant.FILE_UPLOAD_DIR + newFileName);
        createFile(file, fileDirectory, destFile);
        String address = uri;
        String result = "http://" + address + "/images/" + newFileName;
        return result;
    }

    @Override
    public void createFile(@RequestParam("file") MultipartFile file, File fileDirectory, File destFile) {
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdir()) {
                throw new ImoocMallException(ImoocMallExceptionEnum.MKDIR_FAILED);
            }
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
