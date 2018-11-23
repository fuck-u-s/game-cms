package com.cms.web.controller.sys;

import com.cms.utils.DateUtil;
import com.cms.utils.QuartzUtils;
import com.google.gson.Gson;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Zephyr on 2017/4/19.
 */
@Controller
@RequestMapping(value = "quartz")
public class QuartzController {

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @RequestMapping(value = "getJobs")
    @ResponseBody
    public String getJobs() throws Exception{
        String result = "";
        List<CronTrigger> list = QuartzUtils.getTriggers(schedulerFactoryBean);
        for(CronTrigger trigger: list) {
            JobDetail jobDetail = QuartzUtils.getJobDetail(schedulerFactoryBean, trigger);
            result += schedulerFactoryBean.getScheduler().getTriggerState(trigger.getKey()) + "==>";
            result += trigger.getDescription() + "---" + DateUtil.formartRandomDate(trigger.getNextFireTime(), "yyyy-MM-dd HH:mm:ss") + "--" + trigger.getCronExpression() + ";";
        }
        return result;
    }
}
