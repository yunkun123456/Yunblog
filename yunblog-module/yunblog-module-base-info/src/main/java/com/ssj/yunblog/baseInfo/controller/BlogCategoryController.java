package com.ssj.yunblog.baseInfo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCategoryBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCategoryQueryBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogInfoQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogCategoryVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogInfoVo;
import com.ssj.yunblog.baseInfo.service.BlogCategoryService;
import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 博客分类控制层
 *
 * @author yunkun
 * @since 2025-09-13 18:09:08
 */
@RestController
@RequestMapping("/blog/category")
public class BlogCategoryController {

    @Resource
    private BlogCategoryService blogCategoryService;

    /**
     * 分页查询分类信息
     */
    @GetMapping("/page")
    public Result<IPage<BlogCategoryVo>> queryPageList(BlogCategoryQueryBo param) {
        return blogCategoryService.queryPageList(param);
    }

    /**
     * 获取所有分类信息
     */
    @GetMapping("/all")
    public Result<List<BlogCategoryVo>> queryAllCategory() {
        return blogCategoryService.queryAllCategory();
    }

    /**
     * 条件查询分类信息
     */
    @GetMapping("/list")
    public Result<List<BlogCategoryVo>> queryCategoryList(BlogCategoryQueryBo param) {
        return blogCategoryService.queryCategoryList(param);
    }

    /**
     * 新增分类信息
     */
    @PostMapping
    public Result<Boolean> add(@RequestBody @Validated(Add.class) BlogCategoryBo blogCategory) {
        return blogCategoryService.add(blogCategory);
    }

    /**
     * 删除分类信息
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable("id") String id) {
        if (id.isEmpty()) {
            return Result.fail("分类id不能为空！");
        }
        return blogCategoryService.delete(id);
    }

}

