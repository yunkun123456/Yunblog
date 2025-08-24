package com.ssj.yunblog.admin.entity.bo;

import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.api.Update;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 权限实体 Bo
 *
 * @author yunkun
 * @since 2025-08-23 18:21:26
 */
@Data
public class PermissionBo {
    /**
     * 主键
     */
    @NotBlank(message = "主键不能为空!", groups = {Update.class})
    @Size(max = 32, message = "主键长度不能超过32!", groups = {Update.class})
    private String id;

    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空!", groups = {Update.class, Add.class})
    @Size(max = 20, message = "权限名称不能超过20!", groups = {Update.class, Add.class})
    private String permissionName;

    /**
     * 权限编码
     */
    @NotBlank(message = "权限标识不能为空!", groups = {Update.class, Add.class})
    @Size(max = 20, message = "权限标识不能超过20!", groups = {Update.class, Add.class})
    private String permissionCode;

    /**
     * 删除标识，0未删除，1已删除
     */
    @NotBlank(message = "删除标识不能为空!", groups = {Update.class})
    @Size(max = 1, message = "删除标识不能超过1!", groups = {Update.class})
    @Pattern(regexp = "^[0-1]$", message = "删除标识格式不正确!", groups = {Update.class})
    private String delStatus;

}

