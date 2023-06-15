package com.lqyrmk.transportation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.transportation.entity.Carrier;
import com.lqyrmk.transportation.entity.Shipper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 承运商持久层
 * @Author YuanmingLiu
 * @Date 2023/4/29 21:52
 */
@Mapper
public interface CarrierMapper extends BaseMapper<Carrier> {

    /**
     * @description: 根据订单id查询订单详细信息 第二步（Carrier）
     * @author: YuanmingLiu
     * @date: 2023/4/29 21:53
     * @param: [carrierId]
     * @return: com.lqyrmk.transportation.entity.Carrier
     **/
    Carrier getOrderByOrderIdByStep2(@Param("carrierId") Long carrierId);

}
