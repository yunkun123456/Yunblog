package com.ssj.yunblog.search.service.impl;

import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.search.entity.SearchRequest;
import com.ssj.yunblog.search.entity.SearchResult;
import com.ssj.yunblog.search.handler.SearchHandler;
import com.ssj.yunblog.search.handler.SearchHandlerFactory;
import com.ssj.yunblog.search.service.SearchService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 搜索服务接口
 *
 * @author: yunkun
 * @Date: 2025/10/2
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private SearchHandlerFactory searchHandlerFactory;

    /**
     * 全文检索实现
     */
    @Override
    public Result<String> query(String keyword, String engine) {
        SearchHandler handler = searchHandlerFactory.getSearchHandlerByName(engine);
        SearchRequest request = new SearchRequest();
        request.setKeyword(keyword);
        return handler.search(request);
    }
}
