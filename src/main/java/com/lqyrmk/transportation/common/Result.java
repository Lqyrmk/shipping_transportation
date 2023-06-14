package com.lqyrmk.transportation.common;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 公共返回对象
 * @Author YuanmingLiu
 * @Date 2023/3/18 16:58
 */
@Data
@ApiModel("公共返回结果")
public class Result<T> {

    private Integer code; // 编码：1为成功，0和其他数字为失败

    private String msg; // 错误信息

    private T data; // 数据

    private Map map = new HashMap(); // 动态数据

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.data = object;
//        result.msg = msg;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T object, String msg) {
        Result<T> result = new Result<>();
        result.data = object;
        result.msg = msg;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
