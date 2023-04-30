package com.lqyrmk.transportation.controller;

import com.lqyrmk.transportation.entity.*;
import com.lqyrmk.transportation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 订单控制层
 * @Author YuanmingLiu
 * @Date 2023/4/29 16:26
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrderInfo")
    public String getOrderInfo(Model model) {
        // 获取所有的订单信息
        List<Order> allOrders = orderService.getAllOrders();
        // 将所有的订单信息在请求域中共享
        model.addAttribute("allOrders", allOrders);
        // 跳转到订单列表页面
        return "order/order_info";
    }

    @PostMapping("/getOrdersByInfo")
    public String getOrdersByInfo(String keywords, Model model) {
        // 获取所有的订单信息
        List<Order> orders = orderService.getOrdersByInfo(keywords);
        // 将所有的订单信息在请求域中共享
        model.addAttribute("allOrders", orders);
        // 跳转到订单列表页面
        return "order/order_info";
    }

    private StringBuilder getOrderDetails(Order order) {
        // 获取该订单的详细货物
        List<OrderDetails> orderDetailsList = order.getOrderDetailsList();
        StringBuilder orderDetailsStrBuilder = new StringBuilder();

        // 格式化货物信息
        for (int i = 0; i < orderDetailsList.size(); i++) {
            orderDetailsStrBuilder
                    .append(orderDetailsList.get(i).getGoodsName())
                    .append("*")
                    .append(orderDetailsList.get(i).getGoodsNum())
                    .append((i == orderDetailsList.size() - 1) ? "" : ", ");
        }

        return orderDetailsStrBuilder;
    }

    @GetMapping("/showOrderDetails")
    public String showOrderDetails(Integer orderId, Model model) {
        // 根据id查询订单信息
        Order order = orderService.getOrderById(orderId);
        // 共享订单信息到请求域中
        model.addAttribute("order", order);

        // 获取该订单的详细货物
        StringBuilder orderDetailsStrBuilder = getOrderDetails(order);
        // 共享订单货物信息到请求域中
        model.addAttribute("orderDetails", orderDetailsStrBuilder);

        // 跳转到订单详情页面
        return "order/order_details";
    }

    @GetMapping("/toAddOrder")
    public String toAddOrder() {
        // 跳转到创建订单页面
        return "order/order_add";
    }

    @PostMapping("/addOrder")
    public String addOrder(Order order, Integer shipperId, Integer carrierId) {
        Shipper shipper = new Shipper();
        shipper.setShipperId(shipperId);
        Carrier carrier = new Carrier();
        carrier.setCarrierId(carrierId);

        order.setShipper(shipper);
        order.setCarrier(carrier);

        //保存订单信息
        orderService.saveOrder(order);
        // 跳转到订单列表页面
        return "redirect:/getOrderInfo";
    }

    @GetMapping("/toUpdateOrder")
    public String toUpdateOrder(Integer orderId, Model model) {
        // 根据id查询订单信息
        Order order = orderService.getOrderById(orderId);
        //根据订单信息共享到请求域中
        model.addAttribute("order", order);

        // 获取该订单的详细货物
        StringBuilder orderDetailsStrBuilder = getOrderDetails(order);
        // 共享订单货物信息到请求域中
        model.addAttribute("orderDetails", orderDetailsStrBuilder);

        // 跳转到更新订单页面
        return "order/order_update";
    }

    @PostMapping("/updateOrder")
    public String updateOrder(Order order, Integer shipperId, Integer carrierId) {
        Shipper shipper = new Shipper();
        shipper.setShipperId(shipperId);
        Carrier carrier = new Carrier();
        carrier.setCarrierId(carrierId);

        order.setShipper(shipper);
        order.setCarrier(carrier);

        // 根据id修改订单信息
        orderService.updateOrder(order);
        return "redirect:/getOrderInfo";
    }

    @GetMapping("/deleteOrder/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Integer orderId) {
        // 根据id删除订单
        orderService.deleteOrder(orderId);
        return "redirect:/getOrderInfo";
    }


}
