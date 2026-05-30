package com.ssj.yunblog.baseInfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.baseInfo.entity.BlogComment;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCommentBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCommentQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogCommentVo;
import com.ssj.yunblog.common.entity.Result;

/**
 * 评论服务接口
 *
 * @author yunkun
 * @time: 2026-05-30
 */
public interface BlogCommentService extends IService<BlogComment> {

    /**
     * 新增评论
     */
    Result<Boolean> add(BlogCommentBo comment);

    /**
     * 编辑评论
     */
    Result<Boolean> edit(BlogCommentBo comment);

    /**
     * 删除评论
     */
    Result<Boolean> delete(String id);

    /**
     * 分页查询评论
     */
    Result<IPage<BlogCommentVo>> queryPageList(BlogCommentQueryBo param);

    /**
     * 获取评论详情
     */
    Result<BlogCommentVo> getDetail(String id);
}