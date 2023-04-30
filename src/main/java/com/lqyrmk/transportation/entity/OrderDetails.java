package com.lqyrmk.transportation.entity;

import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.Data;

/**
 * @Description
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
