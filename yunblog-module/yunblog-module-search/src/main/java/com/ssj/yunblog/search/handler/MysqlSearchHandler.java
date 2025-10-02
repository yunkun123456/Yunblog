package com.ssj.yunblog.search.handler;

import com.ssj.yunblog.search.entity.SearchRequest;
import com.ssj.yunblog.search.entity.SearchResult;
import com.ssj.yunblog.search.enums.SearchHandlerEnum;
import org.springframework.stereotype.Component;

/**
 * mysql 搜索执行器
 *
 * @author: yunkun
 * @Date: 2025/10/2
 */
@Component
public class MysqlSearchHandler implements SearchHandler {
    @Override
    public SearchHandlerEnum getSearchHandler() {
        return SearchHandlerEnum.MYSQL_SEARCH;
    }

    @Override
    public SearchResult search(SearchRequest request) {
        return null;
    }
}
