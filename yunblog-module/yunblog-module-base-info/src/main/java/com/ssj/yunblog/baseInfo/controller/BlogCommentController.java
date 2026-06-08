package com.ssj.yunblog.baseInfo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCommentBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCommentQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogCommentVo;
import com.ssj.yunblog.baseInfo.service.BlogCommentService;
import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.api.Update;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 评论控制层
 *
 * @author yunkun
 * @time: 2026-05-30
 */
@RestController
@RequestMapping("/blog/comment")
public class BlogCommentController {

    @Resource
    private BlogCommentService blogCommentService;

    /**
     * 分页查询评论
     */
    @GetMapping("/page")
    public Result<IPage<BlogCommentVo>> queryPageList(BlogCommentQueryBo param) {
        return blogCommentService.queryPageList(param);
    }

    /**
     * 获取评论详情
     */
    @GetMapping("/{id}")
    public Result<BlogCommentVo> getDetail(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("评论ID不能为空");
        }
        return blogCommentService.getDetail(id);
    }

    /**
     * 新增评论
     */
    @PostMapping
    public Result<Boolean> add(@RequestBody @Validated(Add.class) BlogCommentBo comment) {
        return blogCommentService.add(comment);
    }

    /**
     * 编辑评论
     */
    @PutMapping
    public Result<Boolean> edit(@RequestBody @Validated(Update.class) BlogCommentBo comment) {
        return blogCommentService.edit(comment);
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("评论ID不能为空");
        }
        return blogCommentService.delete(id);
    }
}