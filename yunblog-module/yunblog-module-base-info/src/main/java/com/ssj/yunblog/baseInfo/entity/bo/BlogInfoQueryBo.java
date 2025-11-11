package com.ssj.yunblog.baseInfo.entity.bo;

import lombok.Data;

/**
 * (BlogInfo)实体类
 *
 * @author yunkun
 * @since 2025-09-13 17:08:18
 */
@Data
public class BlogInfoQueryBo {
    /**
     * 标题
     */
    private String searchTitle;
    /**
     * 类别id
     */
    private String categoryId;
    /**
     * 标签id
     */
    private String labelId;
    /**
     * 分页大小
     */
    private Integer pageSize = 10;
    /**
     * 当前页码
     */
    private Integer pageNum = 1;
}

