package com.lqyrmk.transportation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description 订单
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:18
 */
@ApiModel("订单")
@Data
@TableName("order")
public class Order {

    //
    @ApiModelProperty("订单id")
    @TableId
    private Long orderId;

    @ApiModelProperty("订单信息")
    private List<OrderDetails> orderDetailsList;

    @ApiModelProperty("托运人")
    private Shipper shipper;

    @ApiModelProperty("托运人id")
    private Long shipperId;

    @ApiModelProperty("承运商")
    private Carrier carrier;

    @ApiModelProperty("承运商id")
    private Long carrierId;

    @ApiModelProperty("收货人")
    private String consignee;

    @ApiModelProperty("收获人电话")
    private String consigneePhone;

    @ApiModelProperty("发货地")
    private String shipmentPlace;

    @ApiModelProperty("目的地")
    private String destination;

    @ApiModelProperty("运输重量")
    private Double totalWeight;

    @ApiModelProperty("运输总价")
    private Double totalPrice;

    @ApiModelProperty("订单状态")
    private String state;

}
