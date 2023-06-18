package com.lqyrmk.transportation.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lqyrmk.transportation.entity.Goods;
import com.lqyrmk.transportation.entity.WaitingItem;
import com.lqyrmk.transportation.entity.WaitingList;

/**
 * @Description 货物清单业务层接口
 * @Author YuanmingLiu
 * @Date 2023/4/29 18:37
 */
public interface WaitingListService extends IService<WaitingList> {

    /**
     * @description: 查询货物清单中的所有货物
     * @author: YuanmingLiu
     * @date: 2023/6/14 11:15
     * @param: []
     * @return: com.lqyrmk.transportation.entity.WaitingList
     **/
    WaitingList getWaitingList();

    /**
     * @description: 添加货物到货物清单中
     * @author: YuanmingLiu
     * @date: 2023/4/29 18:39
     * @param: [goods]
     * @return: void
     **/
    int addItemToList(Goods goods);

    /**
     * @description: 更新运输清单中的货物
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:03
     * @param: [goods]
     * @return: int
     **/
    int updateWaitingList(WaitingItem waitingItem);

    /**
     * @description: 删除清单中的某个货物
     * @author: YuanmingLiu
     * @date: 2023/4/30 22:10
     * @param: [waitingId]
     * @return: void
     **/
    int deleteWaitingItem(Long waitingId);


//    /**
//     * @description: 删除货物清单中的货物
//     * @author: YuanmingLiu
//     * @date: 2023/4/30 19:03
//     * @param: [goodsId]
//     * @return: void
//     **/
//    void deleteGoodsList(Integer goodsId);
}
