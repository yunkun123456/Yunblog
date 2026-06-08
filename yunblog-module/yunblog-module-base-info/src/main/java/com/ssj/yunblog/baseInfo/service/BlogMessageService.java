package com.ssj.yunblog.baseInfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.baseInfo.entity.BlogMessage;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogCommentVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogMessageVo;
import com.ssj.yunblog.common.entity.Result;

/**
 * 留言服务接口
 *
 * @author yunkun
 * @since 2026-05-24
 */
public interface BlogMessageService extends IService<BlogMessage> {

    /**
     * 新增留言
     */
    Result<Boolean> add(BlogMessageBo blogMessage);

    /**
     * 点赞留言
     */
    Result<Boolean> like(String id);

    /**
     * 审核留言
     */
    Result<Boolean> audit(String id, Integer status);

    /**
     * 删除留言
     */
    Result<Boolean> delete(String id);

    /**
     * 分页查询留言（前端）
     */
    Result<IPage<BlogMessageVo>> queryPageList(BlogMessageQueryBo param);

    /**
     * 分页查询留言（管理端）
     */
    Result<IPage<BlogMessageVo>> queryPageListAdmin(BlogMessageQueryBo param);

    /**
     * 获取留言详情
     */
    Result<BlogMessageVo> getDetail(String id);

    /**
     * 获取留言评论列表
     */
    Result<IPage<BlogCommentVo>> getComments(String id, Integer current, Integer size);
}