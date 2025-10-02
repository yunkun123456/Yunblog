package com.ssj.yunblog.search.controller;

import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.search.service.SearchService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 搜索控制层
 *
 * @author: yunkun
 * @Date: 2025/10/2
 */
@RequestMapping("/search")
@RestController
public class SearchController {

    @Resource
    private SearchService searchService;

    /**
     * 测试搜索功能
     */
    @GetMapping("/test")
    public Result<String> test() {
        return searchService.query("关键词", "es");
    }
}
