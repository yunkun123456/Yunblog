package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.entity.BlogInfoDetail;
import com.ssj.yunblog.baseInfo.dao.BlogInfoDetailDao;
import com.ssj.yunblog.baseInfo.service.BlogInfoDetailService;
import org.springframework.stereotype.Service;


/**
 * (BlogInfoDetail)表服务实现类
 *
 * @author yunkun
 * @since 2025-09-13 17:08:57
 */
@Service("blogInfoDetailService")
public class BlogInfoDetailServiceImpl extends ServiceImpl<BlogInfoDetailDao, BlogInfoDetail> implements BlogInfoDetailService {
}
