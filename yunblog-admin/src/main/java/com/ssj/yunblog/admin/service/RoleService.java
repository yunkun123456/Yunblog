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
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(String id);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Integer insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
