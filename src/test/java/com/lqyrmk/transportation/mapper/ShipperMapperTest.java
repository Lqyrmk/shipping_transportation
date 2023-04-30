package com.lqyrmk.transportation.mapper;

import com.lqyrmk.transportation.ShippingTransportationApplication;
import com.lqyrmk.transportation.entity.Shipper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/30 20:47
 */
@SpringBootTest(classes = ShippingTransportationApplication.class)
class ShipperMapperTest {

    @Autowired
    private ShipperMapper shipperMapper;

    private Integer shipperId;
    private String shipperName;
    private String password;

    @BeforeEach
    void setUp() {
        shipperId = 1;
        shipperName = "admin";
        password = "123";
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getShipperByShippernameAndPassword() {
        Shipper shipper = shipperMapper.getShipperByShippernameAndPassword(shipperName, password);
        System.out.println("shipper = " + shipper);
    }

    @Test
    void getShipperById() {
        Shipper shipper = shipperMapper.getShipperById(shipperId);
        System.out.println("shipper = " + shipper);
    }

    @Test
    void getShipperByName() {
        Shipper shipper = shipperMapper.getShipperByName(shipperName);
        System.out.println("shipper = " + shipper);
    }

    @Test
    void getShipperAndOrderByShipperIdByStepOne() {
        Shipper shipper = shipperMapper.getShipperAndOrderByShipperIdByStepOne(shipperId);
        System.out.println("shipper = " + shipper);
    }

    @Test
    void getOrderAndShipperAndCarrierByOrderIdByStepTwo() {
        Shipper shipper = shipperMapper.getOrderAndShipperAndCarrierByOrderIdByStepTwo(shipperId);
        System.out.println("shipper = " + shipper);
    }

    @Test
    void insertShipper() {
        Shipper shipper = new Shipper();
        shipper.setShipperName("kunkun");
        shipper.setPassword("123123");
        shipperMapper.insertShipper(shipper);
    }
}
