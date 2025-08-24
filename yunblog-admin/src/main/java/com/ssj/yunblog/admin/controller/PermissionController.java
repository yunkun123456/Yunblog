package com.ssj.yunblog.admin.controller;

import com.ssj.yunblog.admin.entity.bo.PermissionBo;
import com.ssj.yunblog.admin.service.PermissionService;
import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 权限控制层
 *
 * @author yunkun
 * @since 2025-08-23 18:21:26
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    /**
     * 新增权限
     */
    @PostMapping
    public Result<Boolean> add(@Validated(Add.class) @RequestBody PermissionBo permissionBo) {
        return permissionService.add(permissionBo);
    }

}

