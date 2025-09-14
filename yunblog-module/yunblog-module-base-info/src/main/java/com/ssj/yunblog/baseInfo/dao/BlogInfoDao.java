package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.BlogInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * (BlogInfo)表数据库访问层
 *
 * @author yunkun
 * @since 2025-09-13 17:08:17
 */
@Mapper
public interface BlogInfoDao extends BaseMapper<BlogInfo> {
}

