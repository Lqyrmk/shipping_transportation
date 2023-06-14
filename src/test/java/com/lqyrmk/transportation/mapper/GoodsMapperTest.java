//package com.lqyrmk.transportation.mapper;
//
//import com.lqyrmk.transportation.ShippingTransportationApplication;
//import com.lqyrmk.transportation.entity.Goods;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @Description
// * @Author YuanmingLiu
// * @Date 2023/4/30 20:59
// */
//@SpringBootTest(classes = ShippingTransportationApplication.class)
//class GoodsMapperTest {
//
//    @Autowired
//    private GoodsMapper goodsMapper;
//
//    private Integer goodsId;
//    private Map<String, Object> map = new HashMap<>();
//    private Goods goods = new Goods();
//
//    @BeforeEach
//    void setUp() {
//        goodsId = 20;
//        map.put("priceMin", 1);
//        map.put("priceMax", 1000);
//        map.put("weightMin", 10);
//        map.put("weightMax", 888);
//        map.put("stockMin", 10);
//        map.put("stockMax", 10000);
////        map.put("keywords", "a");
//
//        goods.setGoodsName("机械键盘");
//        goods.setPrice(1000.0);
//        goods.setWeight(500.0);
//        goods.setStock(1000);
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void getAllGoods() {
//        goodsMapper.getAllGoods().forEach(System.out::println);
//    }
//
//    @Test
//    void getAllGoodsByInfo() {
//        goodsMapper.getAllGoodsByInfo(map).forEach(System.out::println);
//    }
//
//    @Test
//    void getGoodsById() {
//        Goods goods = goodsMapper.getGoodsById(goodsId);
//        System.out.println("goods = " + goods);
//    }
//
//    @Test
//    void insertGoods() {
//        Goods goods = new Goods();
//        goods.setGoodsName("机械键盘2");
//        goods.setPrice(1000.0);
//        goods.setWeight(500.0);
//        goods.setStock(1000);
//        goodsMapper.insertGoods(goods);
//    }
//
//    @Test
//    void updateGoods() {
//        // 获取库中的数据
//        Goods goodsInDataBase1 = goodsMapper.getGoodsById(goodsId);
//        System.out.println("goodsInDataBase1 = " + goodsInDataBase1);
//        // 更新
//        goodsInDataBase1.setStock(2000);
//        goodsMapper.updateGoods(goodsInDataBase1);
//        // 获取库中的数据
//        Goods goodsInDataBase2 = goodsMapper.getGoodsById(goodsId);
//        System.out.println("goodsInDataBase2 = " + goodsInDataBase2);
//    }
//
//    @Test
//    void deleteGoods() {
//        goodsMapper.deleteGoods(goodsId);
//    }
//
//    @Test
//    void updateGoodsStock() {
//        goodsMapper.updateGoodsStock(goodsId);
//    }
//}
