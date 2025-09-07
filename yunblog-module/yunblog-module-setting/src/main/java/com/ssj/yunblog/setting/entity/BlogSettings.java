package com.ssj.yunblog.setting.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 博客设置实体类
 *
 * @author yunkun
 * @since 2025-09-07 11:57:32
 */
@Data
@TableName(value = "t_blog_settings")
public class BlogSettings {
    /**
     * 主键
     */
    private String id;
    /**
     * 博客名称
     */
    private String blogName;
    /**
     * 作者名称
     */
    private String author;
    /**
     * 作者介绍
     */
    private String introduction;
    /**
     * 作者头像
     */
    private String avatar;
    /**
     * GITHUB地址
     */
    private String githubHome;
    /**
     * CSDN地址
     */
    private String csdnHome;
    /**
     * GITEE地址
     */
    private String giteeHome;
    /**
     * 知乎地址
     */
    private String zhihuHome;
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

