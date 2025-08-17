package com.ssj.yunblog.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.admin.entity.Role;

/**
 * 角色信息服务接口
 *
 * @author yunkun
 * @since 2025-06-07 15:59:57
 */
public interface RoleService extends IService<Role> {

    /**
     * 通过ID查询单条数据
     */
    Role queryById(String id);

    /**
     * 新增数据
     */
    Integer insert(Role role);

    /**
     * 修改数据
     */
    Integer update(Role role);

    /**
     * 通过主键删除数据
     */
    Boolean deleteById(String id);

    /**
     * 更新角色状态
     */
    Boolean updateStatus(String id, Integer status);
}
