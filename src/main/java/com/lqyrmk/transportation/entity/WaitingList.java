package com.lqyrmk.transportation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 货物清单
 * @Author YuanmingLiu
 * @Date 2023/4/29 18:36
 */
@ApiModel("货物清单")
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("waiting_list")
public class WaitingList {

    @ApiModelProperty("清单id")
    @TableId
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

//    @ApiModelProperty("清单项")
//    private List<WaitingItem> waitingItemList;

    @ApiModelProperty("总价")
    private Double totalPrice;

    @ApiModelProperty("总重量")
    private Double totalWeight;

    public WaitingList(Long userId, Double totalPrice, Double totalWeight) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
    }
}
