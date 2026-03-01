package com.ssj.yunblog.baseInfo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssj.yunblog.baseInfo.entity.bo.BlogLabelBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogLabelQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogLabelVo;
import com.ssj.yunblog.baseInfo.service.BlogLabelService;
import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 博客标签控制层
 *
 * @author yunkun
 * @since 2025-09-13 18:09:44
 */
@RestController
@RequestMapping("/blog/label")
public class BlogLabelController {

    @Resource
    private BlogLabelService blogLabelService;

    /**
     * 分页查询标签信息
     */
    @GetMapping("/page")
    public Result<IPage<BlogLabelVo>> queryPageList(BlogLabelQueryBo param) {
        return blogLabelService.queryPageList(param);
    }

    /**
     * 新增博客标签信息
     */
    @PostMapping
    public Result<Boolean> add(@RequestBody @Validated(Add.class) BlogLabelBo blogLabel) {
        return blogLabelService.add(blogLabel);
    }

    /**
     * 删除博客标签信息
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable("id") String id) {
        return blogLabelService.delete(id);
    }

    /**
     * 获取所有标签信息
     */
    @GetMapping("/all")
    public Result<List<BlogLabelVo>> queryAllLabels() {
        return blogLabelService.queryAllLabels();
    }

    /**
     * 分类id查询标签信息
     */
    @GetMapping("/list/{categoryId}")
    public Result<List<BlogLabelVo>> queryLabelListByCategoryId(@PathVariable String categoryId) {
        return blogLabelService.queryLabelListByCategoryId(categoryId);
    }

}

