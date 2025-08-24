package com.ssj.yunblog.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.admin.entity.Permission;
import com.ssj.yunblog.admin.entity.bo.PermissionBo;
import com.ssj.yunblog.common.entity.Result;

import java.util.List;

/**
 * (TPermission)表服务接口
 *
 * @author yunkun
 * @since 2025-08-23 18:21:26
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 通过角色id查询权限
     */
    List<Permission> queryPermissionsByRoleId(String id);

    /**
     * 新增权限
     */
    Result<Boolean> add(PermissionBo permissionBo);

    /**
     * 通过权限标识查询权限
     */
    Permission queryByCode(String code);
}
