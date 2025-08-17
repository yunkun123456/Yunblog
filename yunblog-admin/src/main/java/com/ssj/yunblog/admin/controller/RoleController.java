package com.ssj.yunblog.admin.controller;

import com.ssj.yunblog.admin.entity.Role;
import com.ssj.yunblog.admin.service.RoleService;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
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
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Role> queryById(@PathVariable("id") String id) {
        return Result.ok(this.roleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param role 实体
     * @return 新增结果
     */
    @PostMapping
    public Result<Role> add(@RequestBody Role role) {
        this.roleService.insert(role);
        return Result.ok();
    }

    /**
     * 编辑数据
     *
     * @param role 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result<Role> edit(Role role) {
        return Result.ok(this.roleService.update(role));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result<Boolean> deleteById(String id) {
        return Result.ok(this.roleService.deleteById(id));
    }

}

