package com.ssj.yunblog.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 角色权限关联实体
 *
 * @author yunkun
 * @since 2025-08-23 18:30:13
 */
@Data
@TableName("t_role_permission")
public class RolePermission {
    /**
     * 主键
     */
    private String id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 权限id
     */
    private String permissionId;
    /**
     * 删除状态;0-未删除，1-已删除
     */
    private String delStatus;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    private Date updatedTime;

}

