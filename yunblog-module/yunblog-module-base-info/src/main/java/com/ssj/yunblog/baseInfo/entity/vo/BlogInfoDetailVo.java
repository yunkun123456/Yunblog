package com.ssj.yunblog.baseInfo.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * (BlogInfoDetail)实体类
 *
 * @author yunkun
 * @since 2025-09-13 17:08:57
 */
@Data
public class BlogInfoDetailVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 博客id
     */
    private String blogId;
    /**
     * 博客标题
     */
    private String title;
    /**
     * 博客封面
     */
    private String coverUrl;
    /**
     * 文章介绍
     */
    private String introduction;
    /**
     * 博客一级分类
     */
    private String primaryCategoryId;
    /**
     * 博客二级分类
     */
    private String categoryId;
    /**
     * 博客标签列表
     */
    private List<String> labels;
    /**
     * 是否推荐,0-不推荐，1-权重低，2-权重中，3权重高(后续扩展)
     */
    private String recommend;
    /**
     * 博客内容
     */
    private String content;
    /**
     * 图片地址
     */
    private String picUrl;
}

