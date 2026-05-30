package com.ssj.yunblog.baseInfo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogCommentVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogMessageVo;
import com.ssj.yunblog.baseInfo.service.BlogMessageService;
import com.ssj.yunblog.common.access.CheckRole;
import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 留言板控制层
 *
 * @author yunkun
 * @since 2026-05-24
 */
@RestController
@RequestMapping("/blog/message")
public class BlogMessageController {

    @Resource
    private BlogMessageService blogMessageService;

    /**
     * 获取留言分页列表（前端）
     */
    @GetMapping("/page")
    public Result<IPage<BlogMessageVo>> queryPageList(BlogMessageQueryBo param) {
        return blogMessageService.queryPageList(param);
    }

    /**
     * 获取留言详情
     */
    @GetMapping("/{id}")
    public Result<BlogMessageVo> getDetail(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        return blogMessageService.getDetail(id);
    }

    /**
     * 获取留言评论列表
     */
    @GetMapping("/{id}/comments")
    public Result<IPage<BlogCommentVo>> getComments(
            @PathVariable("id") String id,
            @RequestParam Integer current,
            @RequestParam Integer size) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        if (current == null || current < 1) {
            current = 1;
        }
        if (size == null || size < 1) {
            size = 10;
        }
        return blogMessageService.getComments(id, current, size);
    }

    /**
     * 发布留言
     */
    @PostMapping
    public Result<Boolean> add(@RequestBody @Validated(Add.class) BlogMessageBo blogMessage) {
        return blogMessageService.add(blogMessage);
    }

    /**
     * 点赞留言
     */
    @GetMapping("/like/{id}")
    public Result<Boolean> like(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        return blogMessageService.like(id);
    }

    /**
     * 获取留言分页列表（管理端）
     */
    @GetMapping("/admin/page")
    @CheckRole(value = {"admin"})
    public Result<IPage<BlogMessageVo>> queryPageListAdmin(BlogMessageQueryBo param) {
        return blogMessageService.queryPageListAdmin(param);
    }

    /**
     * 审核留言
     */
    @PostMapping("/audit/{id}")
    @CheckRole(value = {"admin"})
    public Result<Boolean> audit(@PathVariable("id") String id, @RequestBody Map<String, Integer> params) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        Integer status = params.get("status");
        if (status == null) {
            return Result.fail("审核状态不能为空");
        }
        return blogMessageService.audit(id, status);
    }

    /**
     * 删除留言
     */
    @DeleteMapping("/{id}")
    @CheckRole(value = {"admin"})
    public Result<Boolean> delete(@PathVariable("id") String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        return blogMessageService.delete(id);
    }
}