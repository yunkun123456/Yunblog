package com.ssj.yunblog.baseInfo.entity.bo;

import lombok.Data;

/**
 * 知识库信息实体类
 *
 * @author yunkun
 * @since 2026-01-03 20:18:34
 */
@Data
public class KnowledgeInfoBo {
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
}

