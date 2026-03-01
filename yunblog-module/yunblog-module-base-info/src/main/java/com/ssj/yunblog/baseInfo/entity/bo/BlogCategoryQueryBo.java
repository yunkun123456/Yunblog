package com.ssj.yunblog.baseInfo.entity.bo;

import lombok.Data;

/**
 * 类别查询 bo
 *
 * @author: yunkun
 * @Date: 2025/11/10
 */
@Data
public class BlogCategoryQueryBo {

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
}
