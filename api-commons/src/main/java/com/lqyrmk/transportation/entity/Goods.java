package com.lqyrmk.transportation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 货物
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:13
 */
@ApiModel("货物")
@Data
@TableName("goods")
public class Goods {

    @ApiModelProperty("货物id")
    @TableId
    private Long goodsId;

    @ApiModelProperty("货物名称")
    private String goodsName;

    @ApiModelProperty("货物单价")
    private Double price;

    @ApiModelProperty("货物重量")
    private Double weight;

    @ApiModelProperty("货物库存量")
    private Integer stock;

}
