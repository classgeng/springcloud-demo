package com.demo.ratelimiter;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流实现-固定时间窗口
 * @author xfenggeng
 * @date 2024-08-07 15:16
 */
public class FixedWindowRateLimiter {

    // 阈值（每个时间窗口内，限制数量）
    private int limit;
    // 固定时间窗口（毫秒）
    private long interval;
    // 开始时间
    private long startTime;
    // 计数器
    private AtomicInteger count;

    public FixedWindowRateLimiter(int limit, long interval){
        this.limit = limit;
        this.interval = interval;
        this.count = new AtomicInteger();
        this.startTime = System.currentTimeMillis();
    }

    /**
     * 是否限流
     * @return
     */
    public synchronized boolean tryAcquire() {
        long nowTime = System.currentTimeMillis();
        if ((nowTime - startTime) > interval) {
            startTime = nowTime;
            count.set(0);
        }
        return count.incrementAndGet() <= limit;
    }

    /**
     * 测试
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        FixedWindowRateLimiter rateLimiter = new FixedWindowRateLimiter(5, 1000);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            Date now = new Date();
            if (rateLimiter.tryAcquire()) {
                // TODO 处理业务
                System.out.println(now + " 处理业务...");
            } else {
                System.out.println(now + " 系统繁忙，请稍后重试...");
            }
        }
    }
}
