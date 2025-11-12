package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.dao.BlogCategoryDao;
import com.ssj.yunblog.baseInfo.dao.BlogInfoDetailDao;
import com.ssj.yunblog.baseInfo.dao.BlogLabelDao;
import com.ssj.yunblog.baseInfo.entity.BlogCategory;
import com.ssj.yunblog.baseInfo.entity.BlogInfo;
import com.ssj.yunblog.baseInfo.dao.BlogInfoDao;
import com.ssj.yunblog.baseInfo.entity.BlogInfoDetail;
import com.ssj.yunblog.baseInfo.entity.BlogLabel;
import com.ssj.yunblog.baseInfo.entity.bo.BlogInfoBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogInfoQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogCategoryVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogInfoDetailVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogInfoVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogLabelVo;
import com.ssj.yunblog.baseInfo.service.BlogInfoService;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
        blogInfoDao.insert(info);
        detail.setBlogId(info.getId());
        detail.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        blogInfoDetailDao.insert(detail);
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
        blogInfoDao.updateById(info);
        String blogId = info.getId();
        LambdaUpdateWrapper<BlogInfoDetail> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(BlogInfoDetail::getBlogId, blogId)
                .set(!blogInfo.getContent().isEmpty(), BlogInfoDetail::getContent, blogInfo.getContent())
                .set(!blogInfo.getPicUrl().isEmpty(), BlogInfoDetail::getPicUrl, blogInfo.getPicUrl());
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
                .eq(param.getLabelId() != null && !param.getLabelId().isEmpty(), BlogInfo::getLabelId, param.getLabelId())
                .like(param.getSearchTitle() != null && !param.getSearchTitle().isEmpty(), BlogInfo::getTitle, param.getSearchTitle())
                .eq(BlogInfo::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        Page<BlogInfo> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<BlogInfo> blogInfoPage = blogInfoDao.selectPage(page, queryWrapper);
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
            return infoVo;
        }).toList();
        Page<BlogInfoVo> result = new Page<>();
        result.setRecords(records);
        result.setTotal(blogInfoPage.getTotal());
        return Result.ok(result);
    }

    /**
     * 查询博客详情
     */
    @Override
    public Result<BlogInfoDetailVo> queryDetail(String blogId) {
        LambdaQueryWrapper<BlogInfoDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogInfoDetail::getBlogId, blogId)
                .eq(BlogInfoDetail::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        BlogInfoDetail detail = blogInfoDetailDao.selectOne(queryWrapper);
        BlogInfoDetailVo result = new BlogInfoDetailVo();
        BeanUtils.copyProperties(detail, result);
        return Result.ok(result);
    }
}
