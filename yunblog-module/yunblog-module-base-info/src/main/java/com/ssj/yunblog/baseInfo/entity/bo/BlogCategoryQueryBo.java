package com.ssj.yunblog.baseInfo.entity.bo;

import lombok.Data;

/**
 * 类别查询 bo
 *
 * @author: yunkun
 * @Date: 2025/11/10
 */
@Data
public class BlogCategoryQueryBo {

    /**
     * 级别
     */
    private Integer level;

    /**
     * 父级id
     */
    private String parentId;
}
