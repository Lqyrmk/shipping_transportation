package com.lqyrmk.transportation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lqyrmk.transportation.common.Result;
import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:20
 */
@Api(tags = "货物接口")
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * @description: 根据id查询货物信息
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:11
     * @param: [goodsId]
     * @return: com.lqyrmk.transportation.common.Result<com.lqyrmk.transportation.entity.Goods>
     **/
    @GetMapping("/{goodsId}")
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation("根据id查询货物信息")
    @ApiImplicitParams({
    })
    public Result<Goods> getGoodsById(@PathVariable("goodsId") Long goodsId) {
        // 获取所有的货物信息
        Goods goods = goodsService.getById(goodsId);
        if (goods != null) {
            return Result.success(goods, "查询成功！");
        }
        return Result.error("没有此货物！");
    }

    /**
     * @description: 根据关键信息查询货物
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:11
     * @param: [priceMin, priceMax, weightMin, weightMax, stockMin, stockMax, keywords, model]
     * @return: com.lqyrmk.transportation.common.Result<java.util.List<com.lqyrmk.transportation.entity.Goods>>
     **/
    @GetMapping
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation("根据关键信息查询货物")
    @ApiImplicitParams({
    })
    public Result<List<Goods>> getGoodsByInfo(@RequestParam(value = "priceMin", required = false) Double priceMin,
                                              @RequestParam(value = "priceMax", required = false) Double priceMax,
                                              @RequestParam(value = "weightMin", required = false) Double weightMin,
                                              @RequestParam(value = "weightMax", required = false) Double weightMax,
                                              @RequestParam(value = "stockMin", required = false) Integer stockMin,
                                              @RequestParam(value = "stockMax", required = false) Integer stockMax,
                                              @RequestParam("keywords") String keywords) {
        // 查询条件组装
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(keywords), Goods::getGoodsName, keywords)
                .ge(priceMin != null, Goods::getPrice, priceMin)
                .le(priceMax != null, Goods::getPrice, priceMax)
                .ge(weightMin != null, Goods::getWeight, weightMin)
                .le(weightMax != null, Goods::getWeight, weightMax)
                .ge(stockMin != null, Goods::getStock, stockMin)
                .le(stockMax != null, Goods::getStock, stockMax);
        List<Goods> goods = goodsService.list(queryWrapper);
        return Result.success(goods, "查询成功！");

    }

    /**
     * @description: 添加货物
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:12
     * @param: [goods]
     * @return: com.lqyrmk.transportation.common.Result<com.lqyrmk.transportation.entity.Goods>
     **/
    @PostMapping
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation("添加货物")
    @ApiImplicitParams({
    })
    public Result<Goods> addGoods(@RequestBody Goods goods) {
        //保存货物信息
        boolean save = goodsService.save(goods);
        if (save) {
            return Result.success(goods, "添加成功！");
        }
        return Result.error("添加失败！");
    }

    /**
     * @description: 更新货物
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:12
     * @param: [goods]
     * @return: com.lqyrmk.transportation.common.Result<com.lqyrmk.transportation.entity.Goods>
     **/
    @PutMapping
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation("更新货物")
    @ApiImplicitParams({
    })
    public Result<Goods> updateGoods(@RequestBody Goods goods) {
        //保存货物信息
        boolean update = goodsService.updateById(goods);
        if (update) {
            return Result.success(goods, "更新成功！");
        }
        return Result.error("更新失败！");
    }

    /**
     * @description: 删除货物
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:13
     * @param: [goodsId]
     * @return: java.lang.String
     **/
    @DeleteMapping("/{goodsId}")
    @PreAuthorize("hasAuthority('system:use')")
    @ApiOperation("删除货物")
    @ApiImplicitParams({
    })
    public Result<String> deleteGoods(@PathVariable("goodsId") Long goodsId) {
        // 根据id删除货物
        boolean b = goodsService.removeById(goodsId);
        if (b) {
            return Result.success("删除成功！");
        }
        return Result.error("删除失败！");
    }


}
