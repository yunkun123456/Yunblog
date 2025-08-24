package com.ssj.yunblog.admin.entity.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.api.Update;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

/**
 * 角色信息 - bo
 *
 * @author yunkun
 * @since 2025-06-07 15:59:57
 */
@Data
public class RoleBo {

    /**
     * 主键
     */
    @NotBlank(message = "主键不能为空!", groups = {Update.class})
    @Size(max = 32, message = "主键长度不能超过32!", groups = {Update.class})
    private String id;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空!", groups = {Update.class, Add.class})
    @Size(max = 20, message = "角色名称不能超过20!", groups = {Update.class, Add.class})
    private String roleName;

    /**
     * 角色标识
     */
    @NotBlank(message = "角色标识不能为空!", groups = {Update.class, Add.class})
    @Size(max = 20, message = "角色标识不能超过20!", groups = {Update.class, Add.class})
    private String roleCode;

    /**
     * 使用状态;0-禁用，1-启用
     */
    @NotBlank(message = "使用标识不能为空!", groups = {Update.class})
    @Size(max = 1, message = "使用标识不能超过1!", groups = {Update.class})
    @Pattern(regexp = "^[0-1]$", message = "使用标识格式不正确!", groups = {Update.class})
    private String useStatus;

    /**
     * 删除状态;0-未删除，1-已删除
     */
    @NotBlank(message = "删除标识不能为空!", groups = {Update.class})
    @Size(max = 1, message = "删除标识不能超过1!", groups = {Update.class})
    @Pattern(regexp = "^[0-1]$", message = "删除标识格式不正确!", groups = {Update.class})
    private String delStatus;
}

