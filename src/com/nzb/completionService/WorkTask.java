package com.nzb.completionService;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author M
 * @create 2018/2/19
 */
public class WorkTask implements Callable<String> {
    private String name;

    public WorkTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        int sleepTime = new Random().nextInt(1000);
        Thread.sleep(sleepTime);
        String str = name + " sleep time: " + sleepTime;
        System.out.println(str + " finished...");
        return str;
    }

}
