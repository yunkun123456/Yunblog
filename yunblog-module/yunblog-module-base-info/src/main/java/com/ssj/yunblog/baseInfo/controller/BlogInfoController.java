package com.ssj.yunblog.baseInfo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssj.yunblog.baseInfo.entity.bo.BlogInfoBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogInfoQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogInfoDetailVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogInfoVo;
import com.ssj.yunblog.baseInfo.service.BlogInfoService;
import com.ssj.yunblog.common.access.CheckRole;
import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.api.Update;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 博客信息控制层
 *
 * @author yunkun
 * @since 2025-09-13 17:08:15
 */
@RestController
@RequestMapping("/blog/info")
public class BlogInfoController {

    @Resource
    private BlogInfoService blogInfoService;


    /**
     * 新增博客
     */
    @PostMapping
    @CheckRole(value = {"admin"})
    public Result<Boolean> add(@RequestBody @Validated(Add.class) BlogInfoBo blogInfo) {
        return blogInfoService.add(blogInfo);
    }

    /**
     * 编辑博客
     */
    @PutMapping
    @CheckRole(value = {"admin"})
    public Result<Boolean> edit(@RequestBody @Validated(Update.class) BlogInfoBo blogInfo) {
        return blogInfoService.edit(blogInfo);
    }

    /**
     * 发布/取消发布博客
     */
    @CheckRole(value = {"admin"})
    @PutMapping("/publish/{id}")
    public Result<Boolean> publish(@PathVariable("id") String id) {
        if (id.isEmpty()) {
            return Result.fail("博客id不能为空！");
        }
        return blogInfoService.publish(id);
    }

    /**
     * 分页查询博客信息
     */
    @GetMapping("/page")
    public Result<IPage<BlogInfoVo>> queryPageList(BlogInfoQueryBo param) {
        return blogInfoService.queryPageList(param);
    }

    /**
     * 查询博客详情
     */
    @GetMapping("/detail/{id}")
    public Result<BlogInfoDetailVo> queryDetail(@PathVariable("id") String id) {
        if (id.isEmpty()) {
            return Result.fail("博客id不能为空！");
        }
        return blogInfoService.queryDetail(id);
    }

    /**
     * 每日推荐 - 最新博客
     */
    @GetMapping("/daily/recommend/new")
    public Result<BlogInfoVo> getDailyRecommendNew() {
        return blogInfoService.getDailyRecommendNew();
    }

    /**
     * 每日推荐 - 最热博客
     */
    @GetMapping("/daily/recommend/hot")
    public Result<BlogInfoVo> getDailyRecommendHot() {
        return blogInfoService.getDailyRecommendHot();
    }

    /**
     * 点赞
     */
    @CheckRole(value = {"admin", "COMMON_USER"})
    @GetMapping("/like")
    public Result<Boolean> giveALike(String blogId, Integer status) {
        if (blogId == null || blogId.isEmpty() || status == null) {
            return Result.fail("点赞失败!");
        }
        return blogInfoService.giveALike(blogId, status);
    }

}

