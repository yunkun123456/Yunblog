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
    private Integer pageSize = 10;
    /**
     * 当前页码
     */
    private Integer pageNum = 1;
    /**
     * 排序字段
     */
    private String sort = "default";
    /**
     * 降序:true, 升序:false
     */
    private Boolean desc = true;
}
