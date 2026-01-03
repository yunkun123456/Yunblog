package com.ssj.yunblog.baseInfo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 知识库信息实体类
 *
 * @author yunkun
 * @since 2026-01-03 20:18:34
 */
@TableName("t_knowledge_info")
@Data
public class KnowledgeInfo {
    /**
     * 主键
     */
    private String id;
    /**
     * 知识库关联id
     */
    private String knowledgeId;
    /**
     * 分组名称，或文章名称
     */
    private String name;
    /**
     * 上级id
     */
    private String parentId;
    /**
     * 1分组，2文章
     */
    private String type;
    /**
     * 层级
     */
    private String level;
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

