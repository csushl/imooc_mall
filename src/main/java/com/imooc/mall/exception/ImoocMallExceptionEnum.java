package com.imooc.mall.exception;

/**
 * 描述：     异常枚举
 */
public enum ImoocMallExceptionEnum {
    NEED_USER_NAME(10001, "用户名不能为空"),
    NEED_PASSWORD(10002, "密码不能为空"),
    PASSWORD_TOO_SHORT(10003, "密码长度不能小于8位"),
    NAME_EXISTED(10004, "不允许重名"),
    INSERT_FAILED(10005, "插入失败，请重试"),
    WRONG_PASSWORD(10006, "密码错误"),
    NEED_LOGIN(10007, "用户未登录"),
    UPDATE_FAILED(10008, "更新失败"),
    NEED_ADMIN(10009, "无管理员权限"),
    PARA_NOT_NULL(10010, "参数不能为空"),
    CREATE_FAILED(10011, "新增失败"),
    REQUEST_PARAM_ERROR(10012, "参数错误"),
    DELETE_FAILED(10013, "删除失败"),
    MKDIR_FAILED(10014, "文件夹创建失败"),
    UPLOAD_FAILED(10015, "图片上传失败"),
    NOT_SALE(10016, "商品状态不可售"),
    NOT_ENOUGH(10017, "商品库存不足"),
    CART_EMPTY(10018, "购物车已勾选的商品为空"),
    NO_ENUM(10019, "未找到对应的枚举"),
    NO_ORDER(10020, "订单不存在"),
    NOT_YOUR_ORDER(10021, "订单不属于你"),
    WRONG_ORDER_STATUS(10022, "订单状态不符"),
    WRONG_EMAIL(10023, "非法的邮件地址"),
    EMAIL_ALREADY_BEEN_REGISTERED(10024, "email地址已被注册"),
    EMAIL_ALREADY_BEEN_SEND(10025, "email已发送，若无法收到，请稍后再试"),
    NEED_EMAIL_ADDRESS(10026, "email不能为空"),
    NEED_VERIFICATION_CODE(10027, "验证码不能为空"),
    WRONG_VERIFICATION_CODE(10028, "验证码错误"),
    TOKEN_EXPIRED(10029, "token过期"),
    TOKEN_WRONG(10030, "token解析失败"),
    PRICE_TOO_LOW(10031, "价格过低"),
    STOCK_TOO_MANY(10032, "库存不能大于10000"),
    CANCEL_WRONG_ORDER_STATUS(10033, "订单状态有误，付款后暂不支持取消订单"),
    PAY_WRONG_ORDER_STATUS(10034, "订单状态有误，仅能在未付款时付款"),
    DELIVER_WRONG_ORDER_STATUS(10035, "订单状态有误，仅能在付款后发货"),
    FINISH_WRONG_ORDER_STATUS(10036, "请在后台管理系统操作发货后，再“确认收货”完单"),
    SYSTEM_ERROR(20000, "系统异常，请从控制台或日志中查看具体错误信息");
    /**
     * 异常码
     */
    Integer code;
    /**
     * 异常信息
     */
    String msg;

    ImoocMallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
