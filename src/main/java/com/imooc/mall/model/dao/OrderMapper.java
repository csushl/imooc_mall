package com.imooc.mall.model.dao;

import com.imooc.mall.model.pojo.Order;
import com.imooc.mall.model.query.OrderStatisticsQuery;
import com.imooc.mall.model.vo.OrderStatisticsVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByOrderNo(String orderNo);

    List<Order> selectForCustomer(Integer userId);

    List<Order> selectAllForAdmin();

    List<OrderStatisticsVO> selectOrderStatistics(@Param("query") OrderStatisticsQuery query);
}