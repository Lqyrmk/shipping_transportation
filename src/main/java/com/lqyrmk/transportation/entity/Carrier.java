package com.lqyrmk.transportation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 承运商
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:16
 */
@ApiModel("承运商")
@Data
@TableName("carrier")
public class Carrier {

    @ApiModelProperty("id")
    @TableId
    private Long carrierId;

    @ApiModelProperty("姓名")
    private String carrierName;

    @ApiModelProperty("公司名")
    private String companyName;

}
