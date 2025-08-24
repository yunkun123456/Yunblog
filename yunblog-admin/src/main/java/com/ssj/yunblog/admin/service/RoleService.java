package com.ssj.yunblog.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.admin.entity.Role;
import com.ssj.yunblog.admin.entity.bo.RoleBo;
import com.ssj.yunblog.admin.entity.bo.RolePermissionBo;
import com.ssj.yunblog.common.entity.Result;

/**
 * 角色信息服务接口
 *
 * @author yunkun
 * @since 2025-06-07 15:59:57
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据code查询角色信息
     */
    Role queryByCode(String code);

    /**
     * 新增角色
     */
    Result<Boolean> add(RoleBo role);

    /**
     * 角色关联权限
     */
    Result<Boolean> associatedPermissions(RolePermissionBo rolePermission);
}
