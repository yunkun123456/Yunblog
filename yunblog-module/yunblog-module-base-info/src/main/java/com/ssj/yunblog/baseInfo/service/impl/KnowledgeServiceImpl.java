package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.dao.KnowledgeInfoDao;
import com.ssj.yunblog.baseInfo.entity.BlogInfo;
import com.ssj.yunblog.baseInfo.entity.Knowledge;
import com.ssj.yunblog.baseInfo.dao.KnowledgeDao;
import com.ssj.yunblog.baseInfo.entity.KnowledgeInfo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeInfoBo;
import com.ssj.yunblog.baseInfo.entity.bo.KnowledgeQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.KnowledgeVo;
import com.ssj.yunblog.baseInfo.service.KnowledgeService;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
}
