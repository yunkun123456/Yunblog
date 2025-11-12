package com.ssj.yunblog.baseInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (BlogInfo)实体类
 *
 * @author yunkun
 * @since 2025-09-13 17:08:18
 */
@Data
@TableName("t_blog_info")
public class BlogInfo {
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
     * 类别id
     */
    private String categoryId;
    /**
     * 标签id
     */
    private String labelId;
    /**
     * 阅读量
     */
    private Integer readNum;
    /**
     * 点赞数
     */
    private Integer likeNum;
    /**
     * 封面
     */
    private String coverUrl;
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

