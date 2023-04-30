package com.lqyrmk.transportation.service.impl;

import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.entity.GoodsList;
import com.lqyrmk.transportation.mapper.GoodsListMapper;
import com.lqyrmk.transportation.mapper.GoodsMapper;
import com.lqyrmk.transportation.service.GoodsListService;
import com.lqyrmk.transportation.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/29 18:38
 */
@Service
public class GoodsListServiceImpl implements GoodsListService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsListMapper goodsListMapper;

    @Override
    public List<GoodsList> getGoodsList() {
        List<GoodsList> allGoodsItem = goodsListMapper.getGoodsList();
        return allGoodsItem;
    }

    @Override
    public void addGoodsToList(Integer goodsId) {

        // 根据id从货物表中查询货物信息
        Goods goods = goodsMapper.getGoodsById(goodsId);

        // 货物的库存量小于或等于0时不能添加进清单
        if (goods.getStock() <= 0) {
            return;
        }

        // 货物的库存量大于0
        // 根据id从货物清单中查询货物信息
        GoodsList goodsInList = goodsListMapper.getGoodsListByGoodsId(goodsId);
        // 检查货物是否存在于清单中
        if (goodsInList != null) {
            // 货物存在，更新已存在的货物数量
            goodsListMapper.updateGoodsNumber(goodsId);
        }
        else {
            // 货物不存在，添加货物
            goodsListMapper.addGoodsToList(goods);
        }
        // 减少货物的库存量
        goodsMapper.updateGoodsStock(goodsId);

    }

    @Override
    public void deleteGoodsList(Integer goodsId) {
        goodsListMapper.deleteGoodsList(goodsId);
    }
}
