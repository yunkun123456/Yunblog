package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.BlogMessageDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * 留言内容表数据库访问层
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Mapper
public interface BlogMessageDetailDao extends BaseMapper<BlogMessageDetail> {
}