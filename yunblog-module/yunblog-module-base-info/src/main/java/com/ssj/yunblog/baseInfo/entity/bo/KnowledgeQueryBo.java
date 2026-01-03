package com.ssj.yunblog.baseInfo.entity.bo;

import lombok.Data;

/**
 * 知识库查询参数
 *
 * @author: sunshijie
 * @Date: 2026/1/3
 */
@Data
public class KnowledgeQueryBo {

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 分页大小
     */
    private Integer pageSize = 10;
    /**
     * 当前页码
     */
    private Integer pageNum = 1;
}
