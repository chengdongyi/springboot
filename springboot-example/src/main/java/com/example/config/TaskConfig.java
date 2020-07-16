package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @description: 配置定时任务线程池
 * @author chengdongyi
 * @date 2020/7/14 20:21
 */
@EnableScheduling
@Configuration
public class TaskConfig {

    @Bean
    public TaskScheduler taskScheduler() {

        ThreadPoolTaskScheduler threadPool = new ThreadPoolTaskScheduler();
        // 设置定时任务线程池 poolSize
        threadPool.setPoolSize(10);
        threadPool.setThreadNamePrefix("TaskScheduler-");
        return threadPool;

    }

}
