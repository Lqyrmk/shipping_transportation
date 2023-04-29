package com.lqyrmk.transportation.service;

import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.entity.GoodsList;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/4/29 18:37
 */
public interface GoodsListService {

    /**
     * @description: 查询货物清单的所有货物
     * @author: YuanmingLiu
     * @date: 2023/4/29 19:04
     * @param: []
     * @return: java.util.List<com.lqyrmk.transportation.entity.GoodsList>
     **/
    List<GoodsList> getGoodsList();

    /**
     * @description: 添加货物到货物清单中
     * @author: YuanmingLiu
     * @date: 2023/4/29 18:39
     * @param: [goods]
     * @return: void
     **/
    void addGoodsToList(Integer goodsId);


}
