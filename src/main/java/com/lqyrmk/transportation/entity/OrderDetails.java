package com.lqyrmk.transportation.entity;

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

    private Integer goodsId;

    private Integer goodsNum;

}
