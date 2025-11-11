package com.ssj.yunblog.baseInfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssj.yunblog.baseInfo.entity.BlogCategory;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCategoryBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCategoryQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogCategoryVo;
import com.ssj.yunblog.common.entity.Result;

import java.util.List;

/**
 * (BlogCategory)表服务接口
 *
 * @author yunkun
 * @since 2025-09-13 18:09:08
 */
public interface BlogCategoryService extends IService<BlogCategory> {

    /**
     * 新增博客分类信息
     */
    Result<Boolean> add(BlogCategoryBo blogCategory);

    /**
     * 删除分类信息
     */
    Result<Boolean> delete(String id);

    /**
     * 查询所有分类信息
     */
    Result<List<BlogCategoryVo>> queryAllCategory();

    /**
     * 条件查询分类信息
     */
    Result<List<BlogCategoryVo>> queryCategoryList(BlogCategoryQueryBo param);
}
