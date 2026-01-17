package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.entity.KnowledgeDetail;
import com.ssj.yunblog.baseInfo.dao.KnowledgeDetailDao;
import com.ssj.yunblog.baseInfo.service.KnowledgeDetailService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * (KnowledgeDetail)表服务实现类
 *
 * @author yunkun
 * @since 2026-01-17 15:18:34
 */
@Service("knowledgeDetailService")
public class KnowledgeDetailServiceImpl extends ServiceImpl<KnowledgeDetailDao, KnowledgeDetail> implements KnowledgeDetailService {

    @Resource
    private KnowledgeDetailDao knowledgeDetailDao;

}
