package com.lqyrmk.transportation.entity;

import lombok.Data;

/**
 * @Description 货物清单类
 * @Author YuanmingLiu
 * @Date 2023/4/29 18:36
 */
@Data
public class GoodsList {

    private Integer id;

    private Integer goodsId;

    private String goodsName;

    private Double price;

    private Double weight;

    private Integer num;

}
