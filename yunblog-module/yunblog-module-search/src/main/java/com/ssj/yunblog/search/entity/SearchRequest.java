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

    /**
     * 索引名称
     */
    private String index;

    /**
     * 集群名称
     */
    private String cluster;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }
}
