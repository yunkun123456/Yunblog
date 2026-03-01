package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.entity.BlogCategory;
import com.ssj.yunblog.baseInfo.dao.BlogCategoryDao;
import com.ssj.yunblog.baseInfo.entity.BlogInfo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCategoryBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCategoryQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogCategoryVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogInfoVo;
import com.ssj.yunblog.baseInfo.service.BlogCategoryService;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * (BlogCategory)表服务实现类
 *
 * @author yunkun
 * @since 2025-09-13 18:09:08
 */
@Service("blogCategoryService")
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryDao, BlogCategory> implements BlogCategoryService {

    @Resource
    private BlogCategoryDao blogCategoryDao;


    /**
     * 新增分类信息
     */
    @Override
    public Result<Boolean> add(BlogCategoryBo blogCategory) {
        BlogCategory category = new BlogCategory();
        BeanUtils.copyProperties(blogCategory, category);
        category.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        if (category.getSortNum() == null) {
            category.setSortNum(0);
        }
        if (!category.getParentId().equals("0")) {
            LambdaQueryWrapper<BlogCategory> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(BlogCategory::getId, category.getParentId());
            BlogCategory entity = blogCategoryDao.selectOne(queryWrapper);
            category.setCategoryLevel(entity.getCategoryLevel() + 1);
        }
        if (blogCategoryDao.insert(category) > 0) {
            return Result.ok();
        }
        return Result.fail("新增分类信息失败");
    }

    /**
     * 删除分类信息
     */
    @Override
    public Result<Boolean> delete(String id) {
        BlogCategory category = new BlogCategory();
        category.setId(id);
        category.setDelStatus(DeleteStatusEnum.DELETED.getCode());
        if (blogCategoryDao.updateById(category) > 0) {
            return Result.ok();
        }
        return Result.fail("删除分类信息失败");
    }

    /**
     * 查询所有分类信息
     */
    @Override
    public Result<List<BlogCategoryVo>> queryAllCategory() {
        LambdaQueryWrapper<BlogCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogCategory::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .orderBy(true, true, BlogCategory::getSortNum);
        List<BlogCategory> blogCategories = blogCategoryDao.selectList(queryWrapper);
        List<BlogCategory> topCategories = blogCategories.stream().filter((item) -> item.getParentId().equals("0")).toList();
        List<BlogCategoryVo> result = new ArrayList<>();
        for (BlogCategory category : topCategories) {
            BlogCategoryVo vo = new BlogCategoryVo();
            BeanUtils.copyProperties(category, vo);
            buildCategoryTree(blogCategories, vo);
            result.add(vo);
        }
        return Result.ok(result);
    }

    /**
     * 条件查询分类信息
     */
    @Override
    public Result<List<BlogCategoryVo>> queryCategoryList(BlogCategoryQueryBo param) {
        LambdaQueryWrapper<BlogCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogCategory::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .eq(param.getLevel() != null, BlogCategory::getCategoryLevel, param.getLevel())
                .eq(param.getParentId() != null && !param.getParentId().isEmpty(), BlogCategory::getParentId, param.getParentId())
                .orderBy(true, true, BlogCategory::getSortNum);
        List<BlogCategory> blogCategories = blogCategoryDao.selectList(queryWrapper);
        List<BlogCategoryVo> result = new ArrayList<>();
        for (BlogCategory category : blogCategories) {
            BlogCategoryVo vo = new BlogCategoryVo();
            BeanUtils.copyProperties(category, vo);
            result.add(vo);
        }
        return Result.ok(result);
    }

    /**
     * 分页查询分类信息
     */
    @Override
    public Result<IPage<BlogCategoryVo>> queryPageList(BlogCategoryQueryBo param) {
        LambdaQueryWrapper<BlogCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogCategory::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .like(param.getKeyword() != null && !param.getKeyword().isEmpty(), BlogCategory::getCategoryName, param.getKeyword())
                .between(!StringUtils.isEmpty(param.getStartTime()) && !StringUtils.isEmpty(param.getEndTime()),
                        BlogCategory::getCreateTime, param.getStartTime(), param.getEndTime());
        Page<BlogCategory> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<BlogCategory> categoryPage = blogCategoryDao.selectPage(page, queryWrapper);
        Page<BlogCategoryVo> result = new Page<>();
        List<BlogCategoryVo> list = categoryPage.getRecords().stream().map((item) -> {
            BlogCategoryVo vo = new BlogCategoryVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
        result.setRecords(list);
        result.setTotal(categoryPage.getTotal());
        return Result.ok(result);
    }

    /**
     * 构建分类树
     * 1.一次性查询所有分类数据，进行树构建(数据不多，采取该方案)
     * 2.分批次查询构建树
     */
    private void buildCategoryTree(List<BlogCategory> total, BlogCategoryVo treeItem) {
        List<BlogCategoryVo> children = new ArrayList<>();
        for (BlogCategory blogCategory : total) {
            if (blogCategory.getParentId().equals(treeItem.getId())) {
                BlogCategoryVo vo = new BlogCategoryVo();
                BeanUtils.copyProperties(blogCategory, vo);
                buildCategoryTree(total, vo);
                children.add(vo);
            }
        }
        treeItem.setChildren(children);
    }
}
