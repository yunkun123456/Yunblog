package com.ssj.yunblog.baseInfo.controller;

import com.ssj.yunblog.baseInfo.service.BlogRecommendService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * (BlogRecommend)表控制层
 *
 * @author yunkun
 * @since 2025-11-22 16:36:13
 */
@RestController
@RequestMapping("/blog/recommend")
public class BlogRecommendController {
    /**
     * 服务对象
     */
    @Resource
    private BlogRecommendService blogRecommendService;

}

