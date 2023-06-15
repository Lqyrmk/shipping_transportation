package com.lqyrmk.transportation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqyrmk.transportation.common.Result;
import com.lqyrmk.transportation.entity.Carrier;
import com.lqyrmk.transportation.entity.Order;
import com.lqyrmk.transportation.service.CarrierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 承运商控制层
 * @Author YuanmingLiu
 * @Date 2023/4/29 21:50
 */
@Api(tags = "承运商接口")
@Slf4j
@RestController
@RequestMapping("/carrier")
public class CarrierController {

    @Autowired
    private CarrierService carrierService;


    /**
     * @description: 查询所有的承运商
     * @author: YuanmingLiu
     * @date: 2023/6/15 19:21
     * @param: []
     * @return: com.lqyrmk.transportation.common.Result<java.util.List<com.lqyrmk.transportation.entity.Carrier>>
     **/
    @GetMapping
    @ApiOperation("查询所有的承运商")
    @ApiImplicitParams({
    })
    public Result<List<Carrier>> getAllCarrier() {
        // 获取所有的承运商
        LambdaQueryWrapper<Carrier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Carrier::getCarrierId, Carrier::getCarrierName, Carrier::getCompanyName);
        List<Carrier> carriers = carrierService.list(queryWrapper);
        return Result.success(carriers, "查询成功！");
    }

}
