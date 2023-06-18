package com.lqyrmk.transportation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.transportation.entity.WaitingItem;
import com.lqyrmk.transportation.mapper.WaitingItemMapper;
import com.lqyrmk.transportation.service.WaitingItemService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/6/14 10:59
 */
@Service
public class WaitingItemImpl extends ServiceImpl<WaitingItemMapper, WaitingItem> implements WaitingItemService {
}
