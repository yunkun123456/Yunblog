package com.ssj.yunblog.baseInfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.baseInfo.entity.Knowledge;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeDetailBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeInfoBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.KnowledgeInfoVo;
import com.ssj.yunblog.baseInfo.entity.vo.KnowledgeVo;
import com.ssj.yunblog.common.entity.Result;

import java.util.List;

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

    /**
     * 新增文章内容
     */
    Result<Boolean> addArticleDetail(KnowledgeDetailBo knowledgeDetailBo);

    /**
     * 更新知识库
     */
    Result<Boolean> updateKnowledge(KnowledgeBo knowledge);

    /**
     * 查询知识库下级信息列表
     */
    Result<List<KnowledgeInfoVo>> queryLowList(String id);

    /**
     * 删除知识库
     */
    Result<Boolean> deleteKnowledge(String id);

    /**
     * 更新分组或文章
     */
    Result<Boolean> updateGroupOrArticle(KnowledgeInfoBo knowledgeInfoBo);

    /**
     * 删除分组或文章
     */
    Result<Boolean> deleteGroupOrArticle(String id);

    /**
     * 更新文章内容
     */
    Result<Boolean> updateArticleDetail(KnowledgeDetailBo knowledgeDetailBo);

    /**
     * 查询知识库树形列表
     */
    Result<List<KnowledgeInfoVo>> queryTreeList(String id);

    /**
     * 查询知识库信息树形列表 - 扩展包含文章内容
     */
    Result<List<KnowledgeInfoVo>> queryTreePaperList(String id);

    /**
     * 根据id查询文章内容
     */
    Result<String> queryPaperById(String id);
}
