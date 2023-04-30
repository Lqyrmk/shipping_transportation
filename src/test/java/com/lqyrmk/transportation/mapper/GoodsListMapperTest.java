package com.lqyrmk.transportation.mapper;

import com.lqyrmk.transportation.ShippingTransportationApplication;
import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.entity.GoodsList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/30 21:46
 */
@SpringBootTest(classes = ShippingTransportationApplication.class)
class GoodsListMapperTest {

    @Autowired
    private GoodsListMapper goodsListMapper;

    private Integer goodsId;
    private GoodsList goodsList = new GoodsList();
    private Goods goods = new Goods();


    @BeforeEach
    void setUp() {

        goodsId = 14;

        goodsList.setGoodsId(goodsId);
        goodsList.setGoodsName("鱼丸");
        goodsList.setPrice(23.0);
        goodsList.setWeight(33.0);
        goodsList.setNum(100);

        goods.setGoodsId(goodsId);
        goods.setGoodsName("机械键盘2");
        goods.setPrice(1000.0);
        goods.setWeight(500.0);
        goods.setStock(1000);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getGoodsList() {
        goodsListMapper.getGoodsList().forEach(System.out::println);
    }

    @Test
    void getGoodsListByGoodsId() {
        GoodsList goodsList = goodsListMapper.getGoodsListByGoodsId(goodsId);
        System.out.println("goodsList = " + goodsList);
    }

    @Test
    void addGoodsToList() {
        goodsListMapper.addGoodsToList(goods);
    }

    @Test
    void updateGoodsNumber() {
        GoodsList goodsListInDatabase1 = goodsListMapper.getGoodsListByGoodsId(goodsId);
        System.out.println("goodsListInDatabase1 = " + goodsListInDatabase1);

        goodsListMapper.updateGoodsNumber(goodsId);

        GoodsList goodsListInDatabase2 = goodsListMapper.getGoodsListByGoodsId(goodsId);
        System.out.println("goodsListInDatabase2 = " + goodsListInDatabase2);
    }

    @Test
    void clearGoodsList() {
        goodsListMapper.clearGoodsList();
    }

    @Test
    void deleteGoodsList() {
        goodsListMapper.deleteGoodsList(goodsId);
    }
}
