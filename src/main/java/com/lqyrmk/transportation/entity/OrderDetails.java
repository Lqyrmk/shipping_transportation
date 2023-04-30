package com.lqyrmk.transportation.entity;

import lombok.Data;

/**
 * @Description 订单细节（关系）类
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:19
 */
@Data
public class OrderDetails {

    private Integer id;

    private Integer orderId;
//    private Order order;

    private String goodsName;

    private Goods goods;

    private Integer goodsNum;

}
