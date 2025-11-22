package com.ssj.yunblog.baseInfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.baseInfo.entity.BlogRecommend;

import java.util.List;

/**
 * (BlogRecommend)表服务接口
 *
 * @author yunkun
 * @since 2025-11-22 16:36:13
 */
public interface BlogRecommendService extends IService<BlogRecommend> {

    /**
     * 新增博主推荐内容
     */
    Boolean insert(BlogRecommend entity);

    /**
     * 条件查询博主推荐内容列表
     */
    List<BlogRecommend> queryCondition(Integer size);
}
