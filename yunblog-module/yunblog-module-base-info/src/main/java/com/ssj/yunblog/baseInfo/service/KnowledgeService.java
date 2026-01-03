package com.ssj.yunblog.baseInfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.baseInfo.entity.Knowledge;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeInfoBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.KnowledgeVo;
import com.ssj.yunblog.common.entity.Result;

/**
 * 知识库服务接口
 *
 * @author yunkun
 * @since 2026-01-03 20:06:20
 */
public interface KnowledgeService extends IService<Knowledge> {

    /**
     * 分页查询知识库列表
     */
    Result<IPage<KnowledgeVo>> queryKnowledgePage(KnowledgeQueryBo param);

    /**
     * 新增知识库
     */
    Result<Boolean> add(KnowledgeBo knowledge);

    /**
     * 新增分组或文章
     */
    Result<Boolean> addGroupOrArticle(KnowledgeInfoBo knowledgeInfoBo);
}
