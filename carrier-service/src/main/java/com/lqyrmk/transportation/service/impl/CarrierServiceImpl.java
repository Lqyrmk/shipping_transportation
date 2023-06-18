package com.lqyrmk.transportation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.transportation.entity.Carrier;
import com.lqyrmk.transportation.mapper.CarrierMapper;
import com.lqyrmk.transportation.service.CarrierService;
import org.springframework.stereotype.Service;

/**
 * @Description 承运商业务层
 * @Author YuanmingLiu
 * @Date 2023/4/29 21:51
 */
@Service
public class CarrierServiceImpl extends ServiceImpl<CarrierMapper, Carrier> implements CarrierService {
}
