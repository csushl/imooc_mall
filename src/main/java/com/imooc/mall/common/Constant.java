package com.imooc.mall.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：     常量值
 */
@Configuration
public class Constant {
    public static final String IMOOC_MALL_USER = "imooc_mall_user";
    public static final String SALT = "8svbsvjkweDF,.03[";

    public static String ICODE;

//    @Value("${icode}")
//    public void setICODE(String icode) {
//        ICODE = icode;
//    }
}
