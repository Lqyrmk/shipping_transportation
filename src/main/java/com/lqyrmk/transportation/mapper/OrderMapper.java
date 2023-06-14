package com.lqyrmk.transportation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.transportation.entity.Order;
import com.lqyrmk.transportation.entity.Shipper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 订单持久层
 * @Author YuanmingLiu
 * @Date 2023/4/29 16:29
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * @description: 查询所有的订单
     * @author: YuanmingLiu
     * @date: 2023/4/29 16:35
     * @param: []
     * @return: java.util.List<com.lqyrmk.transportation.entity.Order>
     **/
    List<Order> getAllOrders();

    /**
     * @description: 根据信息
     * @author: YuanmingLiu
     * @date: 2023/4/30 16:30
     * @param: [keywords]
     * @return: java.util.List<com.lqyrmk.transportation.entity.Order>
     **/
    List<Order> getOrdersByInfo(@Param("keywords") String keywords);

    /**
     * @description: 根据订单id查询订单详细信息 第一步
     * @author: YuanmingLiu
     * @date: 2023/4/29 21:35
     * @param: [orderId]
     * @return: com.lqyrmk.transportation.entity.Order
     **/
    Order getOrderByOrderIdByStep1(@Param("orderId") Long orderId);

    /**
     * @description: 根据托运人id查询托运人信息 第二步（order）
     * @author: YuanmingLiu
     * @date: 2023/4/30 0:02
     * @param: [shipperId]
     * @return: java.util.List<com.lqyrmk.transportation.entity.Order>
     **/
    List<Order> getShipperAndOrderByShipperIdByStepTwo(@Param("shipperId") Integer shipperId);

    /**
     * @description: 添加订单信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 16:39
     * @param: [order]
     * @return: int
     **/
    int addOrder(Order order);

    /**
     * @description: 修改订单信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 17:45
     * @param: [order]
     * @return: int
     **/
    int updateOrderById(Order order);

    /**
     * @description: 删除订单
     * @author: YuanmingLiu
     * @date: 2023/4/29 18:20
     * @param: [orderId]
     * @return: int
     **/
    int deleteOrderById(@Param("orderId") Long orderId);

    /**
     * @description: 更新订单中的总价和总重
     * @author: YuanmingLiu
     * @date: 2023/4/29 19:58
     * @param: [order]
     * @return: int
     **/
    int updateOrderPriceAndWeight(Order order);

}
