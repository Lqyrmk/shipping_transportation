package com.lqyrmk.transportation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lqyrmk.transportation.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author YuanmingLiu
 * @Date 2023/6/8 15:04
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * @description: 根据用户id查询对应的权限信息
     * @author: YuanmingLiu
     * @date: 2023/6/8 15:05
     * @param: [userId]
     * @return: java.util.List<java.lang.String>
     **/
    List<String> selectPermsByUserId(@Param("userId") Long userId);

}
