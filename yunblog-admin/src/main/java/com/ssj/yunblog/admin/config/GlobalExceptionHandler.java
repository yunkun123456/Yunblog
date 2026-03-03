package com.ssj.yunblog.admin.config;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.util.SaResult;
import com.ssj.yunblog.common.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handlerException(NotRoleException e) {
        return Result.fail("用户无权限!");
    }

    @ExceptionHandler
    public Result handlerNotPermisssonException(NotPermissionException e) {
        return Result.fail("用户无权限!");
    }
}

