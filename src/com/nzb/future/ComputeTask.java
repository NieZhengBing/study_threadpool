package com.nzb.future;

import java.util.concurrent.Callable;

/**
 * @author M
 * @create 2018/2/19
 */
public class ComputeTask implements Callable<Integer> {
    private Integer result = 0;
    private String taskName = "";

    public ComputeTask(Integer result, String taskName) {
        this.result = result;
        this.taskName = taskName;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 100; i++) {
            result = result + i;
        }
        Thread.sleep(2000);
        System.out.println(taskName + " child task already completed");
        return result;
    }
}
