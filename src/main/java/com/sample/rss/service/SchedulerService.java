package com.sample.rss.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService extends QuartzJobBean {

    @Autowired
    RssFeedConsumerService rssFeedConsumerService;

    @Value("${rss.feed.refresh.frequency}")
    private int duration;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        rssFeedConsumerService.consumeRss();
    }

    @Bean
    public JobDetail rssFeedJobDetail() {
        return JobBuilder.newJob(SchedulerService.class).withIdentity("rssFeed")
                .usingJobData("rss", "feed").storeDurably().build();
    }

    @Bean
    public Trigger rssFeedJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(duration).repeatForever();

        return TriggerBuilder.newTrigger().forJob(rssFeedJobDetail())
                .withIdentity("rssFeedTrigger").withSchedule(scheduleBuilder).build();
    }
}
