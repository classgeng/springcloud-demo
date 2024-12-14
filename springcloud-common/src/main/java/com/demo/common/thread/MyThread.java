package com.demo.common.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyThread {

    public static String url = "https://www.baidu.com/";

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*int size = 101;
        int deductSize = 50;
        int cycleIndex = (size % deductSize)==0 ? size / deductSize : size / deductSize + 1;
        System.out.println(cycleIndex);*/

        /*for (int i = 0; i < 10; i++) {
            OkHttpClientHandler.doAsyncGet(url,null,null);
        }*/

        ExecutorService executors = Executors.newFixedThreadPool(5);

        List<Future<Object>> futures = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            RequestTask task = new RequestTask(url, null);
            Future<Object> future = executors.submit(task);
            futures.add(future);
        }
        for (Future<Object> future : futures) {
            System.out.println(future.get());
        }
        System.out.println("+++++++++++++++==全部执行成功！");

        executors.shutdown();

    }

}
