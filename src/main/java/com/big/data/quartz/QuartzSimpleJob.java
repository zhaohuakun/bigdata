package com.big.data.quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@DisallowConcurrentExecution
public class QuartzSimpleJob implements Job {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private void before(String jobName) {
        System.out.println("手工执行-任务：开始执行-" + jobName + "|" + dateFormat.format(new Date()));
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        JobDetail detail = arg0.getJobDetail();
        before(detail.getKey().getName());
        try {
            Thread.sleep(1000 * 60 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        after(detail.getKey().getName());
    }

    private void after(String jobName) {
        System.out.println("手工执行-：执行结束" + jobName);
        System.out.println();
    }

}
