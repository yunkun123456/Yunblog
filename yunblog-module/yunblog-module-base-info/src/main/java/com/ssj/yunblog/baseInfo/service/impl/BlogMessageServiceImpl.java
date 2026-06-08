package com.ssj.yunblog.baseInfo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssj.yunblog.baseInfo.dao.BlogMessageDao;
import com.ssj.yunblog.baseInfo.dao.BlogMessageDetailDao;
import com.ssj.yunblog.baseInfo.entity.BlogMessage;
import com.ssj.yunblog.baseInfo.entity.BlogMessageDetail;
import com.ssj.yunblog.baseInfo.entity.bo.BlogCommentQueryBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageBo;
import com.ssj.yunblog.baseInfo.entity.bo.BlogMessageQueryBo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogCommentVo;
import com.ssj.yunblog.baseInfo.entity.vo.BlogMessageVo;
import com.ssj.yunblog.baseInfo.service.BlogCommentService;
import com.ssj.yunblog.baseInfo.service.BlogMessageService;
import com.ssj.yunblog.common.constant.RedisKey;
import com.ssj.yunblog.common.constant.ResultCode;
import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 留言服务实现类
 *
 * @author yunkun
 * @since 2026-05-24
 */
@Service("blogMessageService")
public class BlogMessageServiceImpl extends ServiceImpl<BlogMessageDao, BlogMessage> implements BlogMessageService {

    @Resource
    private BlogMessageDao blogMessageDao;

    @Resource
    private BlogMessageDetailDao blogMessageDetailDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private BlogCommentService blogCommentService;

    /**
     * 发布留言
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result<Boolean> add(BlogMessageBo blogMessage) {
        if (!StpUtil.isLogin()) {
            return Result.fail("请登录后发布留言", ResultCode.UNAUTHORIZED);
        }
        BlogMessage message = new BlogMessage();
        message.setUserId(StpUtil.getLoginId().toString());
        Object object = redisTemplate.opsForValue().get(RedisKey.USER_INFO_KEY + StpUtil.getLoginId());
        try {
            String json = new ObjectMapper().writeValueAsString(object);
            Map<String, String> map = new ObjectMapper().readValue(json, Map.class);
            message.setNickname(map.getOrDefault("nickName", ""));
            message.setEmail(map.getOrDefault("email", ""));
            message.setAvatar(map.getOrDefault("avatar", ""));
        } catch (Exception e) {
            return Result.fail("网络异常，请稍后重试", ResultCode.FAIL);
        }

        message.setTitle(blogMessage.getTitle());
        // todo 依赖用户输入讨论以及我们这边利用大模型在一段时间内进行总结
        message.setDiscussionName(blogMessage.getDiscussionName());
        message.setLikeCount(0);
        message.setCommentCount(0);
        message.setFavoriteCount(0);
        message.setStatus(0); // 默认待审核
        message.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());

        if (blogMessageDao.insert(message) <= 0) {
            return Result.fail("留言发布失败");
        }

        // 创建留言内容记录
        BlogMessageDetail detail = new BlogMessageDetail();
        detail.setMessageId(message.getId());
        detail.setContent(blogMessage.getContent());
        detail.setDelStatus(DeleteStatusEnum.UN_DELETED.getCode());

        if (blogMessageDetailDao.insert(detail) <= 0) {
            return Result.fail("留言内容保存失败");
        }

        return Result.ok(true, "留言发布成功，等待审核");
    }

    /**
     * 点赞功能
     * todo:标识用户点赞
     */
    @Override
    public Result<Boolean> like(String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }

        BlogMessage message = blogMessageDao.selectById(id);
        if (message == null) {
            return Result.fail("留言不存在");
        }

        message.setLikeCount((message.getLikeCount() == null ? 0 : message.getLikeCount()) + 1);
        if (blogMessageDao.updateById(message) > 0) {
            return Result.ok(true, "点赞成功");
        }
        return Result.fail("点赞失败");
    }

    /**
     * 审核留言
     */
    @Override
    public Result<Boolean> audit(String id, Integer status) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        if (status == null || (status != 1 && status != 2)) {
            return Result.fail("审核状态不正确");
        }

        BlogMessage message = blogMessageDao.selectById(id);
        if (message == null) {
            return Result.fail("留言不存在");
        }

        message.setStatus(status);
        if (blogMessageDao.updateById(message) > 0) {
            String msg = status == 1 ? "审核通过" : "审核拒绝";
            return Result.ok(true, msg);
        }
        return Result.fail("审核失败");
    }

    /**
     * 删除留言
     */
    @Override
    public Result<Boolean> delete(String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }

        BlogMessage message = new BlogMessage();
        message.setId(id);
        message.setDelStatus(DeleteStatusEnum.DELETED.getCode());
        if (blogMessageDao.updateById(message) > 0) {
            return Result.ok(true, "删除成功");
        }
        return Result.fail("删除失败");
    }

    /**
     * 分页查询留言信息 - frontend
     */
    @Override
    public Result<IPage<BlogMessageVo>> queryPageList(BlogMessageQueryBo param) {
        LambdaQueryWrapper<BlogMessage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogMessage::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .eq(BlogMessage::getStatus, 1);

        if (param.getCategory() != null && !param.getCategory().equals("all")) {
            queryWrapper.eq(BlogMessage::getCategory, param.getCategory());
        }

        queryWrapper.orderBy(!param.getSortBy().isEmpty() && "likeCount".equals(param.getSortBy()), param.getSortOrder().equals("asc"), BlogMessage::getLikeCount);
        queryWrapper.orderBy(!param.getSortBy().isEmpty() && "createTime".equals(param.getSortBy()), param.getSortOrder().equals("asc"), BlogMessage::getCreateTime);

        Page<BlogMessage> page = new Page<>(param.getCurrent(), param.getSize());
        IPage<BlogMessage> messagePage = blogMessageDao.selectPage(page, queryWrapper);

        // 排序处理
        List<BlogMessage> records = messagePage.getRecords();

        Page<BlogMessageVo> result = new Page<>();
        result.setCurrent(messagePage.getCurrent());
        result.setSize(messagePage.getSize());
        result.setTotal(messagePage.getTotal());

        List<BlogMessageVo> list = records.stream().map(item -> {
            BlogMessageVo vo = new BlogMessageVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCreateTime(item.getCreateTime().toString());
            return vo;
        }).toList();

        result.setRecords(list);
        return Result.ok(result);
    }

    /**
     * 分页查询 - 管理端
     */
    @Override
    public Result<IPage<BlogMessageVo>> queryPageListAdmin(BlogMessageQueryBo param) {
        LambdaQueryWrapper<BlogMessage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogMessage::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .eq(param.getStatus() != null, BlogMessage::getStatus, param.getStatus())
                .like(param.getNickname() != null && !param.getNickname().isEmpty(),
                        BlogMessage::getNickname, param.getNickname());

        Page<BlogMessage> page = new Page<>(param.getCurrent(), param.getSize());
        IPage<BlogMessage> messagePage = blogMessageDao.selectPage(page, queryWrapper);

        Page<BlogMessageVo> result = new Page<>();
        List<BlogMessageVo> list = messagePage.getRecords().stream().map(item -> {
            BlogMessageVo vo = new BlogMessageVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCreateTime(item.getCreateTime().toString());
            return vo;
        }).toList();

        result.setRecords(list);
        result.setTotal(messagePage.getTotal());
        return Result.ok(result);
    }

    /**
     * 获取留言详情
     */
    @Override
    public Result<BlogMessageVo> getDetail(String id) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }

        BlogMessage message = blogMessageDao.selectById(id);
        if (message == null) {
            return Result.fail("留言不存在");
        }

        BlogMessageVo vo = new BlogMessageVo();
        BeanUtils.copyProperties(message, vo);
        vo.setCreateTime(message.getCreateTime().toString());

        LambdaQueryWrapper<BlogMessageDetail> detailWrapper = new LambdaQueryWrapper<>();
        detailWrapper.eq(BlogMessageDetail::getMessageId, id)
                .eq(BlogMessageDetail::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
        BlogMessageDetail detail = blogMessageDetailDao.selectOne(detailWrapper);
        if (detail != null) {
            vo.setContent(detail.getContent());
        }

        return Result.ok(vo);
    }

    /**
     * 获取留言评论
     */
    @Override
    public Result<IPage<BlogCommentVo>> getComments(String id, Integer current, Integer size) {
        if (id == null || id.isEmpty()) {
            return Result.fail("留言ID不能为空");
        }
        if (current == null || current < 1) {
            current = 1;
        }
        if (size == null || size < 1) {
            size = 10;
        }

        BlogMessage message = blogMessageDao.selectById(id);
        if (message == null) {
            return Result.fail("留言不存在");
        }

        BlogCommentQueryBo param = new BlogCommentQueryBo();
        param.setType("message");
        param.setParentId(id);
        param.setCurrent(current);
        param.setSize(size);

        return blogCommentService.queryPageList(param);
    }
}