package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.BlogRecommend;
import org.apache.ibatis.annotations.Mapper;

/**
 * (BlogRecommend)表数据库访问层
 *
 * @author yunkun
 * @since 2025-11-22 16:36:13
 */
@Mapper
public interface BlogRecommendDao extends BaseMapper<BlogRecommend> {

}

