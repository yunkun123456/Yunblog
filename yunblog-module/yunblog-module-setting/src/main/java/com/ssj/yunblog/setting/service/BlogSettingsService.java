package com.ssj.yunblog.setting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.setting.entity.BlogSettings;
import com.ssj.yunblog.setting.entity.vo.BlogSettingsVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (BlogSettings)表服务接口
 *
 * @author yunkun
 * @since 2025-09-07 11:57:34
 */
public interface BlogSettingsService extends IService<BlogSettings> {

    /**
     * 查询博客设置详情
     */
    Result<BlogSettingsVo> queryBlogSettingDetail();
}
