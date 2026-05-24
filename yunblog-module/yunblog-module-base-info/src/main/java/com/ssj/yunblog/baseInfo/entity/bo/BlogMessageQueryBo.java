package com.ssj.yunblog.baseInfo.entity.bo;

import lombok.Data;

/**
 * 留言查询对象
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Data
public class BlogMessageQueryBo {
    /**
     * 当前页码
     */
    private Integer current;
    /**
     * 每页条数
     */
    private Integer size;
    /**
     * 分类筛选（all/job/tech/life）
     */
    private String category;
    /**
     * 排序字段（createTime/likeCount）
     */
    private String sortBy;
    /**
     * 排序方式（asc/desc）
     */
    private String sortOrder;
    /**
     * 审核状态（0-待审核，1-已通过，2-已拒绝）
     */
    private Integer status;
    /**
     * 昵称（模糊查询）
     */
    private String nickname;
}