package com.ssj.yunblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.admin.entity.Role;
import com.ssj.yunblog.admin.dao.RoleDao;
import com.ssj.yunblog.admin.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(String id) {
        return this.roleDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(Role role) {
        return roleDao.insert(role);
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleDao.update(role);
        return this.queryById(role.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.roleDao.deleteById(id) > 0;
    }
}
