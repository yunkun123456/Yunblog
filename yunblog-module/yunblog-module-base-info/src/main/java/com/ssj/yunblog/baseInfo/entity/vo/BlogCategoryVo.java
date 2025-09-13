package com.ssj.yunblog.baseInfo.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * 博客分类实体类 vo
 *
 * @author yunkun
 * @since 2025-09-13 18:09:08
 */
@Data
public class BlogCategoryVo {
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
    /**
     * 子分类信息
     */
    private List<BlogCategoryVo> children;

}

