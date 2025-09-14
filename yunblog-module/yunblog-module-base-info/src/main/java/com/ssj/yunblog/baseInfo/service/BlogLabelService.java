package com.ssj.yunblog.baseInfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.baseInfo.entity.BlogLabel;
import com.ssj.yunblog.baseInfo.entity.bo.BlogLabelBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogLabelVo;
import com.ssj.yunblog.common.entity.Result;

import java.util.List;

/**
 * (BlogLabel)表服务接口
 *
 * @author yunkun
 * @since 2025-09-13 18:09:44
 */
public interface BlogLabelService extends IService<BlogLabel> {

    /**
     * 添加博客标签信息
     */
    Result<Boolean> add(BlogLabelBo blogLabel);

    /**
     * 删除博客标签信息
     */
    Result<Boolean> delete(String id);

    /**
     * 查询所有标签信息
     */
    Result<List<BlogLabelVo>> queryAllLabels();
}
