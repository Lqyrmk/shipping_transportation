package com.lqyrmk.transportation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/6/14 10:50
 */
@ApiModel("货物清单项")
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("waiting_item")
public class WaitingItem {

    @ApiModelProperty("清单项id")
    @TableId
    private Long waitingId;

    @ApiModelProperty("清单id")
    private Long listId;

    @ApiModelProperty("货物id")
    private Long goodsId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("货物名称")
    private String goodsName;

    @ApiModelProperty("货物单价")
    private Double price;

    @ApiModelProperty("货物重量")
    private Double weight;

    @ApiModelProperty("货物运输个数")
    private Integer num;

    public WaitingItem(Long goodsId, Long userId, String goodsName, Double price, Double weight, Integer num) {
        this.goodsId = goodsId;
        this.userId = userId;
        this.goodsName = goodsName;
        this.price = price;
        this.weight = weight;
        this.num = num;
    }
}
