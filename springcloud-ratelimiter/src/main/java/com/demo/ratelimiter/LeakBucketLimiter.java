package com.demo.ratelimiter;

import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 限流实现-漏桶
 * @author xfenggeng
 * @date 2024-08-10 12:46
 */
@Data
public class LeakBucketLimiter {

    //桶的大小
    private long capacity;
    //流出速率,每秒流出数
    private long rate;
    //开始时间
    private long startTime;
    //桶中剩余的水
    private AtomicLong water;

    public LeakBucketLimiter(long capacity, long rate){
        this.capacity = capacity;
        this.rate = rate;
        this.startTime = System.currentTimeMillis();
        this.water = new AtomicLong();
    }


    /**
     * true 代表放行，请求可已通过
     * false 代表限制，不让请求通过
     */
    public synchronized boolean tryAcquire() {
        //如果桶的余量问0，直接放行
        if (water.get() == 0) {
            startTime = System.currentTimeMillis();
            water.set(1);
            return true;
        }
        //计算从当前时间到开始时间流出的水，和现在桶中剩余的水
        //桶中剩余的水
        water.set(water.get() - (System.currentTimeMillis() - startTime) / 1000 * rate);
        //防止出现<0的情况
        water.set(Math.max(0, water.get()));
        //设置新的开始时间
        startTime += (System.currentTimeMillis() - startTime) / 1000 * 1000;
        //如果当前水小于容量，表示可以放行
        if (water.get() < capacity) {
            water.incrementAndGet();
            return true;
        } else {
            return false;
        }
    }


    // 测试
    public static void main(String[] args) throws InterruptedException {
        // 请求数
        final int threads = 10;
        // 初始化漏桶
        LeakBucketLimiter leakBucketLimiter = new LeakBucketLimiter(5,1);
        // 被限制的次数
        AtomicInteger limited = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            if (leakBucketLimiter.tryAcquire()) {
                // TODO 处理业务
                System.out.println("处理业务...");
            } else {
                // 被限制的次数累积
                limited.getAndIncrement();
                System.out.println("系统繁忙，请稍后重试...");
            }
            Thread.sleep(200);
        }

        float time = (System.currentTimeMillis() - start) / 1000F;
        //输出统计结果
        System.out.println("限制的次数为：" + limited.get() + ",通过的次数为：" + (threads-limited.get()));
        System.out.println("限制的比例为：" + (float) limited.get() / (float)threads);
        System.out.println("运行的时长为：" + time + "s");
    }

}
