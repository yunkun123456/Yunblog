package com.ssj.yunblog.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 角色信息表实体类
 *
 * @author yunkun
 * @since 2025-06-07 15:59:57
 */
@Data
@TableName("t_role")
public class Role {

    /**
     * 主键
     */
    private String id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色标识
     */
    private String roleCode;
    /**
     * 使用状态;0-禁用，1-启用
     */
    private String useStatus;
    /**
     * 删除状态;0-未删除，1-已删除
     */
    private String delStatus;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
}

