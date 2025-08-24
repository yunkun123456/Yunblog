package com.ssj.yunblog.admin.controller;

import com.ssj.yunblog.admin.entity.Role;
import com.ssj.yunblog.admin.entity.bo.RoleBo;
import com.ssj.yunblog.admin.entity.bo.RolePermissionBo;
import com.ssj.yunblog.admin.service.RoleService;
import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 角色信息控制层
 *
 * @author yunkun
 * @since 2025-06-07 15:59:57
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 新增角色
     */
    @PostMapping
    public Result<Boolean> add(@Validated(Add.class) @RequestBody RoleBo role) {
        return roleService.add(role);
    }

    /**
     * 角色关联权限
     */
    @PostMapping("/associatedPermissions")
    public Result<Boolean> associatedPermissions(@Validated(Add.class) @RequestBody RolePermissionBo rolePermission) {
        return roleService.associatedPermissions(rolePermission);
    }

}

