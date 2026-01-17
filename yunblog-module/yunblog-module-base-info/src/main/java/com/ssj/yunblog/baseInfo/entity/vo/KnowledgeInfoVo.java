package com.ssj.yunblog.baseInfo.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 知识库信息实体类 - vo
 *
 * @author yunkun
 * @since 2026-01-03 20:18:34
 */
@Data
public class KnowledgeInfoVo {
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
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}

