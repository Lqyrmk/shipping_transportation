package com.lqyrmk.transportation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 订单细节类
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:19
 */
@ApiModel("订单细节")
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("order_details")
public class OrderDetails {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("订单id")
    private Long orderId;
//    private Order order;

    @ApiModelProperty("货物名称")
    private String goodsName;

    @ApiModelProperty("货物单价")
    private Double price;

    @ApiModelProperty("货物重量")
    private Double weight;

    @ApiModelProperty("货物数量")
    private Integer goodsNum;

    public OrderDetails(Long orderId, String goodsName, Double price, Double weight, Integer goodsNum) {
        this.orderId = orderId;
        this.goodsName = goodsName;
        this.price = price;
        this.weight = weight;
        this.goodsNum = goodsNum;
    }
}
