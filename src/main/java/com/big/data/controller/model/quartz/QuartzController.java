package com.big.data.controller.model.quartz;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.big.data.quartz.config.QuartzSchedulerManager;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quartz")
public class QuartzController {
    @Autowired
    private QuartzSchedulerManager quartzSchedulerManager;

    // @Description: 获取定时器信息
    @GetMapping("/info")
    public String getQuartzJob(String name, String group) {
        String info = null;
        try {
            info = quartzSchedulerManager.getJobInfo(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return info;
    }

    // @Description: 修改定时器的 执行时间
    @PostMapping("/modify")
    public boolean modifyQuartzJob(String name, String group, String time) {
        boolean flag = true;

        if (!CronExpression.isValidExpression(time)) {
            throw new RuntimeException("非法的cron表达式");
        }

        try {
            flag = quartzSchedulerManager.modifyJob(name, group, time);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // @Description: 启动所有定时器
    @PostMapping("/start")
    public void startQuartzJob(@RequestBody JSONArray jsonArray) {
        try {

            quartzSchedulerManager.startJob(jsonArray);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/start/simple")
    public void startQuartzSimpleJob(@RequestBody JSONObject jsonObject) {
        try {
            quartzSchedulerManager.simpleStartJob(jsonObject);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/start/simple/batch")
    public void startQuartzSimpleBatchJob(@RequestBody JSONObject jsonObject) {
        try {

            String jobName = jsonObject.getString("jobName");
            String jobGroup = jsonObject.getString("jobGroup");
            int batchSize = jsonObject.getIntValue("batchSize");
            for (int i = 0; i < batchSize; i++) {
                JSONObject json = new JSONObject();
                json.put("jobName", jobName + "-00" + i);
                json.put("jobGroup", jobGroup + "-00" + i);
                quartzSchedulerManager.simpleStartJob(json);
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    // @Description: 暂停指定 定时器
    @PostMapping(value = "/pause")
    public void pauseQuartzJob(String name, String group) {
        try {
            quartzSchedulerManager.pauseJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    // 暂停所有定时器
    @PostMapping(value = "/pauseAll")
    public void pauseAllQuartzJob() {
        try {
            quartzSchedulerManager.pauseAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    // 删除指定定时器
    @PostMapping(value = "/delete")
    public void deleteJob(@RequestBody JSONObject jsonObject) {
        try {
            String name = jsonObject.getString("jobName");
            String group = jsonObject.getString("jobGroup");
            quartzSchedulerManager.deleteJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}