package com.ssj.yunblog.common.entity;

import com.ssj.yunblog.common.constant.ResultCode;
import lombok.Data;

/**
 * 结果实体
 *
 * @author: sunshijie
 * @Date: 2025/8/17
 */
@Data
public class Result<T> {

    private String message;

    private Integer code;

    private T data;

    /**
     * 请求成功 - data
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.OK);
        result.setData(data);
        return result;
    }

    /**
     * 请求成功
     */
    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.OK);
        result.setData(null);
        result.setMessage("请求成功");
        return result;
    }

    /**
     * 请求失败
     */
    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAIL);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}
