package com.lqyrmk.transportation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.entity.LoginUser;
import com.lqyrmk.transportation.entity.WaitingItem;
import com.lqyrmk.transportation.entity.WaitingList;
import com.lqyrmk.transportation.mapper.WaitingItemMapper;
import com.lqyrmk.transportation.mapper.WaitingListMapper;
import com.lqyrmk.transportation.mapper.GoodsMapper;
import com.lqyrmk.transportation.service.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 货物清单业务层实现类
 * @Author YuanmingLiu
 * @Date 2023/4/29 18:38
 */
@Service
public class WaitingListServiceImpl extends ServiceImpl<WaitingListMapper, WaitingList> implements WaitingListService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private WaitingListMapper waitingListMapper;

    @Autowired
    private WaitingItemMapper waitingItemMapper;

    @Override
    public WaitingList getWaitingList() {

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();

        return waitingListMapper.seleteWaitingListByStep1(userId);
    }

    @Override
    public int addItemToList(Goods goods) {

        // 货物的库存量小于或等于0时不能添加进清单
        if (goods.getStock() <= 0) {
            return 0;
        }
        // 货物的库存量大于0
        // 更新库存
        goods.setStock(goods.getStock() - 1);
        goodsMapper.updateById(goods);

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();

        // 根据用户id查询清单
        LambdaQueryWrapper<WaitingList> waitingListLambdaQueryWrapper = new LambdaQueryWrapper<>();
        waitingListLambdaQueryWrapper.eq(WaitingList::getUserId, userId);
        WaitingList waitingList = waitingListMapper.selectOne(waitingListLambdaQueryWrapper);

        if (waitingList != null) {
            // 更新参数
            waitingList.setTotalPrice(waitingList.getTotalPrice() + goods.getPrice());
            waitingList.setTotalWeight(waitingList.getTotalWeight() + goods.getWeight());
            waitingListMapper.updateById(waitingList);
        } else {
            // 添加货运清单
            waitingList = new WaitingList(userId, goods.getPrice(), goods.getWeight());
            waitingListMapper.insert(waitingList);
        }

        // 根据用户id和货物id查询货物清单项
        LambdaQueryWrapper<WaitingItem> waitingItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
        waitingItemLambdaQueryWrapper.eq(WaitingItem::getUserId, userId)
                .eq(WaitingItem::getGoodsId, goods.getGoodsId());
        WaitingItem waitingItem = waitingItemMapper.selectOne(waitingItemLambdaQueryWrapper);

        if (waitingItem != null) {
            // 货物存在 更新货物数量
            waitingItem.setNum(waitingItem.getNum() + 1);
            return waitingItemMapper.updateById(waitingItem);
        }

        // 货物不存在 添加到清单里
        waitingItem = new WaitingItem(goods.getGoodsId(), userId, goods.getGoodsName(), goods.getPrice(), goods.getWeight(), 1);
        return waitingItemMapper.insert(waitingItem);

    }

    @Override
    public int updateWaitingList(WaitingItem waitingItem) {

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();

        // 原来的货物清单项
        WaitingItem oldWaitingItem = waitingItemMapper.selectById(waitingItem.getWaitingId());

        if (oldWaitingItem == null) {
            return 0;
        }

        // 更新库存
        LambdaQueryWrapper<Goods> goodsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsLambdaQueryWrapper.eq(Goods::getGoodsId, waitingItem.getGoodsId());
        Goods goods = goodsMapper.selectOne(goodsLambdaQueryWrapper);

        if (goods.getStock() + oldWaitingItem.getNum() - waitingItem.getNum() < 0) {
            // 更新完后货物的库存量小于0
            waitingItem.setNum(goods.getStock() + oldWaitingItem.getNum());
            goods.setStock(0);
        }
        else {
            // 更新完后货物的库存量大于等于0
            goods.setStock(goods.getStock() + oldWaitingItem.getNum() - waitingItem.getNum());
        }
        goodsMapper.updateById(goods);

        // 根据用户id查询清单
        LambdaQueryWrapper<WaitingList> waitingListLambdaQueryWrapper = new LambdaQueryWrapper<>();
        waitingListLambdaQueryWrapper.eq(WaitingList::getUserId, userId);
        WaitingList waitingList = waitingListMapper.selectOne(waitingListLambdaQueryWrapper);

        if (waitingList == null) {
            return 0;
        }

        // 计算更新后的的价格和重量
        // 新的总 = 原来的总 - 价格*原来的数量 + 价格*新的数量
        Double newPrice = waitingList.getTotalPrice() - oldWaitingItem.getPrice() * oldWaitingItem.getNum() + waitingItem.getPrice() * waitingItem.getNum();
        Double newWeight = waitingList.getTotalWeight() - oldWaitingItem.getWeight() * oldWaitingItem.getNum() + waitingItem.getWeight() * waitingItem.getNum();

        // 修改清单价格、重量
        waitingList.setTotalPrice(newPrice);
        waitingList.setTotalWeight(newWeight);
        waitingListMapper.updateById(waitingList);

        if (waitingItem.getNum() == 0) {
            return waitingItemMapper.deleteById(waitingItem);
        }

        // 根据用户id修改货物清单项
        return waitingItemMapper.updateById(waitingItem);
    }

    @Override
    public int deleteWaitingItem(Long waitingId) {

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();

        // 根据用户id查询货物清单项
        LambdaQueryWrapper<WaitingItem> waitingItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
        waitingItemLambdaQueryWrapper.eq(WaitingItem::getUserId, userId)
                .eq(WaitingItem::getWaitingId, waitingId);
        WaitingItem waitingItem = waitingItemMapper.selectOne(waitingItemLambdaQueryWrapper);

        if (waitingItem == null) {
            return 0;
        }

        // 根据用户id查询清单
        LambdaQueryWrapper<WaitingList> waitingListLambdaQueryWrapper = new LambdaQueryWrapper<>();
        waitingListLambdaQueryWrapper.eq(WaitingList::getUserId, userId);
        WaitingList waitingList = waitingListMapper.selectOne(waitingListLambdaQueryWrapper);

        // 修改清单价格、重量
        waitingList.setTotalPrice(waitingList.getTotalPrice() - waitingItem.getPrice() * waitingItem.getNum());
        waitingList.setTotalWeight(waitingList.getTotalWeight() - waitingItem.getWeight() * waitingItem.getNum());
        waitingListMapper.updateById(waitingList);

        // 删除清单项
        return waitingItemMapper.deleteById(waitingItem);

    }
}
