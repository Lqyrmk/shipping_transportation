package com.lqyrmk.transportation.mapper;

import com.lqyrmk.transportation.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description 货物持久层
 * @Author YuanmingLiu
 * @Date 2023/4/29 0:34
 */
@Mapper
public interface GoodsMapper {

    /**
     * @description: 查询所有的货物
     * @author: YuanmingLiu
     * @date: 2023/4/29 0:36
     * @param: []
     * @return: com.lqyrmk.transportation.entity.Goods
     **/
    List<Goods> getAllGoods();

    /**
     * @description: 根据筛选信息查询所有的货物
     * @author: YuanmingLiu
     * @date: 2023/4/29 13:38
     * @param: [infoMap]
     * @return: java.util.List<com.lqyrmk.transportation.entity.Goods>
     **/
    List<Goods> getAllGoodsByInfo(Map<String, Object> infoMap);

    /**
     * @description: 根据货物id查询货物信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 2:11
     * @param: [goods_id]
     * @return: com.lqyrmk.transportation.entity.Goods
     **/
    Goods getGoodsById(@Param("goodsId") Integer goodsId);

    /**
     * @description: 添加货物
     * @author: YuanmingLiu
     * @date: 2023/4/29 1:01
     * @param: [goods]
     * @return: void
     **/
    void insertGoods(Goods goods);

    /**
     * @description: 修改货物信息
     * @author: YuanmingLiu
     * @date: 2023/4/29 2:26
     * @param: [goods]
     * @return: void
     **/
    void updateGoods(Goods goods);

    /**
     * @description: 根据id删除货物
     * @author: YuanmingLiu
     * @date: 2023/4/29 2:02
     * @param: [goods_id]
     * @return: void
     **/
    void deleteGoods(@Param("goodsId") Integer goodsId);

    /**
     * @description: 减少货物的库存量
     * @author: YuanmingLiu
     * @date: 2023/4/29 19:24
     * @param: [goodsId]
     * @return: void
     **/
    void updateGoodsStock(@Param("goodsId") Integer goodsId);
}
