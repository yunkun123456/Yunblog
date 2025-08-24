package com.ssj.yunblog.admin.entity.bo;

import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.api.Update;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 用户信息 bo
 *
 * @author yunkun
 * @since 2025-08-23 15:11:19
 */
@Data
public class UserInfoBo {
    /**
     * 主键
     */
    @Size(max = 32, message = "主键长度不能超过32!", groups = {Update.class})
    @NotBlank(message = "主键不能为空!", groups = {Update.class})
    private String id;

    /**
     * 用户账号
     */
    @NotBlank(message = "账号不能为空!", groups = {Add.class, Update.class})
    @Size(max = 50, message = "账号长度不能超过50", groups = {Add.class, Update.class})
    private String userAccount;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空!", groups = {Add.class, Update.class})
    @Size(max = 100, message = "密码不能长度不能超出100!", groups = {Add.class, Update.class})
    private String password;

    /**
     * 用户昵称
     */
    @NotBlank(message = "昵称不能为空!", groups = {Add.class, Update.class})
    @Size(max = 50, message = "昵称长度不能超过50!", groups = {Add.class, Update.class})
    private String nickName;

    /**
     * 用户签名
     */
    @NotBlank(message = "签名不能为空!", groups = {Update.class})
    @Size(max = 200, message = "签名长度不能超过00!", groups = {Update.class})
    private String signature;

    /**
     * 邮箱地址
     */
    @NotBlank(message = "邮箱地址不能为空!", groups = {Update.class})
    @Size(max = 30, message = "邮箱地址不能超过30!", groups = {Update.class})
    private String email;

    /**
     * 角色标识
     */
    @NotBlank(message = "角色标识不能为空!", groups = {Update.class})
    @Size(max = 20, message = "角色标识不能超过20!", groups = {Update.class})
    private String roleCode;

    /**
     * 删除标识，0未删除，1已删除
     */
    @NotNull(message = "删除标识不能为空!", groups = {Update.class})
    @Size(max = 1, message = "删除标识不能超过1!", groups = {Update.class})
    @Pattern(regexp = "^[0-1]$", message = "删除标识格式不正确!", groups = {Update.class})
    private Integer delStatus;

}

