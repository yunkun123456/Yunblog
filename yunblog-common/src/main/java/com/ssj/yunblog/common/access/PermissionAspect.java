package com.ssj.yunblog.common.access;

import cn.dev33.satoken.stp.StpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 权限校验切面
 */
@Aspect
@Component
public class PermissionAspect {

    /**
     * 目标方法
     */
    @Pointcut("@annotation(com.ssj.yunblog.common.access.CheckPermission)")
    private void permission() {

    }

    /**
     * 目标方法调用之前执行
     */
    @Before("permission()")
    public void doBefore() {
    }

    /**
     * 目标方法调用之后执行
     */
    @After("permission()")
    public void doAfter() {
    }


    /**
     * 环绕
     * 会将目标方法封装起来
     * 具体验证业务数据
     */
    @Around("permission()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("================== step 1: around ==================");
        long startTime = System.currentTimeMillis();
        /*
         * 获取注解的值，并进行权限验证
         * */
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        CheckPermission checkPermission = method.getAnnotation(CheckPermission.class);
        String[] value = checkPermission.value();
        // 校验权限
        StpUtil.checkPermissionOr(value);

        // 放行
        return proceedingJoinPoint.proceed();

    }

}
