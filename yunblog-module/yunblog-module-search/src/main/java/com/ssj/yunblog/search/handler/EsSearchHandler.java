package com.ssj.yunblog.search.handler;

import com.ssj.yunblog.search.entity.SearchRequest;
import com.ssj.yunblog.search.entity.SearchResult;
import com.ssj.yunblog.search.enums.SearchHandlerEnum;
import org.springframework.stereotype.Component;

/**
 * Elasticsearch 搜索执行器
 *
 * @author: yunkun
 * @Date: 2025/10/2
 */
@Component
public class EsSearchHandler implements SearchHandler {
    @Override
    public SearchHandlerEnum getSearchHandler() {
        return SearchHandlerEnum.ES_SEARCH;
    }

    @Override
    public SearchResult search(SearchRequest request) {
        String keyword = request.getKeyword();
        SearchResult searchResult = new SearchResult();
        searchResult.setData("es test result " + keyword);
        return searchResult;
    }
}
