package com.ssj.yunblog.search.service;

import com.ssj.yunblog.common.entity.Result;

/**
 * 搜索服务接口
 *
 * @author: yunkun
 * @Date: 2025/10/2
 */
public interface SearchService {

    /**
     * 全文检索功能
     */
    Result<String> query(String keyword, String engine);
}
