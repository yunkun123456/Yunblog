package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.Knowledge;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Knowledge)表数据库访问层
 *
 * @author yunkun
 * @since 2026-01-03 20:06:20
 */
@Mapper
public interface KnowledgeDao extends BaseMapper<Knowledge> {
}

