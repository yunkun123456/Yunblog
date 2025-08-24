package com.ssj.yunblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.admin.entity.Permission;
import com.ssj.yunblog.admin.entity.Role;
import com.ssj.yunblog.admin.dao.RoleDao;
import com.ssj.yunblog.admin.entity.RolePermission;
import com.ssj.yunblog.admin.entity.bo.RoleBo;
import com.ssj.yunblog.admin.entity.bo.RolePermissionBo;
import com.ssj.yunblog.admin.service.RolePermissionService;
import com.ssj.yunblog.admin.service.RoleService;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色信息服务实现类
 *
 * @author yunkun
 * @since 2025-06-07 15:59:57
 */
@Service("RoleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 通过角色编码查询单条数据
     */
    @Override
    public Role queryByCode(String code) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getRoleCode, code)
                .eq(Role::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        return roleDao.selectOne(queryWrapper);
    }

    /**
     * 新增角色
     */
    @Override
    public Result<Boolean> add(RoleBo roleBo) {
        Role exist = queryByCode(roleBo.getRoleCode());
        if (exist != null) {
            return Result.fail("角色标识已存在");
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleBo, role);
        role.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        if (roleDao.insert(role) <= 0) {
            return Result.fail("新增角色失败");
        }
        return Result.ok(true, "新增角色成功");
    }

    /**
     * 角色关联权限
     */
    @Override
    public Result<Boolean> associatedPermissions(RolePermissionBo rolePermission) {
        List<String> permissionIds = rolePermission.getPermissionIds();
        String roleId = rolePermission.getRoleId();
        List<RolePermission> list = permissionIds.stream().map((item) -> {
            RolePermission rolePermissionItem = new RolePermission();
            rolePermissionItem.setRoleId(roleId);
            rolePermissionItem.setPermissionId(item);
            rolePermissionItem.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
            return rolePermissionItem;
        }).toList();
        if (!rolePermissionService.saveBatch(list)) {
            return Result.fail("关联角色权限失败！");
        }
        return Result.ok(true, "关联角色权限成功！");
    }
}
