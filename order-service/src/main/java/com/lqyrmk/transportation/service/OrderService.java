package com.lqyrmk.transportation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.transportation.entity.Order;

import java.util.List;

/**
 * @Description 订单业务层接口
 * @Author YuanmingLiu
 * @Date 2023/4/29 16:28
 */
public interface OrderService extends IService<Order> {

//    /**
//     * @description: 查询所有的订单
//     * @author: YuanmingLiu
//     * @date: 2023/4/29 16:31
//     * @param: []
//     * @return: java.util.List<com.lqyrmk.transportation.entity.Order>
//     **/
//    List<Order> getAllOrders();
//
    /**
     * @description: 根据信息查询订单
     * @author: YuanmingLiu
     * @date: 2023/4/30 16:17
     * @param: []
     * @return: java.util.List<com.lqyrmk.transportation.entity.Order>
     **/
    List<Order> getOrdersByInfo(String keywords);

    /**
     * @description: 根据id查询订单信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 17:37
     * @param: [orderId]
     * @return: com.lqyrmk.transportation.entity.Order
     **/
    Order getOrderById(Long orderId);

    /**
     * @description: 添加订单信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 16:38
     * @param: [order]
     * @return: int
     **/
    int saveOrder(Order orer);

    /**
     * @description: 修改订单信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 17:45
     * @param: [order]
     * @return: int
     **/
    int updateOrder(Order order);

    /**
     * @description: 删除订单
     * @author: YuanmingLiu
     * @date: 2023/4/29 18:19
     * @param: [orderId]
     * @return: int
     **/
    int deleteOrder(Long orderId);
}
