package com.ssj.yunblog.baseInfo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeDetailBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeInfoBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.KnowledgeInfoVo;
import com.ssj.yunblog.baseInfo.entity.vo.KnowledgeVo;
import com.ssj.yunblog.baseInfo.service.KnowledgeService;
import com.ssj.yunblog.common.api.Add;
import com.ssj.yunblog.common.api.Update;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
     * 查询知识库下级信息列表
     */
    @GetMapping("/list/{id}")
    public Result<List<KnowledgeInfoVo>> queryLowList(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("上级id不能为空");
        }
        return knowledgeService.queryLowList(id);
    }

    /**
     * 查询知识库信息树形列表
     */
    @GetMapping("/tree/{id}")
    public Result<List<KnowledgeInfoVo>> queryTreeList(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("上级id不能为空");
        }
        return knowledgeService.queryTreeList(id);
    }

    /**
     * 新增知识库
     */
    @PostMapping
    public Result<Boolean> add(@RequestBody @Validated(Add.class) KnowledgeBo knowledge) {
        return knowledgeService.add(knowledge);
    }

    /**
     * 更新知识库
     */
    @PutMapping
    public Result<Boolean> updateKnowledge(@RequestBody @Validated(Update.class) KnowledgeBo knowledge) {
        return knowledgeService.updateKnowledge(knowledge);
    }

    /**
     * 删除知识库
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteKnowledge(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("知识库id不能为空");
        }
        return knowledgeService.deleteKnowledge(id);
    }

    /**
     * 新增分组或文章
     */
    @PostMapping("/info")
    public Result<Boolean> addGroupOrArticle(@RequestBody @Validated(Add.class) KnowledgeInfoBo knowledgeInfoBo) {
        return knowledgeService.addGroupOrArticle(knowledgeInfoBo);
    }

    /**
     * 更新分组或文章
     */
    @PutMapping("/info")
    public Result<Boolean> updateGroupOrArticle(@RequestBody @Validated(Update.class) KnowledgeInfoBo knowledgeInfoBo) {
        return knowledgeService.updateGroupOrArticle(knowledgeInfoBo);
    }

    /**
     * 删除分组或文章
     */
    @DeleteMapping("/info/{id}")
    public Result<Boolean> deleteGroupOrArticle(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("知识库信息id不能为空");
        }
        return knowledgeService.deleteGroupOrArticle(id);
    }

    /**
     * 新增文章内容
     */
    @PostMapping("/detail")
    public Result<Boolean> addArticleDetail(@RequestBody @Validated(Add.class) KnowledgeDetailBo knowledgeDetailBo) {
        return knowledgeService.addArticleDetail(knowledgeDetailBo);
    }

    /**
     * 更新文章内容
     */
    @PutMapping("/detail")
    public Result<Boolean> updateArticleDetail(@RequestBody @Validated(Update.class) KnowledgeDetailBo knowledgeDetailBo) {
        return knowledgeService.updateArticleDetail(knowledgeDetailBo);
    }

}

