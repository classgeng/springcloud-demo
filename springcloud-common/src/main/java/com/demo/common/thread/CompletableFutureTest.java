package com.demo.common.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class CompletableFutureTest {

    public static final Integer deductSize = 50;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testTask();
    }


    public static void testTask() {
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(5);

        List<Future<String>> futures = new ArrayList<>();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("11");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "future1";
        }, threadPoolExecutor).exceptionally(throwable -> {
            System.out.println(throwable.getMessage());
            return null;
        });
        futures.add(future1);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            log.info("22");
            try {
                Thread.sleep(2000);
                int num = 5 / 0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "future2";
        }, threadPoolExecutor).exceptionally(throwable -> {
            System.out.println("error: "+throwable.getMessage());
            return null;
        });
        futures.add(future2);

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            log.info("33");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "future3";
        }, threadPoolExecutor).exceptionally(throwable -> {
            System.out.println(throwable.getMessage());
            return null;
        });
        futures.add(future3);

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        // 等待所有CompletableFuture任务完成
        // CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();


    }



}
