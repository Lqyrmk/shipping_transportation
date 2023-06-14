package com.lqyrmk.transportation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqyrmk.transportation.common.Result;
import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.entity.WaitingItem;
import com.lqyrmk.transportation.entity.WaitingList;
import com.lqyrmk.transportation.service.WaitingListService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description 货物清单控制层
 * @Author YuanmingLiu
 * @Date 2023/4/29 19:02
 */
@Api(tags = "货物清单接口")
@Slf4j
@RestController
@RequestMapping("/waitingList")
public class WaitingListController {

    @Autowired
    private WaitingListService waitingListService;

    /**
     * @description: 查询货物清单中的所有货物
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:10
     * @param: [model]
     * @return: java.lang.String
     **/
//    @GetMapping
//    public Result<WaitingList> getWaitingList() {
//        // 获取清单中所有的货物信息
//        WaitingList waitingList = waitingListService.getWaitingList();
//        // 跳转到货物列表页面
//        return Result.success(waitingList, "查询成功！");
//    }

    @GetMapping
    public Result<WaitingList> getWaitingList() {
        // 用户id
        Long userId = 14L;
        LambdaQueryWrapper<WaitingList> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WaitingList::getUserId, userId);
        // 获取清单中所有的货物信息
        WaitingList waitingList = waitingListService.getOne(queryWrapper);
        // 跳转到货物列表页面
        return Result.success(waitingList, "查询成功！");
    }

    /**
     * @description: 将货物添加到运输清单中
     * @author: YuanmingLiu
     * @date: 2023/4/29 18:39
     * @param: [goodsId]
     * @return: java.lang.String
     **/
    @PostMapping
    public Result<WaitingList> addToList(@RequestBody Goods goods) {
        // 将货物添加到运输清单中
        if (waitingListService.addItemToList(goods) > 0) {
            return Result.success(null, "添加成功！");
        }
        return Result.error("添加失败！");
    }

    /**
     * @description: 更新运输清单中的货物
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:13
     * @param: [goods]
     * @return: com.lqyrmk.transportation.common.Result<com.lqyrmk.transportation.entity.WaitingList>
     **/
    @PutMapping
    public Result<WaitingList> updateWaitingList(@RequestBody WaitingItem waitingItem) {
        // 更新运输清单中的货物
        if (waitingListService.updateWaitingList(waitingItem) > 0) {
            return Result.success(null, "更新成功！");
        }
        return Result.error("更新失败！");
    }

    /**
     * @description: 删除清单中的某个货物
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:10
     * @param: [goodsId]
     * @return: java.lang.String
     **/
    @DeleteMapping("/{waitingId}")
    public Result<String> deleteWaitingItem(@PathVariable("waitingId") Long waitingId) {
        // 根据id删除清单项
        if (waitingListService.deleteWaitingItem(waitingId) > 0) {
            return Result.success(null, "删除成功！");
        }
        return Result.error("删除失败！");
    }

}
