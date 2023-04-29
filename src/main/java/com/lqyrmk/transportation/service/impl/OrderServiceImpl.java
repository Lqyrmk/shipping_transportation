package com.lqyrmk.transportation.service.impl;

import com.lqyrmk.transportation.entity.GoodsList;
import com.lqyrmk.transportation.entity.Order;
import com.lqyrmk.transportation.mapper.GoodsListMapper;
import com.lqyrmk.transportation.mapper.OrderDetailsMapper;
import com.lqyrmk.transportation.mapper.OrderMapper;
import com.lqyrmk.transportation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/29 16:28
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsListMapper goodsListMapper;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderMapper.getAllOrders();
//        List<Order> orders = orderMapper.getAllOrdersAndShipperAndCarrierByStepOne();
        return orders;
    }

    @Override
    public Order getOrderById(Integer orderId) {
//        Order order = orderMapper.getOrderById(orderId);
        Order order = orderMapper.getOrderAndShipperAndCarrierByOrderIdByStepOne(orderId);
        System.out.println("order = " + order);
        return order;
    }

    @Override
    public void saveOrder(Order order) {

        System.out.println("order1 = " + order);
        // 添加订单记录，获得自增id
        orderMapper.insertOrder(order);
        System.out.println("order2 = " + order);

        double totalPrice = 0;
        double totalWeight = 0;
        for (GoodsList goodsList : goodsListMapper.getGoodsList()) {
            // 根据货物清单添加货物订单关系
            orderDetailsMapper.insertOrderDetails(order, goodsList);
            // 计算运输总价、运输总重
            totalPrice += goodsList.getPrice() * goodsList.getNum();
            totalWeight += goodsList.getWeight() * goodsList.getNum();
        }

        // 更新订单记录中的总价和总重
        order.setTotalPrice(totalPrice);
        order.setTotalWeight(totalWeight);
        System.out.println("totalPrice = " + totalPrice);
        System.out.println("totalWeight = " + totalWeight);
        orderMapper.updateOrderPriceAndWeight(order);

        // 清空货物清单
        goodsListMapper.clearGoodsList();
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        // 删除货物订单关系
        orderDetailsMapper.deleteOrderDetails(orderId);
        // 删除货物订单
        orderMapper.deleteOrder(orderId);
    }
}
