package com.lqyrmk.transportation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.mapper.GoodsMapper;
import com.lqyrmk.transportation.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 货物业务层实现类
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:33
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

}
