package com.ssj.yunblog.setting.controller;

import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.setting.entity.vo.BlogSettingsVo;
import com.ssj.yunblog.setting.service.BlogSettingsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 博客设置控制层
 *
 * @author yunkun
 * @since 2025-09-07 11:57:28
 */
@RestController
@RequestMapping("/blog/setting")
public class BlogSettingsController {

    @Resource
    private BlogSettingsService blogSettingsService;

    /**
     * 查询博客设置详情
     */
    @PostMapping("/detail")
    public Result<BlogSettingsVo> queryBlogSettingDetail() {
        return blogSettingsService.queryBlogSettingDetail();
    }

}

