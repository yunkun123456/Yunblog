package com.ssj.yunblog.admin.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.util.SaResult;
import com.ssj.yunblog.common.constant.ResultCode;
import com.ssj.yunblog.common.entity.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handlerException(NotRoleException e, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return Result.fail("用户无权限!", ResultCode.FORBIDDEN);
    }

    @ExceptionHandler
    public Result handlerNotPermisssonException(NotPermissionException e, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return Result.fail("用户无权限!", ResultCode.FORBIDDEN);
    }

    /**
     * 处理未登录异常
     */
    @ExceptionHandler
    public Result handlerNotLoginException(NotLoginException e, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return Result.fail("请先登录!", ResultCode.UNAUTHORIZED);
    }
}

