package com.lqyrmk.transportation.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/27 19:55
 */
@Data
public class Shipper {

    private Integer shipperId;

    private String shipperName;

    private String password;

    private String gender;

    private String email;

    private String phone;

    private List<Order> orders;

}
