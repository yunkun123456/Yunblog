package com.ssj.yunblog.baseInfo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssj.yunblog.baseInfo.entity.Knowledge;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeInfoBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.KnowledgeVo;
import com.ssj.yunblog.baseInfo.service.KnowledgeService;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 知识库控制层
 *
 * @author yunkun
 * @since 2026-01-03 20:06:20
 */
@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Resource
    private KnowledgeService knowledgeService;

    /**
     * 分页查询知识库列表
     */
    @GetMapping("/page")
    public Result<IPage<KnowledgeVo>> queryByPage(KnowledgeQueryBo param) {
        return knowledgeService.queryKnowledgePage(param);
    }

    /**
     * 新增知识库
     */
    @PostMapping
    public Result<Boolean> add(@RequestBody KnowledgeBo knowledge) {
        return knowledgeService.add(knowledge);
    }

    /**
     * 新增分组或文章
     */
    @PostMapping("/info")
    public Result<Boolean> addGroupOrArticle(@RequestBody KnowledgeInfoBo knowledgeInfoBo) {
        return knowledgeService.addGroupOrArticle(knowledgeInfoBo);
    }

}

