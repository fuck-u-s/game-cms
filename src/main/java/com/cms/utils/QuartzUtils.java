package com.cms.utils;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.*;

/**
 * Created by Zephyr on 2017/4/19.
 */
public class QuartzUtils {

    public static List<CronTrigger> getTriggers(SchedulerFactoryBean schedulerFactoryBean) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<String> triggerGroupNames = scheduler.getTriggerGroupNames();

        List<CronTrigger> results = new ArrayList<CronTrigger>();
        for(String name: triggerGroupNames) {
            GroupMatcher<TriggerKey> triggerKeyGroupMatcher = GroupMatcher.groupEquals(name);
            Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(triggerKeyGroupMatcher);
            Iterator<TriggerKey> it = triggerKeys.iterator();
            while (it.hasNext()) {
                TriggerKey o = it.next();
                CronTrigger trigger = (CronTrigger)scheduler.getTrigger(o);
                results.add(trigger);
            }
        }
        return results;
    }

    public static JobDetail getJobDetail(SchedulerFactoryBean schedulerFactoryBean, Trigger trigger) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDetail jobDetail = scheduler.getJobDetail(trigger.getJobKey());
        return jobDetail;
    }
}
