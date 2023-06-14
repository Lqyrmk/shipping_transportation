package com.lqyrmk.transportation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description 托运人
 * @Author YuanmingLiu
 * @Date 2023/4/27 19:55
 */
@ApiModel("托运人")
@Data
@TableName("shipper")
public class Shipper {

    @ApiModelProperty("id")
    @TableId
    private Long shipperId;

    @ApiModelProperty("姓名")
    private String shipperName;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("姓名")
    private String email;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("订单")
    private List<Order> orders;

}
