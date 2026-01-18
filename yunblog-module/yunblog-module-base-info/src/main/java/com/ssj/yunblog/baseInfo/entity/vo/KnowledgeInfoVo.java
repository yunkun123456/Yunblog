package com.ssj.yunblog.baseInfo.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
     * 下级列表
     */
    private List<KnowledgeInfoVo> children;


}

