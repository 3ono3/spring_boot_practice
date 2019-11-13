package com.example.demo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author GuoJingyuan
 * @date 2019/9/23
 */
@Component
public class SchedulerConfig {
    @Scheduled(cron = "*/6 * * * * ?")
    public void scheduleTask() {
        System.out.println("corn 定时任务");
    }
}
