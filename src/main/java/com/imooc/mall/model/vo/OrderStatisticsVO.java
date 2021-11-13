package com.imooc.mall.model.vo;

import java.util.Date;

/**
 * 描述：     订单统计VO
 */
public class OrderStatisticsVO {

    private Date days;

    private Integer amount;

    public Date getDays() {
        return days;
    }

    public void setDays(Date days) {
        this.days = days;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
