package com.ssj.yunblog.baseInfo.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ssj.yunblog.baseInfo.ai.AIModelService;
import com.ssj.yunblog.baseInfo.dao.BlogInfoDao;
import com.ssj.yunblog.baseInfo.dao.BlogInfoDetailDao;
import com.ssj.yunblog.baseInfo.entity.BlogInfo;
import com.ssj.yunblog.baseInfo.entity.BlogInfoDetail;
import com.ssj.yunblog.common.enums.DeleteStatusEnum;
import com.ssj.yunblog.common.enums.RecommendStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public void generateCoverPic() {
        LambdaQueryWrapper<BlogInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogInfo::getRecommend, RecommendStatusEnum.RECOMMEND.getCode())
                .eq(BlogInfo::getDelStatus, DeleteStatusEnum.UN_DELETED.getCode())
                .isNull(BlogInfo::getCoverUrl);
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
            // TODO 这边先使用introduction，后续可以让大模型左一个内容提炼
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
}
