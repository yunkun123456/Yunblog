package com.ssj.yunblog.baseInfo.entity.vo;

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
public class KnowledgeVo {
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
     * 创建时间
     */
    private String createTime;
}

