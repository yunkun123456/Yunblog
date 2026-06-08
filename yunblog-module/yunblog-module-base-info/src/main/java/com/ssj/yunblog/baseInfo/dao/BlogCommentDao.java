package com.ssj.yunblog.baseInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssj.yunblog.baseInfo.entity.BlogComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通用评论表数据库访问层
 *
 * @author yunkun
 * @time: 2026-05-30
 */
@Mapper
public interface BlogCommentDao extends BaseMapper<BlogComment> {
}