package com.ssj.yunblog.common.constant;

/**
 * 结果状态码
 *
 * @author: yunkun
 * @Date: 2025/8/17
 */
public interface ResultCode {

    /**
     * 请求成功状态码
     */
    public final static Integer OK = 1;

    /**
     * 请求失败状态码
     */
    public final static Integer FAIL = -1;

    /**
     * 未登录状态码
     */
    public final static Integer UNAUTHORIZED = 401;

    /**
     * 无权限状态码
     */
    public final static Integer FORBIDDEN = 403;
}
