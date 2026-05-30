package com.ssj.yunblog.baseInfo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssj.yunblog.baseInfo.dao.BlogCommentDao;
import com.ssj.yunblog.baseInfo.entity.BlogComment;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCommentBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCommentQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogCommentVo;
import com.ssj.yunblog.baseInfo.service.BlogCommentService;
import com.ssj.yunblog.common.constant.RedisKey;
import com.ssj.yunblog.common.constant.ResultCode;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import com.ssj.yunblog.common.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 评论服务实现类
 *
 * @author yunkun
 * @time: 2026-05-30
 */
@Service("blogCommentService")
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentDao, BlogComment> implements BlogCommentService {

    @Resource
    private BlogCommentDao blogCommentDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result<Boolean> add(BlogCommentBo comment) {
        if (!StpUtil.isLogin()) {
            return Result.fail("请登录后发表评论", ResultCode.UNAUTHORIZED);
        }

        BlogComment blogComment = new BlogComment();
        BeanUtils.copyProperties(comment, blogComment);
        blogComment.setUserId(StpUtil.getLoginId().toString());

        Object object = redisTemplate.opsForValue().get(RedisKey.USER_INFO_KEY + StpUtil.getLoginId());
        try {
            String json = new ObjectMapper().writeValueAsString(object);
            Map<String, String> map = new ObjectMapper().readValue(json, Map.class);
            blogComment.setNickname(map.getOrDefault("nickName", ""));
            blogComment.setAvatar(map.getOrDefault("avatar", ""));
        } catch (Exception e) {
            return Result.fail("网络异常，请稍后重试", ResultCode.FAIL);
        }

        blogComment.setLikeCount(0);
        blogComment.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());

        if (blogCommentDao.insert(blogComment) <= 0) {
            return Result.fail("评论发布失败");
        }

        return Result.ok(true, "评论发布成功");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result<Boolean> edit(BlogCommentBo comment) {
        if (!StpUtil.isLogin()) {
            return Result.fail("请登录后编辑评论", ResultCode.UNAUTHORIZED);
        }
        if (comment.getId() == null || comment.getId().isEmpty()) {
            return Result.fail("评论ID不能为空");
        }

        BlogComment existing = blogCommentDao.selectById(comment.getId());
        if (existing == null) {
            return Result.fail("评论不存在");
        }
        if (!existing.getUserId().equals(StpUtil.getLoginId().toString())) {
            return Result.fail("只能编辑自己的评论");
        }

        BlogComment blogComment = new BlogComment();
        blogComment.setId(comment.getId());
        blogComment.setContent(comment.getContent());
        if (comment.getReplyNickname() != null) {
            blogComment.setReplyNickname(comment.getReplyNickname());
        }

        if (blogCommentDao.updateById(blogComment) > 0) {
            return Result.ok(true, "评论编辑成功");
        }
        return Result.fail("评论编辑失败");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result<Boolean> delete(String id) {
        if (!StpUtil.isLogin()) {
            return Result.fail("请登录后删除评论", ResultCode.UNAUTHORIZED);
        }
        if (id == null || id.isEmpty()) {
            return Result.fail("评论ID不能为空");
        }

        BlogComment existing = blogCommentDao.selectById(id);
        if (existing == null) {
            return Result.fail("评论不存在");
        }
        if (!existing.getUserId().equals(StpUtil.getLoginId().toString())) {
            return Result.fail("只能删除自己的评论");
        }

        BlogComment blogComment = new BlogComment();
        blogComment.setId(id);
        blogComment.setDelStatus(DeleteStatusEnum.DELETED.getCode());
        if (blogCommentDao.updateById(blogComment) > 0) {
            return Result.ok(true, "删除成功");
        }
        return Result.fail("删除失败");
    }

    @Override
    public Result<IPage<BlogCommentVo>> queryPageList(BlogCommentQueryBo param) {
        if (param.getCurrent() == null || param.getCurrent() < 1) {
            param.setCurrent(1);
        }
        if (param.getSize() == null || param.getSize() < 1) {
            param.setSize(10);
        }

        LambdaQueryWrapper<BlogComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogComment::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());

        if (param.getType() != null && !param.getType().isEmpty()) {
            queryWrapper.eq(BlogComment::getType, param.getType());
        }
        if (param.getParentId() != null && !param.getParentId().isEmpty()) {
            queryWrapper.eq(BlogComment::getParentId, param.getParentId());
        }
        if (param.getNickname() != null && !param.getNickname().isEmpty()) {
            queryWrapper.like(BlogComment::getNickname, param.getNickname());
        }

        queryWrapper.orderByDesc(BlogComment::getCreateTime);

        Page<BlogComment> page = new Page<>(param.getCurrent(), param.getSize());
        IPage<BlogComment> commentPage = blogCommentDao.selectPage(page, queryWrapper);

        Page<BlogCommentVo> result = new Page<>();
        result.setCurrent(commentPage.getCurrent());
        result.setSize(commentPage.getSize());
        result.setTotal(commentPage.getTotal());

        // TODO: 设置 liked 字段
        result.setRecords(commentPage.getRecords().stream().map(comment -> {
            BlogCommentVo vo = new BlogCommentVo();
            vo.setId(comment.getId());
            vo.setType(comment.getType());
            vo.setParentId(comment.getParentId());
            vo.setNickname(comment.getNickname());
            vo.setContent(comment.getContent());
            vo.setCreateTime(comment.getCreateTime().toString());
            vo.setReplyNickname(comment.getReplyNickname());
            vo.setLikeCount(comment.getLikeCount() == null ? 0 : comment.getLikeCount());
            vo.setLiked(false);
            return vo;
        }).toList());

        return Result.ok(result);
    }

    @Override
    public Result<BlogCommentVo> getDetail(String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("评论ID不能为空");
        }

        BlogComment comment = blogCommentDao.selectById(id);
        if (comment == null) {
            return Result.fail("评论不存在");
        }

        BlogCommentVo vo = new BlogCommentVo();
        vo.setId(comment.getId());
        vo.setType(comment.getType());
        vo.setParentId(comment.getParentId());
        vo.setNickname(comment.getNickname());
        vo.setContent(comment.getContent());
        vo.setCreateTime(comment.getCreateTime().toString());
        vo.setReplyNickname(comment.getReplyNickname());
        vo.setLikeCount(comment.getLikeCount() == null ? 0 : comment.getLikeCount());
        vo.setLiked(false);

        return Result.ok(vo);
    }
}