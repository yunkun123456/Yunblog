package com.ssj.yunblog.admin.config;

import cn.dev33.satoken.exception.SaTokenContextException;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 元数据插入
 *
 * @author: yunkun
 * @Date: 2025/11/23
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";
    private static final String CREATE_BY = "createBy";
    private static final String UPDATE_BY = "updateBy";

    @Override
    public void insertFill(MetaObject metaObject) {
        // 检查字段是否存在，避免报错
        if (metaObject.hasSetter(CREATE_TIME)) {
            this.strictInsertFill(metaObject, CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        }
        if (metaObject.hasSetter(UPDATE_TIME)) {
            this.strictInsertFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        }
        if (metaObject.hasSetter(CREATE_BY)) {
            this.strictInsertFill(metaObject, CREATE_BY, String.class, getCurrentLoginId());
        }
        if (metaObject.hasSetter(UPDATE_BY)) {
            this.strictInsertFill(metaObject, UPDATE_BY, String.class, getCurrentLoginId());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter(UPDATE_TIME)) {
            this.strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        }
        if (metaObject.hasSetter(UPDATE_BY)) {
            this.strictUpdateFill(metaObject, UPDATE_BY, String.class, getCurrentLoginId());
        }
    }

    /**
     * 获取当前用户登录id
     */
    private String getCurrentLoginId() {
        try {
            Object loginId = StpUtil.getLoginId();
            if(loginId != null){
                return loginId.toString();
            }
        }catch (SaTokenContextException se){
            // TODO 目前没想到其他好方法
            return "1959249411166965761"; // 默认值
        }
        return "1959249411166965761"; // 默认值
    }
}
