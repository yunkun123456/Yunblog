package com.ssj.yunblog.baseInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (BlogRecommend)实体类
 *
 * @author yunkun
 * @since 2025-11-22 16:36:13
 */
@Data
@TableName("t_blog_recommend")
public class BlogRecommend{
    /**
     * 主键
     */
    private String id;
    /**
     * 标题
     */
    private String title;
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
     * 0-默认博客，1-面经，2-项目，3-知识库
     */
    private String type;
    /**
     * 权重
     */
    private String weight;
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

