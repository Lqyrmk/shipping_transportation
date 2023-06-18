package com.lqyrmk.transportation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.transportation.entity.WaitingList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 货物清单持久层
 * @Author YuanmingLiu
 * @Date 2023/4/29 18:38
 */
@Mapper
public interface WaitingListMapper extends BaseMapper<WaitingList> {

    /**
     * @description: 根据用户id查询货物清单中的所有货物 1
     * @author: YuanmingLiu
     * @date: 2023/6/14 11:17
     * @param: [userId]
     * @return: com.lqyrmk.transportation.entity.WaitingList
     **/
    WaitingList seleteWaitingListByStep1(@Param("userId") Long userId);

//    /**
//     * @description: 查询货物清单的所有货物
//     * @author: YuanmingLiu
//     * @date: 2023/4/29 19:05
//     * @param: []
//     * @return: java.util.List<com.lqyrmk.transportation.entity.GoodsList>
//     **/
//    List<GoodsList> getGoodsList();
//
//    /**
//     * @description: 根据id查询货物信息
//     * @author: YuanmingLiu
//     * @date: 2023/4/29 18:50
//     * @param: [goods]
//     * @return: com.lqyrmk.transportation.entity.Goods
//     **/
//    GoodsList getGoodsListByGoodsId(@Param("goodsId") Integer goodsId);
//
//    /**
//     * @description: 添加货物到清单中
//     * @author: YuanmingLiu
//     * @date: 2023/4/29 18:40
//     * @param: [goods]
//     * @return: void
//     **/
//    void addGoodsToList(Goods goods);
//
//    /**
//     * @description: 更新货物数量
//     * @author: YuanmingLiu
//     * @date: 2023/4/29 18:54
//     * @param: [goodsId]
//     * @return: void
//     **/
//    void updateGoodsNumber(@Param("goodsId") Integer goodsId);
//
//    /**
//     * @description: 清空货物清单
//     * @author: YuanmingLiu
//     * @date: 2023/4/29 19:34
//     * @param: []
//     * @return: void
//     **/
//    void clearGoodsList();
//
//    /**
//     * @description:  删除货物清单中的货物
//     * @author: YuanmingLiu
//     * @date: 2023/4/30 19:03
//     * @param: [goodsId]
//     * @return: void
//     **/
//    void deleteGoodsList(@Param("goodsId") Integer goodsId);
}
