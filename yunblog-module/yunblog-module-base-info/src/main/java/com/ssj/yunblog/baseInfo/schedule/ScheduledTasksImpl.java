package com.ssj.yunblog.baseInfo.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ssj.yunblog.baseInfo.ai.AIModelService;
import com.ssj.yunblog.baseInfo.dao.BlogInfoDao;
import com.ssj.yunblog.baseInfo.dao.BlogInfoDetailDao;
import com.ssj.yunblog.baseInfo.entity.BlogInfo;
import com.ssj.yunblog.baseInfo.entity.BlogInfoDetail;
import com.ssj.yunblog.common.constant.RedisKey;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import com.ssj.yunblog.common.enums.RecommendStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 具体任务实现
 *
 * @author: yunkun
 * @Date: 2025/12/7
 */
@Component
public class ScheduledTasksImpl {

    @Resource
    private AIModelService aiModelService;

    @Resource
    private BlogInfoDao blogInfoDao;

    @Resource
    private BlogInfoDetailDao blogInfoDetailDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 文生图 - 封面
     */
    public void generateCoverPic() {
        LambdaQueryWrapper<BlogInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogInfo::getRecommend, RecommendStatusEnum.RECOMMEND.getCode())
                .eq(BlogInfo::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .and(w -> w.isNull(BlogInfo::getCoverUrl)
                        .or()
                        .eq(BlogInfo::getCoverUrl, ""));
        List<BlogInfo> blogInfos = blogInfoDao.selectList(wrapper);
        for (BlogInfo blogInfo : blogInfos) {
            String blogId = blogInfo.getId();
            LambdaQueryWrapper<BlogInfoDetail> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(BlogInfoDetail::getBlogId, blogId)
                    .eq(BlogInfoDetail::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode());
            BlogInfoDetail detail = blogInfoDetailDao.selectOne(queryWrapper);
            if (detail == null) {
                continue;
            }
            // TODO 这边先使用introduction，后续可以让大模型做一个内容content提炼
            String content = blogInfo.getIntroduction();
            String picName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
            try {
                aiModelService.generatePic(content, picName);
                blogInfo.setCoverUrl(picName);
                int i = blogInfoDao.updateById(blogInfo);
                System.out.println("update success");
            } catch (Exception e) {
                // TODO 日志打印
                e.printStackTrace();
            }
        }
    }

    /**
     * 同步redis中的点赞信息
     */
    public void syncRedisLikes() {
        String hashKey = RedisKey.BLOG_LIKES;
        // 每次扫描1000条，避免长时间阻塞redis
        int batchSize = 1000;
        ScanOptions scan = ScanOptions.scanOptions().count(batchSize).build();
        Cursor<Map.Entry<Object, Object>> cursor = null;

        try {
            cursor = redisTemplate.opsForHash().scan(hashKey, scan);
            while (cursor.hasNext()) {
                Map.Entry<Object, Object> entry = cursor.next();
                String key = (String) entry.getKey();
                Integer likeCount = Integer.valueOf(entry.getValue().toString());
                BlogInfo entity = new BlogInfo();
                entity.setId(key);
                entity.setLikeNum(likeCount);
                blogInfoDao.updateById(entity);
            }

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
}
