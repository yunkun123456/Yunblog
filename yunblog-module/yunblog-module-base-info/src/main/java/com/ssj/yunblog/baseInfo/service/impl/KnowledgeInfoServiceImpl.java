package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.entity.KnowledgeInfo;
import com.ssj.yunblog.baseInfo.dao.KnowledgeInfoDao;
import com.ssj.yunblog.baseInfo.service.KnowledgeInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * (KnowledgeInfo)表服务实现类
 *
 * @author yunkun
 * @since 2026-01-03 20:18:34
 */
@Service("knowledgeInfoService")
public class KnowledgeInfoServiceImpl extends ServiceImpl<KnowledgeInfoDao,KnowledgeInfo> implements KnowledgeInfoService {

    @Resource
    private KnowledgeInfoDao knowledgeInfoDao;


}
