package com.ssj.yunblog.search.handler;

import com.ssj.yunblog.search.enums.SearchHandlerEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 搜索策略工厂类
 *
 * @author: yunkun
 * @Date: 2025/10/2
 */
@Component
public class SearchHandlerFactory implements InitializingBean {

    @Resource
    public List<SearchHandler> handlers = new ArrayList<>();

    public static final Map<SearchHandlerEnum, SearchHandler> SEARCH_HANDLERS = new HashMap<>();

    /**
     * 前置初始化
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        for (SearchHandler handler : handlers) {
            SEARCH_HANDLERS.put(handler.getSearchHandler(), handler);
        }
    }

    /**
     * 通过策略名称获取执行器，默认返回mysql搜索执行器
     */
    public SearchHandler getSearchHandlerByName(String handlerName) {
        SearchHandlerEnum handler = SearchHandlerEnum.getHandlerByName(handlerName);
        return getSearchHandler(Objects.requireNonNullElse(handler, SearchHandlerEnum.MYSQL_SEARCH));
    }

    /**
     * 通过策略编号获取执行器，默认返回mysql搜索执行器
     */
    public SearchHandler getSearchHandlerByCode(String handlerCode) {
        SearchHandlerEnum handler = SearchHandlerEnum.getHandlerByCode(handlerCode);
        return getSearchHandler(Objects.requireNonNullElse(handler, SearchHandlerEnum.MYSQL_SEARCH));
    }

    private SearchHandler getSearchHandler(SearchHandlerEnum handler) {
        return SEARCH_HANDLERS.get(handler);
    }
}
