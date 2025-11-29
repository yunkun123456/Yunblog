package com.ssj.yunblog.baseInfo.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * (BlogInfo)实体类
 *
 * @author yunkun
 * @since 2025-09-13 17:08:18
 */
@Data
public class BlogInfoVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章介绍
     */
    private String introduction;
    /**
     * 类别信息
     */
    private BlogCategoryVo category;
    /**
     * 标签列表
     */
    private List<BlogLabelVo> labels;
    /**
     * 点赞数
     */
    private Integer likeNum;
    /**
     * 是否点赞
     */
    private Boolean likeFlag;
    /**
     * 阅读量
     */
    private Integer readNum;
    /**
     * 封面
     */
    private String coverUrl;
    /**
     * 标签列表
     */
    private List<String> tags;
    /**
     * 删除标识，0未删除，1已删除
     */
    private String delStatus;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private String updateTime;
}

