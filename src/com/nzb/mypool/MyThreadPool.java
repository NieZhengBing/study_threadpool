package com.nzb.mypool;

import java.util.LinkedList;
import java.util.List;

/**
 * @author M
 * @create 2018/2/17
 */
public class MyThreadPool {
    private int work_num = 5;
    private WorkThread[] workThreads;
    private List<Runnable> taskQueue = new LinkedList<Runnable>();

    public MyThreadPool(int work_num) {
        this.work_num = work_num;
        workThreads = new WorkThread[work_num];
        for (int i = 0; i < work_num; i++) {
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void destroy() {
        System.out.println("ready stop pool......");
        for (int i = 0; i < work_num; i++) {
            workThreads[i].stopWorker();
            workThreads[i] = null;
        }
        taskQueue.clear();
    }

    private class WorkThread extends Thread {
        private volatile boolean on = true;

        @Override
        public void run() {
            Runnable r = null;
            try {
                while (on && !isInterrupted()) {
                    synchronized (taskQueue) {
                        while (on && !isInterrupted() && taskQueue.isEmpty()) {
                            taskQueue.wait(1000);
                        }
                        if (on && !isInterrupted() && !taskQueue.isEmpty()) {
                            r = taskQueue.remove(0);
                        }
                    }
                }
                if (r != null) {
                    System.out.println(getId() + " ready execute...");
                    r.run();
                }
                r = null;
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId() + " is Interrupted");
            }
        }

        public void stopWorker() {
            on = false;
            interrupt();
        }
    }
}
