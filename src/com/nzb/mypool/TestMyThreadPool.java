package com.nzb.mypool;

import java.util.Random;

/**
 * @author M
 * @create 2018/2/17
 */
public class TestMyThreadPool {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool t = new MyThreadPool(3);
        t.execute(new MyTask("testA"));
        t.execute(new MyTask("testB"));
        t.execute(new MyTask("testC"));
        t.execute(new MyTask("testD"));
        t.execute(new MyTask("testE"));

        System.out.println(t);
        Thread.sleep(3000);
        t.destroy();
        System.out.println(t);
    }

    static class MyTask implements Runnable {

        private String name;
        private Random r = new Random();

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(1000) + 2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId() + " sleep InterruptedException: " + Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }

            System.out.println("task " + name + "completed");
        }
    }
}
