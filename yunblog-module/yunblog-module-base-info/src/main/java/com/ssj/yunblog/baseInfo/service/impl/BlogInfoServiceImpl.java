package com.ssj.yunblog.baseInfo.service.impl;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.dao.*;
import com.ssj.yunblog.baseInfo.entity.*;
import com.ssj.yunblog.baseInfo.entity.bo.BlogInfoBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogInfoQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogCategoryVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogInfoDetailVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogInfoVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogLabelVo;
import com.ssj.yunblog.baseInfo.service.BlogInfoService;
import com.ssj.yunblog.baseInfo.service.BlogRecommendService;
import com.ssj.yunblog.common.constant.RedisKey;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import com.ssj.yunblog.common.enums.RecommendStatusEnum;
import com.ssj.yunblog.common.enums.RecommendWeightEnum;
import com.ssj.yunblog.common.utils.RecommendationCalculator;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * (BlogInfo)表服务实现类
 *
 * @author yunkun
 * @since 2025-09-13 17:08:20
 */
@Service("blogInfoService")
public class BlogInfoServiceImpl extends ServiceImpl<BlogInfoDao, BlogInfo> implements BlogInfoService {

    @Resource
    private BlogInfoDao blogInfoDao;

    @Resource
    private BlogInfoDetailDao blogInfoDetailDao;

    @Resource
    private BlogLabelDao blogLabelDao;

    @Resource
    private BlogCategoryDao blogCategoryDao;

    @Resource
    private BlogRecommendService blogRecommendService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private final static Integer RECOMMEND_QUERY_SIZE = 10;

    /**
     * 新增博客信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result<Boolean> add(BlogInfoBo blogInfo) {
        BlogInfo info = new BlogInfo();
        BeanUtils.copyProperties(blogInfo, info);
        info.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        BlogInfoDetail detail = new BlogInfoDetail();
        BeanUtils.copyProperties(blogInfo, detail);
        info.setReadNum(0);
        info.setLikeNum(0);
        String labelIds = String.join(",", blogInfo.getTags());
        info.setLabelId(labelIds);
        // 插入作者信息
        String authorName = (String) StpUtil.getSession().get("username");
        info.setAuthorName(authorName);
        blogInfoDao.insert(info);
        detail.setBlogId(info.getId());
        detail.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        blogInfoDetailDao.insert(detail);
        // 是否推荐
        if (!"0".equals(blogInfo.getRecommend())) {
            BlogRecommend recommend = new BlogRecommend();
            recommend.setRelatedId(info.getId());
            recommend.setTitle(info.getTitle());
            recommend.setCategoryId(info.getCategoryId());
            recommend.setIntroduction(info.getIntroduction());
            recommend.setType("0");
            recommend.setWeight(RecommendWeightEnum.MEDIUM.getCode());
            recommend.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
            blogRecommendService.insert(recommend);
        }
        return Result.ok();
    }

    /**
     * 编辑博客信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result<Boolean> edit(BlogInfoBo blogInfo) {
        BlogInfo info = new BlogInfo();
        BeanUtils.copyProperties(blogInfo, info);
        info.setLabelId(String.join(",", blogInfo.getTags()));
        blogInfoDao.updateById(info);
        String blogId = info.getId();
        LambdaUpdateWrapper<BlogInfoDetail> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(BlogInfoDetail::getBlogId, blogId)
                .set(!blogInfo.getContent().isEmpty(), BlogInfoDetail::getContent, blogInfo.getContent())
                .set(blogInfo.getPicUrl() != null && !blogInfo.getPicUrl().isEmpty(), BlogInfoDetail::getPicUrl, blogInfo.getPicUrl());
        blogInfoDetailDao.update(updateWrapper);
        return Result.ok();
    }

    /**
     * 发布/取消发布博客
     */
    @Override
    public Result<Boolean> publish(String id) {
        BlogInfo blogInfo = blogInfoDao.selectById(id);
        if (blogInfo == null) {
            return Result.fail("博客不存在！");
        }
        if (blogInfo.getDelStatus().equals(DeleteStatusEnum.DELETED.getCode())) {
            blogInfo.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        } else {
            blogInfo.setDelStatus(DeleteStatusEnum.DELETED.getCode());
        }
        blogInfoDao.updateById(blogInfo);
        return Result.ok();
    }

    /**
     * 分页查询博客基础信息
     */
    @Override
    public Result<IPage<BlogInfoVo>> queryPageList(BlogInfoQueryBo param) {
        LambdaQueryWrapper<BlogInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(param.getCategoryId() != null && !param.getCategoryId().isEmpty(), BlogInfo::getCategoryId, param.getCategoryId())
                .like(param.getLabelId() != null && !param.getLabelId().isEmpty(), BlogInfo::getLabelId, param.getLabelId())
                .like(param.getSearchTitle() != null && !param.getSearchTitle().isEmpty(), BlogInfo::getTitle, param.getSearchTitle())
                .eq(BlogInfo::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        if ("default".equals(param.getSort())) {
            queryWrapper.orderBy(true, param.getAsc(), BlogInfo::getCreateTime);
        } else if ("like".equals(param.getSort())) {
            queryWrapper.orderBy(true, param.getAsc(), BlogInfo::getLikeNum);
        }
        Page<BlogInfo> page = new Page<>(param.getCurrent(), param.getSize());
        IPage<BlogInfo> blogInfoPage = blogInfoDao.selectPage(page, queryWrapper);
        boolean login = StpUtil.isLogin();
        String loginId;
        if (login) {
            loginId = StpUtil.getLoginId().toString();
        } else {
            loginId = "";
        }
        List<BlogInfoVo> records = blogInfoPage.getRecords().stream().map((item) -> {
            BlogInfoVo infoVo = new BlogInfoVo();
            BeanUtils.copyProperties(item, infoVo);
            // 获取标签信息
            List<String> labelIds = Arrays.stream(item.getLabelId().split(",")).toList();
            LambdaQueryWrapper<BlogLabel> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(BlogLabel::getId, labelIds)
                    .eq(BlogLabel::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
            List<BlogLabel> blogLabels = blogLabelDao.selectList(wrapper);
            List<BlogLabelVo> blogLabelVos = blogLabels.stream().map(blogLabel -> {
                BlogLabelVo labelVo = new BlogLabelVo();
                BeanUtils.copyProperties(blogLabel, labelVo);
                return labelVo;
            }).toList();
            infoVo.setLabels(blogLabelVos);
            // 获取分类信息
            LambdaQueryWrapper<BlogCategory> categoryWrapper = new LambdaQueryWrapper<>();
            categoryWrapper.eq(BlogCategory::getId, item.getCategoryId())
                    .eq(BlogCategory::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
            BlogCategory category = blogCategoryDao.selectOne(categoryWrapper);
            BlogCategoryVo categoryVo = new BlogCategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            infoVo.setCategory(categoryVo);
            infoVo.setCreateTime(item.getCreateTime().toString().substring(0, 10));
            // 判断用户是否点赞
            if (login) {
                String key = loginId + "_" + item.getId();
                Integer status = (Integer) redisTemplate.opsForValue().get(key);
                infoVo.setLikeFlag(status != null && status == 1);
            } else {
                // 未登录 默认未点赞
                infoVo.setLikeFlag(false);
            }
            return infoVo;
        }).toList();
        Page<BlogInfoVo> result = new Page<>();
        result.setRecords(records);
        result.setTotal(blogInfoPage.getTotal());
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        return Result.ok(result);
    }

    /**
     * 查询博客详情
     */
    @Override
    public Result<BlogInfoDetailVo> queryDetail(String blogId) {
        BlogInfo info = new BlogInfo();
        BlogInfo blogInfo = blogInfoDao.selectById(blogId);
        if (blogInfo == null) {
            return Result.fail("博客不存在！");
        }
        Integer readNum = blogInfo.getReadNum();
        info.setReadNum(readNum + 1);
        info.setId(blogId);
        blogInfoDao.updateById(info);
        LambdaQueryWrapper<BlogInfoDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogInfoDetail::getBlogId, blogId)
                .eq(BlogInfoDetail::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        BlogInfoDetail detail = blogInfoDetailDao.selectOne(queryWrapper);
        BlogInfoDetailVo result = new BlogInfoDetailVo();
        BeanUtils.copyProperties(detail, result);
        result.setTitle(blogInfo.getTitle());
        result.setIntroduction(blogInfo.getIntroduction());
        result.setCoverUrl(blogInfo.getCoverUrl());
        result.setCategoryId(blogInfo.getCategoryId());
        result.setLabels(Arrays.stream(blogInfo.getLabelId().split(",")).toList());
        BlogCategory category = blogCategoryDao.selectById(blogInfo.getCategoryId());
        result.setPrimaryCategoryId(category.getParentId());
        result.setRecommend(blogInfo.getRecommend());
        return Result.ok(result);
    }

    /**
     * 获取每日推荐-最新
     */
    @Override
    public Result<BlogInfoVo> getDailyRecommendNew() {
        LambdaQueryWrapper<BlogInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogInfo::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .orderByDesc(BlogInfo::getCreateTime)
                .last("limit " + RECOMMEND_QUERY_SIZE);
        List<BlogInfo> blogInfos = blogInfoDao.selectList(queryWrapper);
        if (blogInfos.isEmpty()) {
            return Result.fail("暂无数据！");
        }
        int size = Math.min(RECOMMEND_QUERY_SIZE, blogInfos.size());
        Random random = new Random();
        int index = random.nextInt(size);
        BlogInfo blogInfo = blogInfos.get(index);
        BlogInfoVo result = new BlogInfoVo();
        BeanUtils.copyProperties(blogInfo, result);
        result.setCreateBy(blogInfo.getAuthorName());
        result.setCreateTime(blogInfo.getCreateTime().toString().substring(0, 10));
        LambdaQueryWrapper<BlogLabel> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(BlogLabel::getId, Arrays.stream(blogInfo.getLabelId().split(",")).toList())
                .eq(BlogLabel::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        List<BlogLabel> blogLabels = blogLabelDao.selectList(wrapper);
        result.setTags(blogLabels.stream().map(BlogLabel::getLabelName).toList());
        return Result.ok(result);
    }

    /**
     * 获取每日推荐-最热
     */
    @Override
    public Result<BlogInfoVo> getDailyRecommendHot() {
        LambdaQueryWrapper<BlogInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogInfo::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .orderByDesc(BlogInfo::getLikeNum)
                .orderByDesc(BlogInfo::getReadNum)
                .last("limit " + RECOMMEND_QUERY_SIZE);
        List<BlogInfo> blogInfos = blogInfoDao.selectList(queryWrapper);
        if (blogInfos.isEmpty()) {
            return Result.fail("暂无数据！");
        }
        int size = Math.min(RECOMMEND_QUERY_SIZE, blogInfos.size());
        Random random = new Random();
        int index = random.nextInt(size);
        BlogInfo blogInfo = blogInfos.get(index);
        BlogInfoVo result = new BlogInfoVo();
        BeanUtils.copyProperties(blogInfo, result);
        result.setCreateTime(blogInfo.getCreateTime().toString().substring(0, 10));
        LambdaQueryWrapper<BlogLabel> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(BlogLabel::getId, Arrays.stream(blogInfo.getLabelId().split(",")).toList())
                .eq(BlogLabel::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        List<BlogLabel> blogLabels = blogLabelDao.selectList(wrapper);
        result.setTags(blogLabels.stream().map(BlogLabel::getLabelName).toList());
        return Result.ok(result);
    }

    /**
     * 点赞
     */
    @Override
    public Result<Boolean> giveALike(String blogId, Integer status) {
        String userId = (String) StpUtil.getLoginId();
        if (userId == null) {
            return Result.fail("请登录后操作！");
        }
        String key = userId + "_" + blogId;
        Integer oldValue = (Integer) redisTemplate.opsForValue().get(key);
        redisTemplate.opsForValue().set(key, status);
        // 这边还是要校验以下数值
        if (oldValue == null) {
            // 用户第一次操作
            if (status != 1) {
                return Result.fail("请点赞后再取消点赞");
            }
            redisTemplate.opsForHash().increment(RedisKey.BLOG_LIKES, blogId, 1);
        } else {
            if (!oldValue.equals(status)) {
                int count = status == 1 ? 1 : -1;
                redisTemplate.opsForHash().increment(RedisKey.BLOG_LIKES, blogId, count);
            }
        }
        return Result.ok(true);
    }
}
