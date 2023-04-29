package com.lqyrmk.transportation.entity;

import lombok.Data;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:16
 */
@Data
public class Carrier {

    private Integer carrierId;

    private String carrierName;

    private String password;

    private String companyName;

    private String phone;
}
