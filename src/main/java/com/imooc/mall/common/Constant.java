package com.imooc.mall.common;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * 描述：     常量值
 */
@Configuration
public class Constant {
    public static final String IMOOC_MALL_USER = "imooc_mall_user";
    public static final String SALT = "8svbsvjkweDF,.03[";

    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public interface ProductListOrderBy{

        Set<String> PRICE_ORDER_ENUM = Sets.newHashSet("price desc", "price asc");

    }

    public static String ICODE;

    @Value("${icode}")
    public void setICODE(String icode) {
        ICODE = icode;
    }
}
