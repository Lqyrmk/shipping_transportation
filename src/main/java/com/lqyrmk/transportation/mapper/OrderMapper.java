package com.lqyrmk.transportation.mapper;

import com.lqyrmk.transportation.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/29 16:29
 */
@Mapper
public interface OrderMapper {

    /**
     * @description: 查询所有的订单
     * @author: YuanmingLiu
     * @date: 2023/4/29 16:35
     * @param: []
     * @return: java.util.List<com.lqyrmk.transportation.entity.Order>
     **/
    List<Order> getAllOrders();

    /**
     * @description: 根据id查询订单信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 17:40
     * @param: [orderId]
     * @return: com.lqyrmk.transportation.entity.Order
     **/
    Order getOrderById(@Param("orderId") Integer orderId);

    /**
     * @description: 添加订单信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 16:39
     * @param: [order]
     * @return: void
     **/
    void insertOrder(Order order);

    /**
     * @description: 修改订单信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 17:45
     * @param: [order]
     * @return: void
     **/
    void updateOrder(Order order);

    /**
     * @description: 删除订单
     * @author: YuanmingLiu
     * @date: 2023/4/29 18:20
     * @param: [orderId]
     * @return: void
     **/
    void deleteOrder(@Param("orderId") Integer orderId);

    /**
     * @description: 更新订单中的总价和总重
     * @author: YuanmingLiu
     * @date: 2023/4/29 19:58
     * @param: [order]
     * @return: void
     **/
    void updateOrderPriceAndWeight(Order order);
}
