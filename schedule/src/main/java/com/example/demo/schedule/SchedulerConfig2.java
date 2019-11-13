package com.example.demo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author GuoJingyuan
 * @date 2019/9/23
 */
@Component
public class SchedulerConfig2 {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 6000)
    public void scheduleTask() {
        System.out.println("fixedRate 定时任务,当前时间: " + dateFormat.format(new Date()));
    }
}
