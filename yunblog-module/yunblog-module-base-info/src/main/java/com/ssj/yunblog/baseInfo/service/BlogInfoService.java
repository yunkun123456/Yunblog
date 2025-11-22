package com.ssj.yunblog.baseInfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.baseInfo.entity.BlogInfo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogInfoBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogInfoQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogInfoDetailVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogInfoVo;
import com.ssj.yunblog.common.entity.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (BlogInfo)表服务接口
 *
 * @author yunkun
 * @since 2025-09-13 17:08:19
 */
public interface BlogInfoService extends IService<BlogInfo> {

    /**
     * 新增博客信息
     */
    Result<Boolean> add(BlogInfoBo blogInfo);

    /**
     * 编辑博客信息
     */
    Result<Boolean> edit(BlogInfoBo blogInfo);

    /**
     * 发布和取消发布博客
     */
    Result<Boolean> publish(String id);

    /**
     * 分页查询博客基础信息
     */
    Result<IPage<BlogInfoVo>> queryPageList(BlogInfoQueryBo param);

    /**
     * 查询博客详情
     */
    Result<BlogInfoDetailVo> queryDetail(String id);

    /**
     * 获取每日推荐 最新博客
     */
    Result<BlogInfoVo> getDailyRecommendNew();

    /**
     * 获取每日推荐 最热博客
     */
    Result<BlogInfoVo> getDailyRecommendHot();
}
