package com.lqyrmk.transportation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.transportation.entity.WaitingItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/6/14 11:00
 */
@Mapper
public interface WaitingItemMapper extends BaseMapper<WaitingItem> {

    /**
     * @description: 根据用户id查询货物清单中的所有货物 2
     * @author: YuanmingLiu
     * @date: 2023/6/14 11:25
     * @param: [listId]
     * @return: com.lqyrmk.transportation.entity.WaitingItem
     **/
    WaitingItem seleteWaitingListByStep2(@Param("listId") Long listId);

}
