package com.imooc.mall.common;

import com.google.common.collect.Sets;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：     常量值
 */
@Component
public class Constant {

    public static final String IMOOC_MALL_USER = "imooc_mall_user";
    public static final String SALT = "8svbsvjkweDF,.03[";
    public static final String EMAIL_SUBJECT = "您的验证码";
    public static final String EMAIL_FROM = "1234554321@qq.com";
    public static final String WATER_MARK_JPG = "watermark.jpg";
    public static final Integer IMAGE_SIZE = 400;
    public static final Float IMAGE_OPACITY = 0.5f;

    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public interface ProductListOrderBy {

        Set<String> PRICE_ORDER_ENUM = Sets.newHashSet("price desc", "price asc");
    }

    public interface SaleStatus {

        int NOT_SALE = 0;//商品下架状态
        int SALE = 1;//商品上架状态
    }

    public interface Cart {

        Integer NOT_SELECTED = 0;//购物车未选中
        Integer SELECTED = 1;//购物车已选中
    }

    public enum OrderStatusEnum {
        CANCELED(0, "用户已取消"),
        NOT_PAID(10, "未付款"),
        PAID(20, "已付款"),
        DELIVERED(30, "已发货"),
        FINISHED(40, "交易完成");

        private String value;
        private int code;

        OrderStatusEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        public static OrderStatusEnum codeOf(int code) {
            for (OrderStatusEnum orderStatusEnum : values()) {
                if (orderStatusEnum.getCode() == code) {
                    return orderStatusEnum;
                }
            }
            throw new ImoocMallException(ImoocMallExceptionEnum.NO_ENUM);
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public static String ICODE;

    @Value("${icode}")
    public void setICODE(String icode) {
        ICODE = icode;
    }

    public static final String JWT_KEY = "imooc-mall";
    public static final String JWT_TOKEN = "jwt_token";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_ROLE = "user_role";
    public static final Long EXPIRE_TIME = 60 * 1000 * 60 * 24 * 1000L;//单位是毫秒
}
