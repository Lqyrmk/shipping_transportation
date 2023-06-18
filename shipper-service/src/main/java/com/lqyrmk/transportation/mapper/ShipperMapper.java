package com.lqyrmk.transportation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.transportation.entity.Shipper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 用户（托运人）持久层
 * @Author YuanmingLiu
 * @Date 2023/4/28 1:11
 */
@Mapper
public interface ShipperMapper extends BaseMapper<Shipper> {

    /**
     * @description: 根据用户名和密码查询托运人
     * @author: YuanmingLiu
     * @date: 2023/4/28 1:19
     * @param: [shipperName, password]
     * @return: com.lqyrmk.transportation.entity.Shipper
     **/
    Shipper getShipperByShippernameAndPassword(@Param("shipperName") String shipperName,
                                         @Param("password") String password);

    /**
     * @description: 根据id查询托运人信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 21:07
     * @param: [shipperId]
     * @return: com.lqyrmk.transportation.entity.Shipper
     **/
    Shipper getShipperById(@Param("shipperId") Integer shipperId);

    /**
     * @description: 根据用户名查询托运人
     * @author: YuanmingLiu
     * @date: 2023/4/30 18:08
     * @param: [shipperName]
     * @return: com.lqyrmk.transportation.entity.Shipper
     **/
    Shipper getShipperByName(@Param("shipperName") String shipperName);

    /**
     * @description: 根据托运人id查询托运人信息 第一步
     * @author: YuanmingLiu
     * @date: 2023/4/29 23:51
     * @param: [shipperId]
     * @return: com.lqyrmk.transportation.entity.Shipper
     **/
    Shipper getShipperAndOrderByShipperIdByStepOne(@Param("shipperId") Integer shipperId);

    /**
     * @description: 根据订单id查询订单详细信息 第二步（Shipper）
     * @author: YuanmingLiu
     * @date: 2023/4/29 21:46
     * @param: [shipperId]
     * @return: com.lqyrmk.transportation.entity.Shipper
     **/
    Shipper getOrderByOrderIdByStep2(@Param("shipperId") Long shipperId);

    /**
     * @description: 添加托运人信息
     * @author: YuanmingLiu
     * @date: 2023/4/30 18:13
     * @param: [shipper]
     * @return: void
     **/
    void insertShipper(Shipper shipper);
}
