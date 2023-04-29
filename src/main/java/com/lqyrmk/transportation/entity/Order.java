package com.lqyrmk.transportation.entity;

import lombok.Data;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:18
 */
@Data
public class Order {

    // 订单编号
    private Integer orderId;

    // 托运人id
    private Integer shipperId;

    // 承运商id
    private Integer carrierId;

    // 收货人
    private String addressee;

    // 发货地
    private String shipmentPlace;

    // 目的地
    private String destination;

    // 运输重量
    private Double totalWeight;

    // 运输总价
    private Double totalPrice;

}
