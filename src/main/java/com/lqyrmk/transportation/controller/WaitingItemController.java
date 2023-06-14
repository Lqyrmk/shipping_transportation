package com.lqyrmk.transportation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqyrmk.transportation.common.Result;
import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.entity.WaitingItem;
import com.lqyrmk.transportation.service.WaitingItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/6/14 10:58
 */
@Api(tags = "货物清单项接口")
@Slf4j
@RestController
@RequestMapping("/waitingItem")
public class WaitingItemController {

    @Autowired
    private WaitingItemService waitingItemService;

    /**
     * @description: 根据id查询货物清单项信息
     * @author: YuanmingLiu
     * @date: 2023/6/14 11:03
     * @param: [waitingid]
     * @return: com.lqyrmk.transportation.common.Result<com.lqyrmk.transportation.entity.WaitingItem>
     **/
    @GetMapping("/{waitingId}")
    @ApiOperation("根据id查询货物清单项信息")
    @ApiImplicitParams({
    })
    public Result<WaitingItem> getWaitingItemById(@PathVariable("waitingId") Long waitingId) {
        // 获取所有的货物清单项信息
        WaitingItem waitingItem = waitingItemService.getById(waitingId);
        if (waitingItem != null) {
            return Result.success(waitingItem, "查询成功！");
        }
        return Result.error("没有此货物！");
    }

    /**
     * @description: 查询所有货物清单项
     * @author: YuanmingLiu
     * @date: 2023/6/15 0:00
     * @param: []
     * @return: com.lqyrmk.transportation.common.Result<java.util.List<com.lqyrmk.transportation.entity.WaitingItem>>
     **/
    @GetMapping
    @ApiOperation("查询所有货物清单项")
    @ApiImplicitParams({
    })
    public Result<List<WaitingItem>> getWaitingItems() {

        Long userId = 14L;

        LambdaQueryWrapper<WaitingItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WaitingItem::getUserId, userId);
        // 获取所有的货物清单项信息
        List<WaitingItem> list = waitingItemService.list(queryWrapper);
        return Result.success(list, "查询成功！");
    }

}
