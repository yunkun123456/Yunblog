package com.ssj.yunblog.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.admin.entity.UserInfo;
import com.ssj.yunblog.admin.entity.bo.UserInfoBo;
import com.ssj.yunblog.common.entity.Result;

/**
 * (TUserInfo)表服务接口
 *
 * @author yunkun
 * @since 2025-08-23 15:11:19
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 用户注册
     */
    Result<Boolean> register(UserInfoBo userInfoBo);

    /**
     * 更新用户信息
     */
    Result<Boolean> updateUserInfo(UserInfoBo userInfoBo);

    /**
     * 用户登录
     * @return
     */
    Result<Boolean> login(String account, String password);
}
