package com.ssj.yunblog.baseInfo.entity.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 评论业务对象
 *
 * @author yunkun
 * @time: 2026-05-30
 */
@Data
public class BlogCommentBo {
    /**
     * 主键（编辑时必填）
     */
    private String id;

    /**
     * 类型：message/article/knowledge/comment
     */
    @NotBlank(message = "评论类型不能为空")
    private String type;

    /**
     * 父节点ID（内容ID或评论ID）
     */
    @NotBlank(message = "父节点ID不能为空")
    private String parentId;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    private String content;

    /**
     * 回复的昵称（用于楼中楼）
     */
    private String replyNickname;
}