package com.ssj.yunblog.baseInfo.entity.vo;

import lombok.Data;

/**
 * (BlogRecommend)实体类
 *
 * @author yunkun
 * @since 2025-11-22 16:36:13
 */
@Data
public class BlogRecommendVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 标题
     */
    private String tile;
    /**
     * 关联id
     */
    private String relatedId;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 类别id
     */
    private String categoryId;
    /**
     * 类别
     */
    private String categoryName;
    /**
     * 0-默认博客，1-面经，2-项目，3-知识库
     */
    private String type;
    /**
     * 权重
     */
    private Double recommendWeight;
    /**
     * 创建时间
     */
    private String createTime;

}

