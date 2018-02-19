package com.nzb;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author M
 * @create 2018/2/16
 */
public class UseThreadPool {
    static class MyTask implements Runnable {

        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            Random r = new Random();
            try {
                Thread.sleep(r.nextInt(1000) + 2000);
            } catch (InterruptedException e) {
                System.out.println();
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        for (int i = 0; i <= 5; i++) {
            MyTask task = new MyTask("Task_" + i);
            System.out.println("A new task will add: " + task.getName());
            threadPoolExecutor.execute(task);
        }
        threadPoolExecutor.shutdown();
    }
}
