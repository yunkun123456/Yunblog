package com.ssj.yunblog.common.constant;

/**
 * redis key 常量
 *
 * @author: yunkun
 * @Date: 2025/8/23
 */
public interface RedisKey {

    /**
     * 用户角色
     */
    String ROLE_KEY = "role:";

    /**
     * 用户权限
     */
    String PERMISSION_KEY = "permission:";

    /**
     * 文章总点赞数
     */
    String BLOG_LIKES = "blog_likes";

    /**
     * 用户信息hash
     */
    String USER_INFO_KEY = "user_info_key";
}
