package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.dao.KnowledgeDetailDao;
import com.ssj.yunblog.baseInfo.dao.KnowledgeInfoDao;
import com.ssj.yunblog.baseInfo.entity.Knowledge;
import com.ssj.yunblog.baseInfo.dao.KnowledgeDao;
import com.ssj.yunblog.baseInfo.entity.KnowledgeDetail;
import com.ssj.yunblog.baseInfo.entity.KnowledgeInfo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeDetailBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeInfoBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.KnowledgeInfoVo;
import com.ssj.yunblog.baseInfo.entity.vo.KnowledgeVo;
import com.ssj.yunblog.baseInfo.service.KnowledgeService;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * (Knowledge)表服务实现类
 *
 * @author yunkun
 * @since 2026-01-03 20:06:20
 */
@Service("knowledgeService")
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeDao, Knowledge> implements KnowledgeService {

    @Resource
    private KnowledgeDao knowledgeDao;

    @Resource
    private KnowledgeInfoDao knowledgeInfoDao;

    @Resource
    private KnowledgeDetailDao knowledgeDetailDao;


    /**
     * 查询知识库列表
     */
    @Override
    public Result<IPage<KnowledgeVo>> queryKnowledgePage(KnowledgeQueryBo param) {
        LambdaQueryWrapper<Knowledge> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Knowledge::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .like(param.getKeyword() != null && !param.getKeyword().isEmpty(),
                        Knowledge::getName, param.getKeyword());
        Page<Knowledge> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<Knowledge> knowledgePage = knowledgeDao.selectPage(page, queryWrapper);
        IPage<KnowledgeVo> knowledgeVoPage = knowledgePage.convert(item -> {
            KnowledgeVo knowledgeVo = new KnowledgeVo();
            BeanUtils.copyProperties(item, knowledgeVo);
            String formattedTime = item.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            knowledgeVo.setCreateTime(formattedTime);
            return knowledgeVo;
        });
        return Result.ok(knowledgeVoPage);
    }

    /**
     * 新增知识库
     */
    @Override
    public Result<Boolean> add(KnowledgeBo knowledge) {
        Knowledge item = new Knowledge();
        BeanUtils.copyProperties(knowledge, item);
        item.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        knowledgeDao.insert(item);
        return Result.ok();
    }

    /**
     * 新增分组或文章
     */
    @Override
    public Result<Boolean> addGroupOrArticle(KnowledgeInfoBo knowledgeInfoBo) {
        KnowledgeInfo item = new KnowledgeInfo();
        BeanUtils.copyProperties(knowledgeInfoBo, item);
        item.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        knowledgeInfoDao.insert(item);
        return Result.ok();
    }

    /**
     * 新增文章内容
     */
    @Override
    public Result<Boolean> addArticleDetail(KnowledgeDetailBo knowledgeDetailBo) {
        KnowledgeDetail detail = new KnowledgeDetail();
        BeanUtils.copyProperties(knowledgeDetailBo, detail);
        detail.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        knowledgeDetailDao.insert(detail);
        return Result.ok(true);
    }

    /**
     * 更新知识库
     */
    @Override
    public Result<Boolean> updateKnowledge(KnowledgeBo knowledge) {
        Knowledge item = new Knowledge();
        BeanUtils.copyProperties(knowledge, item);
        knowledgeDao.updateById(item);
        return Result.ok(true);
    }

    /**
     * 查询知识库下级信息列表
     */
    @Override
    public Result<List<KnowledgeInfoVo>> queryLowList(String id) {
        List<KnowledgeInfo> list = knowledgeInfoDao.selectList(new LambdaQueryWrapper<KnowledgeInfo>()
                .eq(KnowledgeInfo::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .eq(KnowledgeInfo::getParentId, id));
        List<KnowledgeInfoVo> infoVos = list.stream().map((item) -> {
            KnowledgeInfoVo infoVo = new KnowledgeInfoVo();
            BeanUtils.copyProperties(item, infoVo);
            return infoVo;
        }).toList();
        return Result.ok(infoVos);
    }

    /**
     * 删除知识库
     */
    @Override
    public Result<Boolean> deleteKnowledge(String id) {
        Knowledge knowledge = new Knowledge();
        knowledge.setId(id);
        knowledge.setDelStatus(DeleteStatusEnum.DELETED.getCode());
        knowledgeDao.updateById(knowledge);
        return Result.ok(true);
    }

    /**
     * 更新分组或文章
     */
    @Override
    public Result<Boolean> updateGroupOrArticle(KnowledgeInfoBo knowledgeInfoBo) {
        KnowledgeInfo item = new KnowledgeInfo();
        BeanUtils.copyProperties(knowledgeInfoBo, item);
        knowledgeInfoDao.updateById(item);
        return Result.ok(true);
    }

    /**
     * 删除分组或文章
     */
    @Override
    public Result<Boolean> deleteGroupOrArticle(String id) {
        KnowledgeInfo item = new KnowledgeInfo();
        item.setId(id);
        item.setDelStatus(DeleteStatusEnum.DELETED.getCode());
        knowledgeInfoDao.updateById(item);
        return Result.ok(true);
    }

    /**
     * 更新文章内容
     */
    @Override
    public Result<Boolean> updateArticleDetail(KnowledgeDetailBo knowledgeDetailBo) {
        KnowledgeDetail detail = new KnowledgeDetail();
        BeanUtils.copyProperties(knowledgeDetailBo, detail);
        knowledgeDetailDao.updateById(detail);
        return Result.ok(true);
    }

    /**
     * 查询知识库树形列表
     */
    @Override
    public Result<List<KnowledgeInfoVo>> queryTreeList(String id) {
        List<KnowledgeInfo> list = knowledgeInfoDao.selectList(new LambdaQueryWrapper<KnowledgeInfo>()
                .eq(KnowledgeInfo::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .eq(KnowledgeInfo::getKnowledgeId, id));
        List<KnowledgeInfo> firstList = list.stream().filter(item -> item.getParentId().equals(id)).toList();
        List<KnowledgeInfoVo> infoVos = buildTree(firstList, list);
        return Result.ok(infoVos);
    }

    /**
     * 查询知识库信息树形列表 - 扩展包含文章内容
     */
    @Override
    public Result<List<KnowledgeInfoVo>> queryTreePaperList(String id) {
        List<KnowledgeInfo> list = knowledgeInfoDao.selectList(new LambdaQueryWrapper<KnowledgeInfo>()
                .eq(KnowledgeInfo::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .eq(KnowledgeInfo::getKnowledgeId, id));
        List<KnowledgeInfo> firstList = list.stream().filter(item -> item.getParentId().equals(id)).toList();
        List<KnowledgeInfoVo> infoVos = buildTree(firstList, list);
        // 第一个文件夹，默认展示。且查询相应的文章
        if (!infoVos.isEmpty()) {
            firstItemBuild(infoVos.getFirst());
        }
        return Result.ok(infoVos);
    }

    /**
     * 查询文章内容
     */
    @Override
    public Result<String> queryPaperById(String id) {
        LambdaQueryWrapper<KnowledgeDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(KnowledgeDetail::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .eq(KnowledgeDetail::getKnowledgeInfoId, id);
        KnowledgeDetail detail = knowledgeDetailDao.selectOne(queryWrapper);
        if (detail == null) {
            return Result.fail("文章内容不存在");
        }
        return Result.ok(detail.getContent());
    }

    /**
     * 构建树形结构
     */
    public List<KnowledgeInfoVo> buildTree(List<KnowledgeInfo> parentList, List<KnowledgeInfo> totalList) {
        if (parentList == null || parentList.isEmpty()) {
            return new ArrayList<>();
        }
        List<KnowledgeInfoVo> result = new ArrayList<>();
        for (KnowledgeInfo knowledgeInfo : parentList) {
            String parentId = knowledgeInfo.getId();
            List<KnowledgeInfo> children = totalList.stream().filter(item -> item.getParentId().equals(parentId)).toList();
            KnowledgeInfoVo infoVo = new KnowledgeInfoVo();
            BeanUtils.copyProperties(knowledgeInfo, infoVo);
            if (infoVo.getType().equals("1")) {
                infoVo.setType("group");
            } else {
                infoVo.setType("doc");
            }
            infoVo.setChildren(buildTree(children, totalList));
            result.add(infoVo);
        }
        return result;
    }

    /**
     * 首个知识库item构建
     */
    public void firstItemBuild(KnowledgeInfoVo result) {
        result.setIsOpen(true);
        // todo 查询文章内容
    }
}
