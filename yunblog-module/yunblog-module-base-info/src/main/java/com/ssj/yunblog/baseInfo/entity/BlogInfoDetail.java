package com.ssj.yunblog.baseInfo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (BlogInfoDetail)实体类
 *
 * @author yunkun
 * @since 2025-09-13 17:08:57
 */
@Data
@TableName("t_blog_info_detail")
public class BlogInfoDetail {
    /**
     * 主键
     */
    private String id;
    /**
     * 博客id
     */
    private String blogId;
    /**
     * 博客内容
     */
    private String content;
    /**
     * 图片地址
     */
    private String picUrl;
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

