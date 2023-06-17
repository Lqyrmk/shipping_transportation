package com.lqyrmk.transportation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.transportation.entity.*;
import com.lqyrmk.transportation.mapper.WaitingItemMapper;
import com.lqyrmk.transportation.mapper.WaitingListMapper;
import com.lqyrmk.transportation.mapper.OrderDetailsMapper;
import com.lqyrmk.transportation.mapper.OrderMapper;
import com.lqyrmk.transportation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 订单业务层实现类
 * @Author YuanmingLiu
 * @Date 2023/4/29 16:28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private WaitingListMapper waitingListMapper;

    @Autowired
    private WaitingItemMapper waitingItemMapper;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

//    @Override
//    public List<Order> getAllOrders() {
//        List<Order> orders = orderMapper.getAllOrders();
////        List<Order> orders = orderMapper.getAllOrdersAndShipperAndCarrierByStepOne();
//        return orders;
//    }

    @Override
    public List<Order> getOrdersByInfo(String keywords) {

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        List<String> permissions = loginUser.getPermissions();
        List<Order> orders = new ArrayList<>();
        // 判断permissions中是否存在管理员权限
        if (permissions.stream().anyMatch("system:manage"::equals)) {
            orders = orderMapper.getOrdersByInfoByCarrier(keywords);
        }
        else {
            Long userId = loginUser.getUser().getUserId();
            orders = orderMapper.getOrdersByInfoByShipper(keywords, userId);
        }

        return orders;
    }

    @Override
    public Order getOrderById(Long orderId) {
        Order order = orderMapper.getOrderByOrderIdByStep1(orderId);
        return order;
    }

    @Override
    public int saveOrder(Order order) {

        // 添加订单记录，获得自增id
//        orderMapper.insert(order);
        orderMapper.addOrder(order);

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();

        // 获取清单项
        LambdaQueryWrapper<WaitingItem> waitingItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
        waitingItemLambdaQueryWrapper.eq(WaitingItem::getUserId, userId);
        List<WaitingItem> waitingItems = waitingItemMapper.selectList(waitingItemLambdaQueryWrapper);

        for (WaitingItem waitingItem : waitingItems) {
            // 根据货物清单添加货物订单细节
            OrderDetails orderDetails = new OrderDetails(order.getOrderId(), waitingItem.getGoodsName(), waitingItem.getPrice(), waitingItem.getWeight(), waitingItem.getNum());
            orderDetailsMapper.insert(orderDetails);
        }

        // 获取清单
        LambdaQueryWrapper<WaitingList> waitingListLambdaQueryWrapper = new LambdaQueryWrapper<>();
        waitingListLambdaQueryWrapper.eq(WaitingList::getUserId, userId);
        WaitingList waitingList = waitingListMapper.selectOne(waitingListLambdaQueryWrapper);

        // 更新订单记录中的总价和总重
        order.setTotalPrice(waitingList.getTotalPrice());
        order.setTotalWeight(waitingList.getTotalWeight());
        orderMapper.updateOrderPriceAndWeight(order);

        // 清空货物清单
        waitingList.setTotalPrice(0.0);
        waitingList.setTotalWeight(0.0);
        waitingListMapper.updateById(waitingList);
        return waitingItemMapper.delete(waitingItemLambdaQueryWrapper);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrderById(order);
    }

    @Override
    public int deleteOrder(Long orderId) {
        // 删除货物订单
        orderMapper.deleteOrderById(orderId);
        // 删除货物订单关系
        LambdaQueryWrapper<OrderDetails> orderDetailsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderDetailsLambdaQueryWrapper.eq(OrderDetails::getOrderId, orderId);
        return orderDetailsMapper.delete(orderDetailsLambdaQueryWrapper);
    }
}
