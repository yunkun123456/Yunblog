package com.ssj.yunblog.common.access;

import cn.dev33.satoken.stp.StpInterface;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssj.yunblog.common.constant.RedisKey;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String key = RedisKey.PERMISSION_KEY + loginId;
        String json = stringRedisTemplate.opsForValue().get(key);
        if (json == null) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            throw new RuntimeException("解析权限数据失败", e);
        }
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        String key = RedisKey.ROLE_KEY + loginId;
        String json = stringRedisTemplate.opsForValue().get(key);
        if (json == null) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (IOException e) {
            throw new RuntimeException("解析权限数据失败", e);
        }
    }

}

