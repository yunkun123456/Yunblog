package com.ssj.yunblog.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 权限实体类
 *
 * @author yunkun
 * @since 2025-08-23 18:21:26
 */
@Data
@TableName("t_permission")
public class Permission {
    /**
     * 主键
     */
    private String id;
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限编码
     */
    private String permissionCode;
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

