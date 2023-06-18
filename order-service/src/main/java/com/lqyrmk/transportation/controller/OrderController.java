package com.lqyrmk.transportation.controller;

import com.lqyrmk.transportation.common.Result;
import com.lqyrmk.transportation.entity.*;
import com.lqyrmk.transportation.service.WaitingListService;
import com.lqyrmk.transportation.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 订单控制层
 * @Author YuanmingLiu
 * @Date 2023/4/29 16:26
 */
@Api(tags = "货物清单接口")
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WaitingListService waitingListService;

    @GetMapping("/consumer/waitingList/")
    public Result<WaitingList> getWaitingList() {
        return WaitingListFeignService.getWaitingList();
    }

    @PostMapping("/consumer/waitingList/")
    public Result<WaitingList> addToList(@RequestBody Goods goods) {
        return WaitingListFeignService.addToList();
    }

    /**
     * @description: 根据关键词查询订单信息
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:08
     * @param: [keywords]
     * @return: com.lqyrmk.transportation.common.Result<java.util.List<com.lqyrmk.transportation.entity.Order>>
     **/
    @GetMapping
    @PreAuthorize("hasAnyAuthority('system:use', 'system:manage')")
    @ApiOperation("根据关键词查询订单信息")
    @ApiImplicitParams({
    })
    public Result<List<Order>> getOrdersByInfo(@RequestParam("keywords") String keywords) {
        // 获取所有的订单信息
        List<Order> orders = orderService.getOrdersByInfo(keywords);
        return Result.success(orders, "查询成功！");
    }

    /**
     * @description: 格式化获取订单货物细节
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:08
     * @param: [orderDetailsList]
     * @return: java.lang.StringBuilder
     **/
    private StringBuilder getOrderDetailsStr(List<OrderDetails> orderDetailsList) {
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
     * @description: 格式化获取订单货物细节
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:09
     * @param: [orderId]
     * @return: com.lqyrmk.transportation.common.Result<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @GetMapping("/{orderId}")
    @PreAuthorize("hasAnyAuthority('system:use', 'system:manage')")
    @ApiOperation("格式化获取订单货物细节")
    @ApiImplicitParams({
    })
    public Result<Map<String, Object>> getOrderDetails(@PathVariable Long orderId) {
        // 根据id查询订单信息
        Order order = orderService.getOrderById(orderId);
        StringBuilder orderDetailsStrBuilder = new StringBuilder("暂无货物");
        // 获取该订单的详细货物
        if (order != null) {
            orderDetailsStrBuilder = getOrderDetailsStr(order.getOrderDetailsList());
        }
        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("order", order);
        orderMap.put("orderDetails", orderDetailsStrBuilder);
        return Result.success(orderMap, "查询成功！");
    }

    /**
     * @description: 添加订单
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:09
     * @param: [order]
     * @return: com.lqyrmk.transportation.common.Result<com.lqyrmk.transportation.entity.Order>
     **/
    @PostMapping
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation("添加订单")
    @ApiImplicitParams({
    })
    public Result<Order> addOrder(@RequestBody Order order) {
        //保存订单信息
        if (orderService.saveOrder(order) > 0) {
            return Result.success(null, "保存成功！");
        }
        return Result.error("保存失败！");
    }

    /**
     * @description: 更新订单
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:10
     * @param: [order]
     * @return: com.lqyrmk.transportation.common.Result<com.lqyrmk.transportation.entity.Order>
     **/
    @PutMapping
    @PreAuthorize("hasAnyAuthority('system:use', 'system:manage')")
    @ApiOperation("更新订单")
    @ApiImplicitParams({
    })
    public Result<Order> updateOrder(@RequestBody Order order) {
        // 根据id修改订单信息
        if (orderService.updateOrder(order) > 0) {
            return Result.success(null, "保存成功！");
        }
        return Result.error("保存失败！");
    }

    /**
     * @description: 根据id删除订单
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:10
     * @param: [orderId]
     * @return: com.lqyrmk.transportation.common.Result<java.lang.String>
     **/
    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasAnyAuthority('system:use', 'system:manage')")
    @ApiOperation("根据id删除订单")
    @ApiImplicitParams({
    })
    public Result<String> deleteOrder(@PathVariable("orderId") Long orderId) {
        // 根据id删除订单
        if (orderService.deleteOrder(orderId) > 0) {
            return Result.success("删除成功！");
        }
        return Result.error("删除失败！");
    }

}
