package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.entity.BlogRecommend;
import com.ssj.yunblog.baseInfo.dao.BlogRecommendDao;
import com.ssj.yunblog.baseInfo.service.BlogRecommendService;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (BlogRecommend)表服务实现类
 *
 * @author yunkun
 * @since 2025-11-22 16:36:13
 */
@Service("blogRecommendService")
public class BlogRecommendServiceImpl extends ServiceImpl<BlogRecommendDao,BlogRecommend> implements BlogRecommendService {

    @Resource
    private BlogRecommendDao blogRecommendDao;

    /**
     * 新增数据
     */
    @Override
    public Boolean insert(BlogRecommend blogRecommend) {
        return blogRecommendDao.insert(blogRecommend)>1;
    }

    /**
     * 条件查询
     */
    @Override
    public List<BlogRecommend> queryCondition(Integer size) {
        LambdaQueryWrapper<BlogRecommend> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogRecommend::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .orderByDesc(BlogRecommend::getCreateTime)
                .last("limit " + size);
        return blogRecommendDao.selectList(queryWrapper);
    }
}
