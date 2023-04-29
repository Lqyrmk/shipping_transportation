package com.lqyrmk.transportation.service.impl;

import com.lqyrmk.transportation.entity.Shipper;
import com.lqyrmk.transportation.mapper.ShipperMapper;
import com.lqyrmk.transportation.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/28 1:11
 */
@Service
public class ShipperServiceImpl implements ShipperService {

    @Autowired
    private ShipperMapper shipperMapper;

    @Override
    public Shipper login(String shipperName, String password) {
        Shipper shipper = shipperMapper.getShipperByShippernameAndPassword(shipperName, password);
        return shipper;
    }

    @Override
    public Shipper getShipperById(Integer shipperId) {
        Shipper shipper = shipperMapper.getShipperById(shipperId);
        return shipper;
    }
}
