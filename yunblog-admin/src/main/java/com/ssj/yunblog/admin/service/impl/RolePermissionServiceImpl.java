package com.ssj.yunblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.admin.entity.RolePermission;
import com.ssj.yunblog.admin.dao.RolePermissionDao;
import com.ssj.yunblog.admin.service.RolePermissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;


/**
 * 角色权限关联表(RolePermission)表服务实现类
 *
 * @author yunkun
 * @since 2025-08-23 18:30:13
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermission> implements RolePermissionService {

    @Resource
    private RolePermissionDao rolePermissionDao;

}
