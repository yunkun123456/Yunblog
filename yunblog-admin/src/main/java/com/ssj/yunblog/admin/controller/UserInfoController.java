package com.ssj.yunblog.admin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.ssj.yunblog.admin.entity.bo.UserInfoBo;
import com.ssj.yunblog.admin.entity.vo.UserInfoVo;
import com.ssj.yunblog.admin.service.UserInfoService;
import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.constant.ResultCode;
import com.ssj.yunblog.common.api.Update;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息-控制层
 *
 * @author yunkun
 * @since 2025-08-23 15:11:19
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<Boolean> register(@Validated(Add.class) @RequestBody UserInfoBo userInfoBo) {
        return userInfoService.register(userInfoBo);
    }

    /**
     * 修改个人信息
     */
    @PutMapping
    public Result<Boolean> updateUserInfo(@Validated(Update.class) @RequestBody UserInfoBo userInfoBo) {
        return userInfoService.updateUserInfo(userInfoBo);
    }

    /**
     * 用户登录
     */
    @GetMapping("/login")
    public Result<String> login(@RequestParam("account") String account, @RequestParam("password") String password) {
        if (account.isEmpty() || password.isEmpty()) {
            return Result.fail("账号或密码不能为空！");
        }
        return userInfoService.login(account, password);
    }

    /**
     * 获取用户信息
     */
    @SaCheckLogin
    @GetMapping("/details")
    public Result<UserInfoVo> getUserInfoDetail(HttpServletResponse response) {
        Result<UserInfoVo> result = userInfoService.getUserInfoDetail();
        if (ResultCode.UNAUTHORIZED.equals(result.getCode())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else if (ResultCode.FORBIDDEN.equals(result.getCode())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return result;
    }


}

