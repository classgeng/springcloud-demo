package com.demo.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 谷歌Guava限流-令牌桶实现
 */
public class GuavaRateLimiter {

    /**
     * 令牌桶
     */
    private RateLimiter rateLimiter;

    public GuavaRateLimiter(double permitsPerSecond){
        rateLimiter = RateLimiter.create(permitsPerSecond);
    }

    /**
     * 尝试获取令牌
     * @return
     */
    public boolean tryAcquire(){
        return rateLimiter.tryAcquire();
    }


    public static void main(String[] args) throws InterruptedException {
        // 线程数
        int threads = 10;
        // 初始化令牌器
        GuavaRateLimiter guavaRateLimiter = new GuavaRateLimiter(1);
        // 被限制的次数
        AtomicInteger limited = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            Thread.sleep(200);
            if (guavaRateLimiter.tryAcquire()) {
                // TODO 处理业务
                System.out.println("处理业务...");
            } else {
                // 被限制的次数累计
                limited.getAndIncrement();
                System.out.println("系统繁忙，请稍后重试...");
            }
        }

        float time = (System.currentTimeMillis() - start) / 1000F;
        //输出统计结果
        System.out.println("限制的次数为：" + limited.get() + ",通过的次数为：" + (threads - limited.get()));
        System.out.println("限制的比例为：" + (float) limited.get() / (float) threads);
        System.out.println("运行的时长为：" + time + "s");

    }

}
