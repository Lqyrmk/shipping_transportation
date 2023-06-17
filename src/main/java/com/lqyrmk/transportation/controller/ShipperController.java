package com.lqyrmk.transportation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqyrmk.transportation.common.Result;
import com.lqyrmk.transportation.entity.Carrier;
import com.lqyrmk.transportation.entity.LoginUser;
import com.lqyrmk.transportation.entity.Shipper;
import com.lqyrmk.transportation.service.ShipperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description 托运人控制层
 * @Author YuanmingLiu
 * @Date 2023/4/27 19:14
 */
@Api(tags = "托运人接口")
@Slf4j
@RestController
@RequestMapping("/shipper")
public class ShipperController {

    @Autowired
    private ShipperService shipperService;

    @GetMapping("/details")
    @ApiOperation("查询当前登录的承运商")
    @ApiImplicitParams({
    })
    public Result<Shipper> getCurrentShipper() {
        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();

        // 获取所有的承运商
        LambdaQueryWrapper<Shipper> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shipper::getShipperId, userId)
                .select(Shipper::getShipperId, Shipper::getShipperName);
        Shipper shipper = shipperService.getOne(queryWrapper);
        return Result.success(shipper, "查询成功！");
    }

}
