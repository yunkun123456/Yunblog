package com.ssj.yunblog.baseInfo.entity.vo;

import lombok.Data;

/**
 * 留言返回对象
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Data
public class BlogMessageVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 标题
     */
    private String title;
    /**
     * 讨论主题
     */
    private String discussionName;
    /**
     * 留言内容
     */
    private String content;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 收藏数
     */
    private Integer favoriteCount;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 分类
     */
    private String category;
}