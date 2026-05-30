package com.ssj.yunblog.baseInfo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 通用评论实体类
 *
 * @author yunkun
 * @time: 2026-05-30
 */
@Data
@TableName("t_blog_comment")
public class BlogComment {
    /**
     * 主键
     */
    private String id;

    /**
     * 类型：message/article/knowledge/comment
     */
    private String type;

    /**
     * 父节点ID（内容ID或评论ID）
     */
    private String parentId;

    /**
     * 评论用户ID
     */
    private String userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 回复的昵称
     */
    private String replyNickname;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 删除标识，0未删除，1已删除
     */
    private String delStatus;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}