package com.lqyrmk.transportation.controller;

import com.lqyrmk.transportation.entity.Carrier;
import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.entity.Order;
import com.lqyrmk.transportation.entity.Shipper;
import com.lqyrmk.transportation.service.OrderService;
import com.lqyrmk.transportation.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/29 16:26
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShipperService shipperService;

    @GetMapping("/getOrderInfo")
    public String getOrderInfo(Model model) {
        // 获取所有的订单信息
        List<Order> allOrders = orderService.getAllOrders();
        // 将所有的订单信息在请求域中共享
        model.addAttribute("allOrders", allOrders);
        // 跳转到订单列表页面
        return "order/order_info";
    }

    @GetMapping("/showOrderDetails")
    public String showOrderDetails(@RequestParam("orderId") Integer orderId, Model model) {
        // 根据id查询订单信息
        Order order = orderService.getOrderById(orderId);
        //根据订单信息共享到请求域中
        model.addAttribute("order", order);

//        // 获取订单对应的托运人
//        Integer shipperId = order.getShipperId();
//        Shipper shipper = shipperService.getShipperById(shipperId);
//
//        model.addAttribute("shipper", shipper);

        // 跳转到订单详情页面
        return "order/order_details";
    }

    @GetMapping("/toAddOrder")
    public String toAddOrder(Model model) {
        // 跳转到创建订单页面
        return "order/order_add";
    }

    @PostMapping("/addOrder")
    public String addOrder(@RequestParam("shipperId") Integer shipperId,
                           @RequestParam("carrierId") Integer carrierId,
                           @RequestParam("addressee") String addressee,
                           @RequestParam("shipmentPlace") String shipmentPlace,
                           @RequestParam("destination") String destination) {
        Order order = new Order();
        Shipper shipper = new Shipper();
        shipper.setShipperId(shipperId);
        Carrier carrier = new Carrier();
        carrier.setCarrierId(carrierId);
        order.setShipper(shipper);
        order.setCarrier(carrier);
        order.setAddressee(addressee);
        order.setShipmentPlace(shipmentPlace);
        order.setDestination(destination);
        //保存订单信息
        System.out.println(order);
        orderService.saveOrder(order);
        // 跳转到订单列表页面
        return "redirect:/getOrderInfo";
    }

    @ResponseBody
    @GetMapping("/niania")
    public List<Order> nia() {
        List<Order> orders = orderService.getAllOrders();
        System.out.println("orders = " + orders);
        return orders;
    }


    @GetMapping("/toUpdateOrder")
    public String toUpdateOrder(@RequestParam("orderId") Integer orderId, Model model) {
        // 根据id查询订单信息
        Order order = orderService.getOrderById(orderId);
        //根据订单信息共享到请求域中
        model.addAttribute("order", order);
        // 跳转到更新订单页面
        return "order/order_update";
    }

    @PostMapping("/updateOrder")
    public String updateOrder(Order order) {
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
