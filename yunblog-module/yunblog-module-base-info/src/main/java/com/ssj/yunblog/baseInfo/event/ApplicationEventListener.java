package com.ssj.yunblog.baseInfo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class ApplicationEventListener {

    /**
     * 监听应用启动事件（比 ApplicationRunner 更早）
     */
    @EventListener(ApplicationStartedEvent.class)
    public void onApplicationStarted(ApplicationStartedEvent event) {
        log.info("========== 应用已启动 (ApplicationStartedEvent) ==========");
        log.info("========== 初始化redis点赞信息 (ApplicationStartedEvent) ==========");
        // TODO 目前数据较少，直接查询所有的博客信息进行同步
        // TODO 后面数据多的话，导致启动时间太长，可以放到定时任务里面进行同步(感觉有点违反完全信赖redis这一初衷，还需要再思考一下)

    }

    /**
     * 监听应用准备就绪事件（此时可以安全地对外提供服务）
     */
//    @EventListener(ApplicationReadyEvent.class)
//    public void onApplicationReady(ApplicationReadyEvent event) {
//        log.info("========== 应用准备就绪 (ApplicationReadyEvent) ==========");
//
//        // 执行关键初始化操作
//        performCriticalInitializations();
//
//        // 打印启动成功信息
//        printStartupBanner();
//
//        // 记录启动日志
//        logApplicationStartup();
//    }

    /**
     * 监听应用启动失败事件
     */
//    @EventListener(ApplicationFailedEvent.class)
//    public void onApplicationFailed(ApplicationFailedEvent event) {
//        log.error("========== 应用启动失败 ==========", event.getException());
//        // 可以发送告警通知
//    }

//    private void performCriticalInitializations() {
//        // 关键初始化操作，例如：
//        // 1. 检查外部服务依赖
//        // 2. 初始化分布式锁
//        // 3. 创建必要的目录结构
//        // 4. 验证数据库迁移状态
//
//        log.info("执行关键初始化操作完成");
//    }

//    private void printStartupBanner() {
//        String banner = """
//
//            ===========================================
//                Spring Boot 应用启动成功!
//                时间: %s
//                端口: %s
//                环境: %s
//                版本: %s
//            ===========================================
//            """.formatted(
//                LocalDateTime.now(),
//                System.getProperty("server.port", "8080"),
//                System.getProperty("spring.profiles.active", "default"),
//                getClass().getPackage().getImplementationVersion()
//        );
//
//        log.info(banner);
//    }

//    private void logApplicationStartup() {
//        // 记录启动信息到数据库或日志文件
//        log.info("应用启动信息已记录");
//    }
}
