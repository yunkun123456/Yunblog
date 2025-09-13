package com.ssj.yunblog.baseInfo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.baseInfo.entity.BlogLabel;
import com.ssj.yunblog.baseInfo.dao.BlogLabelDao;
import com.ssj.yunblog.baseInfo.entity.bo.BlogLabelBo;
import com.ssj.yunblog.baseInfo.service.BlogLabelService;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


/**
 * (BlogLabel)表服务实现类
 *
 * @author yunkun
 * @since 2025-09-13 18:09:44
 */
@Service("blogLabelService")
public class BlogLabelServiceImpl extends ServiceImpl<BlogLabelDao, BlogLabel> implements BlogLabelService {

    @Resource
    private BlogLabelDao blogLabelDao;


    /**
     * 新增博客标签信息
     */
    @Override
    public Result<Boolean> add(BlogLabelBo blogLabel) {
        BlogLabel label = new BlogLabel();
        BeanUtils.copyProperties(blogLabel, label);
        label.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());
        if (blogLabelDao.insert(label) > 0) {
            return Result.ok();
        }
        return Result.fail("新增博客标签信息失败");
    }

    /**
     * 删除博客标签信息
     */
    @Override
    public Result<Boolean> delete(String id) {
        BlogLabel label = new BlogLabel();
        label.setId(id);
        label.setDelStatus(DeleteStatusEnum.DELETED.getCode());
        if (blogLabelDao.updateById(label) > 0) {
            return Result.ok();
        }
        return Result.fail("删除博客标签信息失败！");
    }
}
