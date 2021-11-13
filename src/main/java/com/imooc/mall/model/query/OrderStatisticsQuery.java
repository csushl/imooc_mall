package com.imooc.mall.model.query;

import java.util.Date;

/**
 * 描述：     订单量统计的Query
 */
public class OrderStatisticsQuery {

    private Date startDate;

    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
