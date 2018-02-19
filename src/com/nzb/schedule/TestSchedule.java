package com.nzb.schedule;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author M
 * @create 2018/2/19
 */
public class TestSchedule {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        exec.scheduleAtFixedRate(new ScheduleTask(ScheduleTask.OpenType.None), 1000, 5000, TimeUnit.MILLISECONDS);
        exec.scheduleAtFixedRate(new ScheduleTask(ScheduleTask.OpenType.OnlyThrowException), 1000, 5000, TimeUnit.MILLISECONDS);

        exec.scheduleAtFixedRate(new ScheduleTask(ScheduleTask.OpenType.CatchException), 1000, 5000, TimeUnit.MILLISECONDS);

        exec.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("scheduleWithFixedDelay: begin" + ScheduleTask.formater.format(new Date()));

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("scheduleWithFixedDelay: end" + ScheduleTask.formater.format(new Date()));
            }
        }, 1000, 5000, TimeUnit.MILLISECONDS);
    }
}
