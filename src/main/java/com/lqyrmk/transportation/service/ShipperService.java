package com.lqyrmk.transportation.service;

import com.lqyrmk.transportation.entity.Shipper;

/**
 * @Description 用户（托运人）业务层接口
 * @Author YuanmingLiu
 * @Date 2023/4/28 1:11
 */
public interface ShipperService {

    /**
     * @description: 用户登录
     * @author: YuanmingLiu
     * @date: 2023/4/28 1:16
     * @param: [shipperName, password]
     * @return: com.lqyrmk.transportation.entity.Shipper
     **/
    Shipper login(String shipperName, String password);

    /**
     * @description: 根据id获取托运人信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 21:06
     * @param: [shipperId]
     * @return: com.lqyrmk.transportation.entity.Shipper
     **/
    Shipper getShipperById(Integer shipperId);

    /**
     * @description: 根据用户名查询托运人
     * @author: YuanmingLiu
     * @date: 2023/4/30 18:07
     * @param: [shipperName]
     * @return: com.lqyrmk.transportation.entity.Shipper
     **/
    Shipper getShipperByName(String shipperName);

    /**
     * @description: 注册托运人
     * @author: YuanmingLiu
     * @date: 2023/4/30 18:12
     * @param: [shipper]
     * @return: void
     **/
    void registerShipper(Shipper shipper);
}
