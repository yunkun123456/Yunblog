package com.ssj.yunblog.baseInfo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * (Knowledge)实体类
 *
 * @author yunkun
 * @since 2026-01-03 20:06:20
 */
@Data
@TableName("t_knowledge")
public class Knowledge{
    /**
     * 主键
     */
    private String id;
    /**
     * 知识库名称
     */
    private String name;
    /**
     * 知识库标题
     */
    private String title;
    /**
     * 知识库介绍
     */
    private String introduction;
    /**
     * 知识库封面
     */
    private String coverUrl;
    /**
     * 是否公开，0私有，1公开
     */
    private String isPublic;
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

