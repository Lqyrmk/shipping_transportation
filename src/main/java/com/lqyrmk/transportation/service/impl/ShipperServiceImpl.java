package com.lqyrmk.transportation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.transportation.entity.Shipper;
import com.lqyrmk.transportation.mapper.ShipperMapper;
import com.lqyrmk.transportation.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 用户（托运人）业务层实现类
 * @Author YuanmingLiu
 * @Date 2023/4/28 1:11
 */
@Service
public class ShipperServiceImpl extends ServiceImpl<ShipperMapper, Shipper> implements ShipperService {

    @Autowired
    private ShipperMapper shipperMapper;

    @Override
    public Shipper login(String shipperName, String password) {
        Shipper shipper = shipperMapper.getShipperByShippernameAndPassword(shipperName, password);
        return shipper;
    }

    @Override
    public Shipper getShipperById(Integer shipperId) {
//        Shipper shipper = shipperMapper.getShipperById(shipperId);
        Shipper shipper = shipperMapper.getShipperAndOrderByShipperIdByStepOne(shipperId);
        return shipper;
    }

    @Override
    public Shipper getShipperByName(String shipperName) {
        Shipper shipper = shipperMapper.getShipperByName(shipperName);
        return shipper;
    }

    @Override
    public void registerShipper(Shipper shipper) {
        shipperMapper.insertShipper(shipper);
    }
}
