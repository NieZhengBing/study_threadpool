package com.nzb.future;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author M
 * @create 2018/2/19
 */
public class FutureSample {
    public static void main(String[] args) {
        FutureSample futureSample = new FutureSample();
        ArrayList<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
//         ArrayList<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            FutureTask<Integer> ft = new FutureTask<>(new ComputeTask(i, "task_" + i));
            taskList.add(ft);
            exec.submit(ft);
//            Future<Integer> result = exec.submit(new ComputeTask(i, "task_" + i));
//            futureList.add(result);
        }
        System.out.println("main thread already submit task, do your job");

        int totalResult = 0;
        for (FutureTask<Integer> ft : taskList) {
            try {
                totalResult = totalResult + ft.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("total = " + totalResult);
        exec.shutdown();
    }
}
