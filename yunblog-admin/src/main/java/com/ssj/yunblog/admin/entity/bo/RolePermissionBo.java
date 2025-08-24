package com.ssj.yunblog.admin.entity.bo;

import com.ssj.yunblog.common.api.Add;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 角色权限关联信息 - bo
 *
 * @author yunkun
 * @since 2025-06-07 15:59:57
 */
@Data
public class RolePermissionBo {

    /**
     * 角色id
     */
    @NotBlank(message = "角色id不能为空!", groups = {Add.class})
    private String roleId;

    /**
     * 权限ids
     */
    @NotNull(message = "权限ids不能为空!", groups = {Add.class})
    private List<String> permissionIds;
}

