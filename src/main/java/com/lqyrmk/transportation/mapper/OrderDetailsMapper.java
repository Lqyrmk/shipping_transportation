package com.lqyrmk.transportation.mapper;

import com.lqyrmk.transportation.entity.GoodsList;
import com.lqyrmk.transportation.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/29 19:36
 */
@Mapper
public interface OrderDetailsMapper {

    /**
     * @description: 根据货物清单添加货物订单关系
     * @author: YuanmingLiu
     * @date: 2023/4/29 19:39
     * @param: [goodsList]
     * @return: void
     **/
    void insertOrderDetails(@Param("order") Order order, @Param("goodsList") GoodsList goodsList);

    /**
     * @description: 根据订单id删除货物订单关系
     * @author: YuanmingLiu
     * @date: 2023/4/29 20:48
     * @param: [orderId]
     * @return: void
     **/
    void deleteOrderDetails(@Param("orderId") Integer orderId);
}
