package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.KnowledgeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (KnowledgeInfo)表数据库访问层
 *
 * @author yunkun
 * @since 2026-01-03 20:18:34
 */
@Mapper
public interface KnowledgeInfoDao extends BaseMapper<KnowledgeInfo> {
}

