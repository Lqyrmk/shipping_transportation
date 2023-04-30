package com.lqyrmk.transportation.service;

import com.lqyrmk.transportation.entity.Goods;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 货物业务层接口
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:33
 */
public interface GoodsService {

    /**
     * @description: 获取所有的货物信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 0:35
     * @param: []
     * @return: com.lqyrmk.transportation.entity.Goods
     **/
    List<Goods> getAllGoods();

    /**
     * @description: 根据筛选信息获取货物信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 13:37
     * @param: [infoMap]
     * @return: java.util.List<com.lqyrmk.transportation.entity.Goods>
     **/
    List<Goods> getGoodsByInfo(Map<String, Object> infoMap);

    /**
     * @description: 根据id查询货物信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 2:10
     * @param: [goods_id]
     * @return: com.lqyrmk.transportation.entity.Goods
     **/
    Goods getGoodsById(Integer goodsId);

    /**
     * @description: 新增货物
     * @author: YuanmingLiu
     * @date: 2023/4/29 1:00
     * @param: [goods]
     * @return: void
     **/
    void saveGoods(Goods goods);

    /**
     * @description: 修改货物信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 2:25
     * @param: [goods]
     * @return: void
     **/
    void updateGoods(Goods goods);

    /**
     * @description: 根据id删除货物
     * @author: YuanmingLiu
     * @date: 2023/4/29 2:03
     * @param: [goods_id]
     * @return: void
     **/
    void deleteGoods(Integer goodsId);

}
