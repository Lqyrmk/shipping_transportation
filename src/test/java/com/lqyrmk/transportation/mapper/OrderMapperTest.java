//package com.lqyrmk.transportation.mapper;
//
//import com.lqyrmk.transportation.ShippingTransportationApplication;
//import com.lqyrmk.transportation.entity.Carrier;
//import com.lqyrmk.transportation.entity.Order;
//import com.lqyrmk.transportation.entity.Shipper;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @Description
// * @Author YuanmingLiu
// * @Date 2023/4/30 21:34
// */
//@SpringBootTest(classes = ShippingTransportationApplication.class)
//class OrderMapperTest {
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    private Integer orderId;
//    private Order order = new Order();
//    private Shipper shipper = new Shipper();
//    private Carrier carrier = new Carrier();
//
//
//    @BeforeEach
//    void setUp() {
//        orderId = 100;
//
//        shipper.setShipperId(1);
//        carrier.setCarrierId(1);
//
//        order.setOrderId(orderId);
//        order.setShipper(shipper);
//        order.setCarrier(carrier);
//        order.setConsignee("黑小虎");
//        order.setConsigneePhone("1234567890");
//        order.setShipmentPlace("北京");
//        order.setDestination("上海");
//        order.setTotalPrice(123.0);
//        order.setTotalWeight(223.0);
//        order.setOrderDetailsList(new ArrayList<>());
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void getAllOrders() {
//        orderMapper.getAllOrders().forEach(System.out::println);
//    }
//
//    @Test
//    void getOrdersByInfo() {
//        orderMapper.getOrdersByInfo("a").forEach(System.out::println);
//    }
//
//    @Test
//    void getOrderById() {
//        Order order = orderMapper.getOrderById(orderId);
//        System.out.println("order = " + order);
//    }
//
//    @Test
//    void getOrderAndShipperAndCarrierByOrderIdByStepOne() {
//        Order order = orderMapper.getOrderAndShipperAndCarrierByOrderIdByStepOne(orderId);
//        System.out.println("order = " + order);
//    }
//
//    @Test
//    void getShipperAndOrderByShipperIdByStepTwo() {
//        orderMapper.getShipperAndOrderByShipperIdByStepTwo(orderId).forEach(System.out::println);
//    }
//
//    @Test
//    void insertOrder() {
//        orderMapper.insertOrder(order);
//    }
//
//    @Test
//    void updateOrder() {
//        Order orderInDatabase1 = orderMapper.getOrderAndShipperAndCarrierByOrderIdByStepOne(orderId);
//        System.out.println("orderInDatabase1 = " + orderInDatabase1);
//
//        orderInDatabase1.setState("到达湖南");
//        orderMapper.updateOrder(orderInDatabase1);
//
//        Order orderInDatabase2 = orderMapper.getOrderAndShipperAndCarrierByOrderIdByStepOne(orderId);
//        System.out.println("orderInDatabase2 = " + orderInDatabase2);
//
//    }
//
//    @Test
//    void deleteOrder() {
//        orderMapper.deleteOrder(orderId);
//    }
//
//    @Test
//    void updateOrderPriceAndWeight() {
//        order.setTotalPrice(111.0);
//        order.setTotalWeight(2222.0);
//        orderMapper.updateOrderPriceAndWeight(order);
//        Order orderInDatabase = orderMapper.getOrderAndShipperAndCarrierByOrderIdByStepOne(orderId);
//        System.out.println("orderInDatabase = " + orderInDatabase);
//    }
//}
