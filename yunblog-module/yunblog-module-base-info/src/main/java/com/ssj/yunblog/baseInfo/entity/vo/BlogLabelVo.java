package com.ssj.yunblog.baseInfo.entity.vo;

import lombok.Data;

/**
 * (BlogLabel)实体类
 *
 * @author yunkun
 * @since 2025-09-13 18:09:44
 */
@Data
public class BlogLabelVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 创建时间
     */
    private String createTime;
}

