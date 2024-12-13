package com.demo.common.thread;

import com.demo.common.domain.UserInfo;
import com.demo.common.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class CompletableFutureTest {

    public static final Integer deductSize = 50;
    private static final int MAX_RETRIES = 3;           // 最大重试次数

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testFutureRetries();
    }


    public static void testFuture() throws ExecutionException, InterruptedException {
        Integer totalSize = 30000;
        totalSize = totalSize > 30000 ? 30000 : totalSize; // 最大支持导出30000条，再多内存就会爆了！
        int totalPage = (totalSize + 1000 - 1) / 1000;  //每页查询1000条，计算一共有多少页

        List<CompletableFuture<List<UserInfo>>> futureList = new ArrayList<>();
        //异步分页查询开始
        long currentTimeMillis = System.currentTimeMillis();
        for (int pageIndex=1; pageIndex <= totalPage; pageIndex++) {
            log.info("一共{}条记录，每页查询{}条，第{}页查询开始...", totalSize, 1000, pageIndex);
            //异步发送请求
            CompletableFuture<List<UserInfo>> future = CompletableFuture.supplyAsync(() -> {
                        try {
                            return queryUserInfoList();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    ThreadPoolExecutorConfig.executor()).exceptionally(throwable -> {
                log.error("completable future query invoice error", throwable);
                throw new RuntimeException("completable future query invoice error");
            });
            futureList.add(future);
        }
        CompletableFuture<Void> futures = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
        try {
            futures.join(); // 等待所有任务完成，并获取它们的结果
        } catch (Exception e) {
            log.error("completable future join invoice error", e);
            throw new RuntimeException("completable future join invoice error");
        }
        Double time = (System.currentTimeMillis()-currentTimeMillis) / 1000d;
        log.info("全部查询结束耗时: {}", time);

        List<UserInfo> invoiceList = new ArrayList<>();
        for (CompletableFuture<List<UserInfo>> future : futureList) {
            invoiceList.addAll(future.get());
        }
        log.info("实际查询记录条数: {}", invoiceList.size());
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


    public static List<UserInfo> queryUserInfoList() throws InterruptedException {
        Thread.sleep(3000);
        List<UserInfo> userInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userInfoList.add(new UserInfo(UUIDUtils.getUUID(),"demo", 18,null));
        }
        log.info("queryUserInfoList size:{}", userInfoList.size());

        //根据租户并发执行
        long currentTimeMillis = System.currentTimeMillis();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (UserInfo userInfo : userInfoList) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(userInfo.getId());
                    },
                    ThreadPoolExecutorConfig.executor()).exceptionally(throwable -> {
                log.error("completable future upload invoice doc error", throwable);
                throw new RuntimeException("completable future upload invoice doc error");
            });
            futures.add(future);
        }
        // 等待所有CompletableFuture任务完成
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        double time = (System.currentTimeMillis() - currentTimeMillis) / 1000d;
        log.info("uploadInvoiceDocHandler success，耗时={}", time);

        return userInfoList;
    }


    public static void testFutureRetries() {
        CompletableFuture.runAsync(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
                try {
                    TimeUnit.SECONDS.sleep((long) Math.pow(2, attempt)); // 指数退避
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was interrupted");
                }
                System.out.println("第" + attempt + "次重试");
            }
            double time = (System.currentTimeMillis() - currentTimeMillis) / 1000d;
            System.out.println("耗时=" + time);
        }, ThreadPoolExecutorConfig.executor());

    }




}
