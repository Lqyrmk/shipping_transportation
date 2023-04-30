package com.lqyrmk.transportation.controller;

import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.service.GoodsListService;
import com.lqyrmk.transportation.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:20
 */
@Slf4j
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsListService goodsListService;

    @GetMapping("/getGoodsInfo")
    public String getGoodsInfo(Model model) {
        // 获取所有的货物信息
        List<Goods> allGoods = goodsService.getAllGoods();
        // 将所有的货物信息在请求域中共享
        model.addAttribute("allGoods", allGoods);
        // 跳转到货物列表页面
        return "goods/goods_info";
    }

    @PostMapping("/getGoodsByInfo")
    public String getGoodsByInfo(@RequestParam("priceMin") String priceMin,
                                 @RequestParam("priceMax") String priceMax,
                                 @RequestParam("weightMin") String weightMin,
                                 @RequestParam("weightMax") String weightMax,
                                 @RequestParam("stockMin") String stockMin,
                                 @RequestParam("stockMax") String stockMax,
                                 @RequestParam("keywords") String keywords,
                                 Model model) {
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("priceMin", priceMin);
        infoMap.put("priceMax", priceMax);
        infoMap.put("weightMin", weightMin);
        infoMap.put("weightMax", weightMax);
        infoMap.put("stockMin", stockMin);
        infoMap.put("stockMax", stockMax);
        infoMap.put("keywords", keywords);
        List<Goods> allGoods = goodsService.getGoodsByInfo(infoMap);
//        System.out.println(allGoods);
        model.addAttribute("allGoods", allGoods);
        model.addAttribute("priceMin", priceMin);
        model.addAttribute("priceMax", priceMax);
        model.addAttribute("weightMin", weightMin);
        model.addAttribute("weightMax", weightMax);
        model.addAttribute("stockMin", stockMin);
        model.addAttribute("stockMax", stockMax);
        model.addAttribute("keywords", keywords);
        return "goods/goods_info";
    }

    @GetMapping("/toAddGoods")
    public String toAddGoods(Model model) {
        // 跳转到添加货物页面
        return "goods/goods_add";
    }

    @PostMapping("/addGoods")
    public String addGoods(Goods goods) {
        //保存货物信息
        goodsService.saveGoods(goods);
        // 跳转到货物列表页面
        return "redirect:/getGoodsInfo";
    }

    @GetMapping("/toUpdateGoods")
    public String toUpdateGoods(@RequestParam("goodsId") Integer goodsId, Model model) {
        // 根据id查询货物信息
        Goods goods = goodsService.getGoodsById(goodsId);
        //根据货物信息共享到请求域中
        model.addAttribute("goods", goods);
        // 跳转到更新货物页面
        return "goods/goods_update";
    }

    @PostMapping("/updateGoods")
    public String updateGoods(Goods goods) {
        // 根据id修改货物信息
        goodsService.updateGoods(goods);
        return "redirect:/getGoodsInfo";
    }

    @GetMapping("/deleteGoods/{goodsId}")
    public String deleteGoods(@PathVariable("goodsId") Integer goodsId) {
        // 根据id删除货物
        goodsService.deleteGoods(goodsId);
        return "redirect:/getGoodsInfo";
    }

    @GetMapping("/addToList")
    public String addToList(@RequestParam("goodsId") Integer goodsId) {
        // 将货物添加到运输清单中
        goodsListService.addGoodsToList(goodsId);
        return "redirect:/getGoodsInfo";
    }

}
