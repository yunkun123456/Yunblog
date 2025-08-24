package com.ssj.yunblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.admin.dao.RolePermissionDao;
import com.ssj.yunblog.admin.entity.Permission;
import com.ssj.yunblog.admin.dao.PermissionDao;
import com.ssj.yunblog.admin.entity.RolePermission;
import com.ssj.yunblog.admin.entity.bo.PermissionBo;
import com.ssj.yunblog.admin.service.PermissionService;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 权限管理 service 层
 *
 * @author yunkun
 * @since 2025-08-23 18:21:26
 */
@Service("tPermissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {

    @Resource
    private PermissionDao permissionDao;

    @Resource
    private RolePermissionDao rolePermissionDao;

    /**
     * 根据角色id查询权限
     */
    @Override
    public List<Permission> queryPermissionsByRoleId(String id) {
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId, id)
                .eq(RolePermission::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        List<RolePermission> rolePermissions = rolePermissionDao.selectList(queryWrapper);
        List<String> list = rolePermissions.stream().map(RolePermission::getPermissionId).toList();
        LambdaQueryWrapper<Permission> query = new LambdaQueryWrapper<>();
        query.in(Permission::getId, list)
                .eq(Permission::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        return permissionDao.selectList(query);
    }

    /**
     * 通过角色编码查询单条数据
     */
    @Override
    public Permission queryByCode(String code) {
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permission::getPermissionCode, code)
                .eq(Permission::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        return permissionDao.selectOne(queryWrapper);
    }

    /**
     * 新增权限
     */
    @Override
    public Result<Boolean> add(PermissionBo permissionBo) {
        Permission exist = queryByCode(permissionBo.getPermissionCode());
        if (exist != null) {
            return Result.fail("权限标识已存在");
        }
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionBo, permission);
        permission.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        if (permissionDao.insert(permission) <= 0) {
            return Result.fail("新增权限失败");
        }
        return Result.ok(true, "新增权限成功");
    }
}
