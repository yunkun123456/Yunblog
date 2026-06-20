package com.ssj.yunblog.common.entity;

import lombok.Data;

/**
 * 查询参数基类
 *
 * @author: yunkun
 * @Date: 2026/6/20
 */
@Data
public class BaseQueryParam {

    /**
     * 分页大小
     */
    private Integer size = 10;
    /**
     * 当前页码
     */
    private Integer current = 1;
    /**
     * 排序字段
     */
    private String sort = "default";
    /**
     * 降序:false, 升序:true
     */
    private Boolean asc = false;
}
