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

    @Resource
    private RoleService roleService;

    /**
     * 通过主键查询单条角色信息
     */
    @GetMapping("/{id}")
    public Result<Role> queryById(@PathVariable("id") String id) {
        return Result.ok(roleService.queryById(id));
    }

    /**
     * 新增角色
     */
    @PostMapping
    public Result add(@RequestBody Role role) {
        roleService.insert(role);
        return Result.ok();
    }

    /**
     * 编辑角色信息
     */
    @PutMapping
    public Result edit(Role role) {
        roleService.update(role);
        return Result.ok();
    }

    /**
     * 删除角色数据
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        roleService.deleteById(id);
        return Result.ok();
    }

    /**
     * 更新角色状态
     */
    @PutMapping("/status/{id}")
    public Result<Boolean> updateStatus(@PathVariable("id") String id, @RequestParam("status") Integer status) {
        return Result.ok(roleService.updateStatus(id, status));
    }

}

