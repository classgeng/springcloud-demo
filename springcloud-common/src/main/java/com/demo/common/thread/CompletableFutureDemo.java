package com.demo.common.thread;

import java.util.concurrent.*;

/**
 * 任务编排
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100));
        // 创建任务1
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello task1";
        }, threadPoolExecutor);
        // 创建任务2
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "hello task2";
        }, threadPoolExecutor);

        //task1、task2全部执行完再往下执行
        CompletableFuture.allOf(task1,task2).join();
        //task1、task2任何一个执行完就往下执行
        // CompletableFuture.anyOf(task1,task2).join();

        System.out.println(task1.get());
        System.out.println(task2.get());

        System.out.println("success!");

        threadPoolExecutor.shutdown();

    }


}
