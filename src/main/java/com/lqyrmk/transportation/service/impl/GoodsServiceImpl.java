package com.lqyrmk.transportation.service.impl;

import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.mapper.GoodsMapper;
import com.lqyrmk.transportation.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 货物业务层实现类
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:33
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getAllGoods() {
        List<Goods> goods = goodsMapper.getAllGoods();
        return goods;
    }

    @Override
    public List<Goods> getGoodsByInfo(Map<String, Object> infoMap) {
        List<Goods> goods = goodsMapper.getAllGoodsByInfo(infoMap);
        return goods;
    }

    @Override
    public Goods getGoodsById(Integer goodsId) {
        Goods goods = goodsMapper.getGoodsById(goodsId);
        return goods;
    }

    @Override
    public void saveGoods(Goods goods) {
        goodsMapper.insertGoods(goods);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsMapper.updateGoods(goods);
    }

    @Override
    public void deleteGoods(Integer goodsId) {
        goodsMapper.deleteGoods(goodsId);
    }
}
