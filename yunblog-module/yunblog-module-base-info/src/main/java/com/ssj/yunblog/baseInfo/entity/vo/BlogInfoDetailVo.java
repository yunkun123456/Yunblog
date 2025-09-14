package com.ssj.yunblog.baseInfo.entity.vo;

import lombok.Data;

/**
 * (BlogInfoDetail)实体类
 *
 * @author yunkun
 * @since 2025-09-13 17:08:57
 */
@Data
public class BlogInfoDetailVo {
    /**
     * 主键
     */
    private String id;
    /**
     * 博客id
     */
    private String blogId;
    /**
     * 博客内容
     */
    private String content;
    /**
     * 图片地址
     */
    private String picUrl;
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

