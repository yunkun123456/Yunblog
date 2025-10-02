package com.ssj.yunblog.search.entity;

/**
 * 搜索请求参数
 *
 * @author: yunkun
 * @Date: 2025/10/2
 */
public class SearchRequest {

    /**
     * 搜索关键字
     */
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
