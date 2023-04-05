package com.big.data.quartz.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.big.data.quartz.QuartzJob;
import com.big.data.quartz.QuartzSimpleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class QuartzSchedulerManager {
    @Autowired
    private Scheduler scheduler;

    // 开始执行定时器
    public void startJob(JSONArray array) throws SchedulerException {
        startJob(scheduler, array);
        scheduler.start();
    }

    // 获取Job信息
    public String getJobInfo(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }

    // 修改某个任务的执行时间
    public boolean modifyJob(String name, String group, String time) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
                    .withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    // 暂停所有任务
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    // 暂停某个任务
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.pauseJob(jobKey);
    }

    // 恢复所有任务
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    // 恢复某个任务
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.resumeJob(jobKey);
    }

    // 删除某个任务
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.deleteJob(jobKey);
    }

    // 启动任务1
    private void startJob(Scheduler scheduler, JSONArray array) throws SchedulerException {
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            String jobName = object.getString("jobName");
            String jobGroup = object.getString("jobGroup");
            String cron = object.getString("cron");
            // "0/5 * * * * ?"
            // 通过JobBuilder构建JobDetail实例，JobDetail规定其job只能是实现Job接口的实例
            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity(jobName, jobGroup).build();
            // 基于表达式构建触发器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            // CronTrigger表达式触发器 继承于Trigger。TriggerBuilder 用于构建触发器实例
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
                    .withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }

    }

    /**
     * 立即触发执行
     *
     * @param object object
     * @throws SchedulerException
     */
    public void simpleStartJob(JSONObject object) throws SchedulerException {
        String jobName = object.getString("jobName");
        String jobGroup = object.getString("jobGroup");
        //创建调度器Schedule
        JobDetail jobDetail = JobBuilder.newJob(QuartzSimpleJob.class).withIdentity(jobName, jobGroup)
                .build();
        //创建触发器Trigger实例(立即执行，每隔1S执行一次)
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger-" + jobName, "trigger-" + jobGroup)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0).withIntervalInSeconds(1))
                .build();
        //开始执行
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

}
