package com.nzb.completionService;

import java.util.concurrent.*;

/**
 * @author M
 * @create 2018/2/19
 */
public class CompletionTest {
    private final int POOL_SIZE = 5;
    private final int TOTAL_TASK = 10;

    public void testByQueue() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        LinkedBlockingDeque<Future<String>> queue = new LinkedBlockingDeque<Future<String>>();

        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<String> future = pool.submit(new WorkTask("ExecTask" + i));
            queue.add(future);
        }

        for (int i = 0; i < TOTAL_TASK; i++) {
            System.out.println("ExecTask: " + queue.take().get());
        }

        pool.shutdown();
    }

    public void testByCompletion() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        ExecutorCompletionService<String> service = new ExecutorCompletionService<>(pool);

        for (int i = 0; i < TOTAL_TASK; i++) {
            service.submit(new WorkTask("ExecTask " + i));
        }

        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<String> future = service.take();
            System.out.println("CompletionService: " + future.get());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletionTest completionTest = new CompletionTest();
        completionTest.testByQueue();
//        completionTest.testByCompletion();
    }

}
