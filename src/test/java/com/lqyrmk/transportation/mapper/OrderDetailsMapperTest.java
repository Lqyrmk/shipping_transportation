package com.lqyrmk.transportation.mapper;

import com.lqyrmk.transportation.ShippingTransportationApplication;
import com.lqyrmk.transportation.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/30 21:22
 */
@SpringBootTest(classes = ShippingTransportationApplication.class)
class OrderDetailsMapperTest {

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    private Integer orderId;


    private Order order = new Order();
    private Shipper shipper = new Shipper();
    private Carrier carrier = new Carrier();
    private GoodsList goodsList = new GoodsList();


    @BeforeEach
    void setUp() {
        orderId = 100;


        shipper.setShipperId(1);
        carrier.setCarrierId(1);

        order.setOrderId(orderId);
        order.setShipper(shipper);
        order.setCarrier(carrier);
        order.setConsignee("黑小虎");
        order.setConsigneePhone("1234567890");
        order.setShipmentPlace("北京");
        order.setDestination("上海");
        order.setTotalPrice(123.0);
        order.setTotalWeight(223.0);
        order.setOrderDetailsList(new ArrayList<>());

        goodsList.setGoodsId(14);
        goodsList.setGoodsName("鱼丸");
        goodsList.setPrice(23.0);
        goodsList.setWeight(33.0);
        goodsList.setNum(100);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getOrderDetailsByOrderId() {
        orderDetailsMapper.getOrderDetailsByOrderId(orderId).forEach(System.out::println);
    }

    @Test
    void insertOrderDetails() {
        orderDetailsMapper.insertOrderDetails(order, goodsList);
    }

    @Test
    void deleteOrderDetails() {
        orderDetailsMapper.deleteOrderDetails(orderId);
    }
}
