package com.lqyrmk.transportation.entity;

import lombok.Data;

/**
 * @Description 货物类
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:13
 */
@Data
public class Goods {

    private Integer goodsId;

    private String goodsName;

    private Double price;

    private Double weight;

    private Integer stock;

}
