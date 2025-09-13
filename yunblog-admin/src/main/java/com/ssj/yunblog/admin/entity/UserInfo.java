package com.ssj.yunblog.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 实体类
 *
 * @author yunkun
 * @since 2025-08-23 15:11:19
 */
@TableName("t_user_info")
@Data
public class UserInfo {
    /**
     * 主键
     */
    private String id;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户签名
     */
    private String signature;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 角色标识
     */
    private String roleCode;
    /**
     * 删除标识，0未删除，1已删除
     */
    private String delStatus;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private String updateTime;

}

