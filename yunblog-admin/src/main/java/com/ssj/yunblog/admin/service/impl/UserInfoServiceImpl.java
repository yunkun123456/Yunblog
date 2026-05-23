package com.ssj.yunblog.admin.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.admin.dao.PermissionDao;
import com.ssj.yunblog.admin.entity.Permission;
import com.ssj.yunblog.admin.entity.Role;
import com.ssj.yunblog.admin.entity.UserInfo;
import com.ssj.yunblog.admin.dao.UserInfoDao;
import com.ssj.yunblog.admin.entity.bo.UserInfoBo;
import com.ssj.yunblog.admin.entity.vo.UserInfoVo;
import com.ssj.yunblog.admin.service.PermissionService;
import com.ssj.yunblog.admin.service.RoleService;
import com.ssj.yunblog.admin.service.UserInfoService;
import com.ssj.yunblog.common.constant.RedisKey;
import com.ssj.yunblog.common.constant.ResultCode;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息管理 - service 层
 *
 * @author yunkun
 * @since 2025-08-23 15:11:19
 */
@Service("tUserInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RoleService roleService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 用户注册信息
     */
    @Override
    public Result<Boolean> register(UserInfoBo userInfoBo) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getUserAccount, userInfoBo.getUserAccount());
        List<UserInfo> list = userInfoDao.selectList(queryWrapper);
        if (!list.isEmpty()) {
            return Result.fail("用户已存在！");
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoBo, userInfo);
        // 分配基础角色
        userInfo.setRoleCode("COMMON_USER");
        userInfo.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        // 存储用户信息到MySQL
        userInfo.setPassword(SaSecureUtil.md5(userInfo.getPassword()));
        if (userInfoDao.insert(userInfo) <= 0) {
            return Result.fail("用户注册失败！");
        }
        return Result.ok(true, "用户注册成功！");
    }

    /**
     * 更新用户信息
     */
    @Override
    public Result<Boolean> updateUserInfo(UserInfoBo userInfoBo) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoBo, userInfo);
        if (userInfo.getPassword() != null && !userInfo.getPassword().isEmpty()) {
            userInfo.setPassword(SaSecureUtil.md5(userInfo.getPassword()));
        }
        if (userInfoDao.updateById(userInfo) <= 0) {
            return Result.fail("更新个人信息失败！");
        }
        return Result.ok(true, "更新个人信息成功！");
    }

    /**
     * 用户登录
     */
    @Override
    public Result<String> login(String account, String password) {
//        if (StpUtil.isLogin()) {
//            return Result.fail("请勿重复登录！");
//        }
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getUserAccount, account)
                .eq(UserInfo::getPassword, SaSecureUtil.md5(password))
                .eq(UserInfo::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        queryWrapper.or(query -> query.eq(UserInfo::getEmail, account)
                .eq(UserInfo::getPassword, SaSecureUtil.md5(password))
                .eq(UserInfo::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode()));
        List<UserInfo> list = userInfoDao.selectList(queryWrapper);
        if (list.isEmpty()) {
            return Result.fail("用户不存在！");
        }
        // 创建会话
        StpUtil.login(list.getFirst().getId());
        // 存储用户名称
        StpUtil.getSession().set("username", list.getFirst().getNickName());
        // 存在将用户角色和权限保存到Redis中
        String[] roles = list.getFirst().getRoleCode().split(",");
        redisTemplate.opsForValue().set(RedisKey.ROLE_KEY + StpUtil.getLoginId(), roles);
        // 权限信息保存
        Role role = roleService.queryByCode(list.getFirst().getRoleCode());
        List<Permission> permissions = permissionService.queryPermissionsByRoleId(role.getId());
        List<String> permissionCodes = permissions.stream().map(Permission::getPermissionCode).toList();
        redisTemplate.opsForValue().set(RedisKey.PERMISSION_KEY + StpUtil.getLoginId(), permissionCodes);
        String tokenValue = StpUtil.getTokenInfo().getTokenValue();
        return Result.ok(tokenValue, "登录成功！");
    }

    /**
     * 获取用户信息详情
     */
    @Override
    public Result<UserInfoVo> getUserInfoDetail() {
        String loginId = (String) StpUtil.getLoginIdDefaultNull();
        if (loginId == null) {
            return Result.fail("请先登录!", ResultCode.UNAUTHORIZED);
        }
        UserInfo userInfo = userInfoDao.selectById(loginId);
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return Result.ok(userInfoVo, "获取用户信息成功！");
    }
}
