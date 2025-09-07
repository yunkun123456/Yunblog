package com.ssj.yunblog.setting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.setting.entity.BlogSettings;
import com.ssj.yunblog.setting.dao.BlogSettingsDao;
import com.ssj.yunblog.setting.entity.vo.BlogSettingsVo;
import com.ssj.yunblog.setting.service.BlogSettingsService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 博客设置服务实现类
 *
 * @author yunkun
 * @since 2025-09-07 11:57:37
 */
@Service("blogSettingsService")
public class BlogSettingsServiceImpl extends ServiceImpl<BlogSettingsDao, BlogSettings> implements BlogSettingsService {

    @Resource
    private BlogSettingsDao blogSettingsDao;

    @Override
    public Result<BlogSettingsVo> queryBlogSettingDetail() {
        List<BlogSettings> blogSettings = blogSettingsDao.selectList(null);
        if (blogSettings.isEmpty()) {
            return Result.fail("查询博客设置信息失败！");
        }
        BlogSettings settings = blogSettings.getFirst();
        BlogSettingsVo settingsVo = new BlogSettingsVo();
        BeanUtils.copyProperties(settings, settingsVo);
        return Result.ok(settingsVo);
    }
}
