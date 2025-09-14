package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.BlogInfoDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * (BlogInfoDetail)表数据库访问层
 *
 * @author yunkun
 * @since 2025-09-13 17:08:57
 */
@Mapper
public interface BlogInfoDetailDao extends BaseMapper<BlogInfoDetail> {
}

