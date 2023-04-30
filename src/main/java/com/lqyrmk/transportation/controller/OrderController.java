package com.lqyrmk.transportation.controller;

import com.lqyrmk.transportation.entity.*;
import com.lqyrmk.transportation.service.GoodsListService;
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

    @Autowired
    private GoodsListService goodsListService;

    /**
     * @description: 查询所有的订单
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:07
     * @param: [model]
     * @return: java.lang.String
     **/
    @GetMapping("/getOrderInfo")
    public String getOrderInfo(Model model) {
        // 获取所有的订单信息
        List<Order> allOrders = orderService.getAllOrders();
        // 将所有的订单信息在请求域中共享
        model.addAttribute("allOrders", allOrders);
        // 跳转到订单列表页面
        return "order/order_info";
    }

    /**
     * @description: 根据关键信息查询订单信息
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:08
     * @param: [keywords, model]
     * @return: java.lang.String
     **/
    @PostMapping("/getOrdersByInfo")
    public String getOrdersByInfo(String keywords, Model model) {
        // 获取所有的订单信息
        List<Order> orders = orderService.getOrdersByInfo(keywords);
        // 将所有的订单信息在请求域中共享
        model.addAttribute("allOrders", orders);
        model.addAttribute("keywords", keywords);
        // 跳转到订单列表页面
        return "order/order_info";
    }

    /**
     * @description: 格式化获取订单货物细节
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:08
     * @param: [orderDetailsList]
     * @return: java.lang.StringBuilder
     **/
    private StringBuilder getOrderDetails(List<OrderDetails> orderDetailsList) {
        // 获取该订单的详细货物
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

    /**
     * @description: 获取某个订单的详细信息
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:09
     * @param: [orderId, model]
     * @return: java.lang.String
     **/
    @GetMapping("/showOrderDetails")
    public String showOrderDetails(Integer orderId, Model model) {
        // 根据id查询订单信息
        Order order = orderService.getOrderById(orderId);
        // 共享订单信息到请求域中
        model.addAttribute("order", order);

        // 获取该订单的详细货物
        StringBuilder orderDetailsStrBuilder = getOrderDetails(order.getOrderDetailsList());
        // 共享订单货物信息到请求域中
        model.addAttribute("orderDetails", orderDetailsStrBuilder);

        // 跳转到订单详情页面
        return "order/order_details";
    }

    /**
     * @description: 跳转到添加订单页面
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:09
     * @param: [model]
     * @return: java.lang.String
     **/
    @GetMapping("/toAddOrder")
    public String toAddOrder(Model model) {
        // 获取该订单的详细货物
        StringBuilder goodsListStrBuilder = new StringBuilder();
        List<GoodsList> goodsList = goodsListService.getGoodsList();

        // 如果清单中没有货物，则跳转到货物页面
        if (goodsList.isEmpty()) {
            model.addAttribute("orderError", "清单中没有货物，请添加货物！");
            return "forward:/getGoodsInfo";
        }

        // 格式化货物信息
        for (int i = 0; i < goodsList.size(); i++) {
            goodsListStrBuilder
                    .append(goodsList.get(i).getGoodsName())
                    .append("*")
                    .append(goodsList.get(i).getNum())
                    .append((i == goodsList.size() - 1) ? "" : ", ");
        }
        model.addAttribute("goodsInfo", goodsListStrBuilder);
        // 跳转到创建订单页面
        return "order/order_add";
    }

    /**
     * @description: 添加订单
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:09
     * @param: [order, shipperId, carrierId]
     * @return: java.lang.String
     **/
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

    /**
     * @description: 跳转到更新订单页面
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:09
     * @param: [orderId, model]
     * @return: java.lang.String
     **/
    @GetMapping("/toUpdateOrder")
    public String toUpdateOrder(Integer orderId, Model model) {
        // 根据id查询订单信息
        Order order = orderService.getOrderById(orderId);
        //根据订单信息共享到请求域中
        model.addAttribute("order", order);

        // 获取该订单的详细货物
        StringBuilder orderDetailsStrBuilder = getOrderDetails(order.getOrderDetailsList());
        // 共享订单货物信息到请求域中
        model.addAttribute("orderDetails", orderDetailsStrBuilder);

        // 跳转到更新订单页面
        return "order/order_update";
    }

    /**
     * @description: 更新订单
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:10
     * @param: [order, shipperId, carrierId]
     * @return: java.lang.String
     **/
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

    /**
     * @description: 根据id删除订单
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:10
     * @param: [orderId]
     * @return: java.lang.String
     **/
    @GetMapping("/deleteOrder/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Integer orderId) {
        // 根据id删除订单
        orderService.deleteOrder(orderId);
        return "redirect:/getOrderInfo";
    }

}
