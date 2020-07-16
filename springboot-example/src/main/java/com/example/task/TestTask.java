package com.example.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {

    @Scheduled(cron = "0 30 1 * * ?")
    public void test() {
        System.out.println("启动定时任务...");
    }

}