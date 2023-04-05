package com.big.data.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

@DisallowConcurrentExecution
public class QuartzJob implements Job {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private void before() {
        System.out.println("周期执行：开始执行-" + dateFormat.format(new Date()));
    }

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        before();
        System.out.println("周期执行：业务逻辑。。。");
        after();
    }

    private void after() {
        System.out.println("周期执行：执行结束");
        System.out.println();
    }

}
