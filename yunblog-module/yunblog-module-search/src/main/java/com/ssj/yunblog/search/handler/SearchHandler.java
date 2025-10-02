package com.ssj.yunblog.search.handler;

import com.ssj.yunblog.search.entity.SearchRequest;
import com.ssj.yunblog.search.entity.SearchResult;
import com.ssj.yunblog.search.enums.SearchHandlerEnum;

/**
 * 搜索策略接口
 *
 * @author: yunkun
 * @Date: 2025/10/2
 */
public interface SearchHandler {

    /**
     * 获取当前搜索策略
     */
    SearchHandlerEnum getSearchHandler();

    /**
     * 搜索
     */
    SearchResult search(SearchRequest request);
}
