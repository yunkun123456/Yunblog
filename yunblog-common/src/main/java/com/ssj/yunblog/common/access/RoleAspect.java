package com.ssj.yunblog.common.access;

import cn.dev33.satoken.stp.StpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 角色校验切面
 */
@Aspect
@Component
public class RoleAspect {

    /**
     * 目标方法
     */
    @Pointcut("@annotation(com.ssj.yunblog.common.access.CheckRole)")
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
        /*
         * 获取注解的值，并进行权限验证
         * */
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        CheckRole checkRole = method.getAnnotation(CheckRole.class);
        String[] value = checkRole.value();
        // all游客皆可访问
        if ("all".equals(value[0])) {
            return proceedingJoinPoint.proceed();
        }
        // 校验权限
        StpUtil.checkRoleOr(value);

        // 放行
        return proceedingJoinPoint.proceed();

    }

}
