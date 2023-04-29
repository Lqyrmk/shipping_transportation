package com.lqyrmk.transportation.mapper;

import com.lqyrmk.transportation.entity.Shipper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/28 1:11
 */
@Mapper
public interface ShipperMapper {

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
}
