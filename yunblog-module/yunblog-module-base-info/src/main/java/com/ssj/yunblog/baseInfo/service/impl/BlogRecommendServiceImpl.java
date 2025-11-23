package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.dao.BlogCategoryDao;
import com.ssj.yunblog.baseInfo.entity.BlogCategory;
import com.ssj.yunblog.baseInfo.entity.BlogRecommend;
import com.ssj.yunblog.baseInfo.dao.BlogRecommendDao;
import com.ssj.yunblog.baseInfo.entity.vo.BlogRecommendVo;
import com.ssj.yunblog.baseInfo.service.BlogRecommendService;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import com.ssj.yunblog.common.enums.RecommendWeightEnum;
import com.ssj.yunblog.common.utils.RecommendationCalculator;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * (BlogRecommend)表服务实现类
 *
 * @author yunkun
 * @since 2025-11-22 16:36:13
 */
@Service("blogRecommendService")
public class BlogRecommendServiceImpl extends ServiceImpl<BlogRecommendDao, BlogRecommend> implements BlogRecommendService {

    @Resource
    private BlogRecommendDao blogRecommendDao;

    @Resource
    private BlogCategoryDao blogCategoryDao;

    private final static Integer RECOMMEND_QUERY_SIZE = 10;

    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 新增数据
     */
    @Override
    public Boolean insert(BlogRecommend blogRecommend) {
        return blogRecommendDao.insert(blogRecommend) > 1;
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

    /**
     * 每日推荐 - 博主推荐
     */
    @Override
    public Result<BlogRecommendVo> getDailyRecommend() {
        LambdaQueryWrapper<BlogRecommend> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogRecommend::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .orderByDesc(BlogRecommend::getCreateTime);
        List<BlogRecommend> blogRecommendList = blogRecommendDao.selectList(queryWrapper);
        if (blogRecommendList.isEmpty()) {
            return Result.fail("暂无数据！");
        }
        List<BlogRecommendVo> list = blogRecommendList.stream().map((item) -> {
            BlogRecommendVo recommendVo = new BlogRecommendVo();
            BeanUtils.copyProperties(item, recommendVo);
            recommendVo.setRecommendWeight(
                    BigDecimal.valueOf(
                            RecommendationCalculator.calculateRecommendation(
                                    Objects.requireNonNull(RecommendWeightEnum.getByCode(item.getWeight())),
                                    item.getCreateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                    ).setScale(2, RoundingMode.HALF_UP).doubleValue()
            );
            recommendVo.setCreateTime(item.getCreateTime().toString().substring(0, 10));
            return recommendVo;
        }).sorted(Comparator.comparingDouble(BlogRecommendVo::getRecommendWeight)).toList();
        int size = Math.min(RECOMMEND_QUERY_SIZE, list.size());
        Random random = new Random();
        int index = random.nextInt(size);
        BlogRecommendVo result = list.get(index);
        BlogCategory category = blogCategoryDao.selectById(result.getCategoryId());
        result.setCategoryName(category.getCategoryName());
        result.setCreateTime(result.getCreateTime());
        return Result.ok(result);
    }

    public long stringToTimestamp(String dateTimeStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, formatter);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
