package com.ssj.yunblog.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.admin.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色信息  mapper
 *
 * @author yunkun
 * @since 2025-06-07 15:59:57
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {

}

