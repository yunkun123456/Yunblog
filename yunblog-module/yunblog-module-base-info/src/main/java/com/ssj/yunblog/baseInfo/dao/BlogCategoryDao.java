package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.BlogCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * (BlogCategory)表数据库访问层
 *
 * @author yunkun
 * @since 2025-09-13 18:09:08
 */
@Mapper
public interface BlogCategoryDao extends BaseMapper<BlogCategory> {
}

