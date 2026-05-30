package com.ssj.yunblog.baseInfo.entity.bo;

import lombok.Data;

/**
 * 评论查询业务对象
 *
 * @author yunkun
 * @time: 2026-05-30
 */
@Data
public class BlogCommentQueryBo {
    /**
     * 当前页码
     */
    private Integer current;

    /**
     * 每页条数
     */
    private Integer size;

    /**
     * 评论类型：message/article/knowledge
     */
    private String type;

    /**
     * 父节点ID（内容ID）
     */
    private String parentId;

    /**
     * 昵称（模糊查询）
     */
    private String nickname;
}