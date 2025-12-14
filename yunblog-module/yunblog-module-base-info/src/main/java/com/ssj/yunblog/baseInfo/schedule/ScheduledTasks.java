package com.ssj.yunblog.baseInfo.schedule;

import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 定时任务
 * @author: yunkun
 * @Date: 2025/12/7
 */
@Component
@Slf4j
public class ScheduledTasks {

    @Resource
    private ScheduledTasksImpl scheduledTasksImpl;

    /**
     * 固定频率执行：每5秒执行一次
     * fixedRate：固定速率，从任务开始时间计算间隔
     */
//    @Scheduled(fixedRate = 5000)
//    public void taskWithFixedRate() {
//        log.info("固定速率任务执行，当前时间：{}", LocalDateTime.now());
////        scheduledTasksImpl.generateCoverPic();
//    }

    /**
     * Cron 表达式执行：每天凌晨执行
     * Cron 表达式格式：秒 分 时 日 月 周 年（可选）
     * 定时生成封面图
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void taskWithCronExpression() {
        log.info("文生图-封面，当前时间：{}", LocalDateTime.now());
        scheduledTasksImpl.generateCoverPic();
    }

    /**
     * Cron 表达式执行：每天凌晨执行
     * Cron 表达式格式：秒 分 时 日 月 周 年（可选）
     * 定时同步点赞信息
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void syncRedisLikes() {
        log.info("点赞信息同步，当前时间：{}", LocalDateTime.now());
        scheduledTasksImpl.syncRedisLikes();
    }
}
