package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.BlogLabel;
import org.apache.ibatis.annotations.Mapper;

/**
 * (BlogLabel)表数据库访问层
 *
 * @author yunkun
 * @since 2025-09-13 18:09:44
 */
@Mapper
public interface BlogLabelDao extends BaseMapper<BlogLabel> {
}

