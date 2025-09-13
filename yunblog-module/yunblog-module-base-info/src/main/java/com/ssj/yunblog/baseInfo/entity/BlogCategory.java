package com.ssj.yunblog.baseInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * (BlogCategory)实体类
 *
 * @author yunkun
 * @since 2025-09-13 18:09:08
 */
@Data
@TableName("t_blog_category")
public class BlogCategory {
    /**
     * 主键
     */
    private String id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类级别
     */
    private Integer categoryLevel;
    /**
     * 图像地址，后续可能会拓展分类icon
     */
    private String picUrl;
    /**
     * 父级id
     */
    private String parentId;
    /**
     * 排序字段
     */
    private Integer sortNum;
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

