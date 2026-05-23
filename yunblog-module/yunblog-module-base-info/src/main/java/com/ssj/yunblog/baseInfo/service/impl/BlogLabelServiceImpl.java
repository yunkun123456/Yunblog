package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.entity.BlogLabel;
import com.ssj.yunblog.baseInfo.entity.BlogLabel;
import com.ssj.yunblog.baseInfo.dao.BlogLabelDao;
import com.ssj.yunblog.baseInfo.entity.bo.BlogLabelBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogLabelQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogLabelVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogLabelVo;
import com.ssj.yunblog.baseInfo.service.BlogLabelService;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * (BlogLabel)表服务实现类
 *
 * @author yunkun
 * @since 2025-09-13 18:09:44
 */
@Service("blogLabelService")
public class BlogLabelServiceImpl extends ServiceImpl<BlogLabelDao, BlogLabel> implements BlogLabelService {

    @Resource
    private BlogLabelDao blogLabelDao;


    /**
     * 新增博客标签信息
     */
    @Override
    public Result<Boolean> add(BlogLabelBo blogLabel) {
        BlogLabel label = new BlogLabel();
        BeanUtils.copyProperties(blogLabel, label);
        label.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        if (blogLabelDao.insert(label) > 0) {
            return Result.ok();
        }
        return Result.fail("新增博客标签信息失败");
    }

    /**
     * 删除博客标签信息
     */
    @Override
    public Result<Boolean> delete(String id) {
        BlogLabel label = new BlogLabel();
        label.setId(id);
        label.setDelStatus(DeleteStatusEnum.DELETED.getCode());
        if (blogLabelDao.updateById(label) > 0) {
            return Result.ok();
        }
        return Result.fail("删除博客标签信息失败！");
    }

    /**
     * 查询所有的标签信息
     */
    @Override
    public Result<List<BlogLabelVo>> queryAllLabels() {
        LambdaQueryWrapper<BlogLabel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogLabel::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        List<BlogLabel> blogLabels = blogLabelDao.selectList(queryWrapper);
        List<BlogLabelVo> result = new ArrayList<>();
        for (BlogLabel label : blogLabels) {
            BlogLabelVo vo = new BlogLabelVo();
            BeanUtils.copyProperties(label, vo);
            result.add(vo);
        }
        return Result.ok(result);
    }

    /**
     * 根据分类id查询标签信息
     */
    @Override
    public Result<List<BlogLabelVo>> queryLabelListByCategoryId(String categoryId) {
        LambdaQueryWrapper<BlogLabel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogLabel::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .eq(categoryId != null && !categoryId.isEmpty(), BlogLabel::getCategoryId, categoryId);
        List<BlogLabel> blogLabels = blogLabelDao.selectList(queryWrapper);
        List<BlogLabelVo> result = new ArrayList<>();
        for (BlogLabel label : blogLabels) {
            BlogLabelVo vo = new BlogLabelVo();
            BeanUtils.copyProperties(label, vo);
            result.add(vo);
        }
        return Result.ok(result);
    }

    /**
     * 分页查询标签信息
     */
    @Override
    public Result<IPage<BlogLabelVo>> queryPageList(BlogLabelQueryBo param) {
        LambdaQueryWrapper<BlogLabel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogLabel::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .like(param.getKeyword() != null && !param.getKeyword().isEmpty(), BlogLabel::getLabelName, param.getKeyword())
                .between(!StringUtils.isEmpty(param.getStartTime()) && !StringUtils.isEmpty(param.getEndTime()),
                        BlogLabel::getCreateTime, param.getStartTime(), param.getEndTime())
                .and(param.getPrimaryCategoryId() != null || param.getSecondCategoryId() != null, wrapper -> wrapper.
                        eq(param.getPrimaryCategoryId() != null && !param.getPrimaryCategoryId().isEmpty(),
                                BlogLabel::getCategoryId, param.getPrimaryCategoryId())
                        .or()
                        .eq(param.getSecondCategoryId() != null && !param.getSecondCategoryId().isEmpty(),
                                BlogLabel::getCategoryId, param.getSecondCategoryId()));
        Page<BlogLabel> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<BlogLabel> labelPage = blogLabelDao.selectPage(page, queryWrapper);
        Page<BlogLabelVo> result = new Page<>();
        List<BlogLabelVo> list = labelPage.getRecords().stream().map((item) -> {
            BlogLabelVo vo = new BlogLabelVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCreateTime(item.getCreateTime().toString());
            return vo;
        }).toList();
        result.setRecords(list);
        result.setTotal(labelPage.getTotal());
        return Result.ok(result);
    }
}
