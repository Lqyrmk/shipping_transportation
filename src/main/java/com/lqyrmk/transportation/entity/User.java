package com.lqyrmk.transportation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:53
 */
@ApiModel("用户")
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = -40745805429167314L;

    @ApiModelProperty("用户id")
    @TableId
    private Long userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户性别")
    private String gender;

    @ApiModelProperty("用户手机号")
    private String phone;

}
