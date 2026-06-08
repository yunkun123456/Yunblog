package com.ssj.yunblog.baseInfo.entity.vo;

import lombok.Data;

/**
 * 通用评论返回对象
 *
 * @author yunkun
 * @time: 2026-05-30
 */
@Data
public class BlogCommentVo {
    /**
     * 主键
     */
    private String id;

    /**
     * 类型
     */
    private String type;

    /**
     * 父节点ID
     */
    private String parentId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 回复的昵称
     */
    private String replyNickname;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 是否已点赞
     */
    private Boolean liked;
}