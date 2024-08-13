package com.demo.ratelimiter;

import lombok.Data;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流实现-滑动时间窗口
 * @author xfenggeng
 * @date 2024-08-07 15:16
 */
public class SlidingWindowRateLimiter {

    /**
     * 阈值（每个时间窗口内，限制数量）
     */
    private int limit;
    /**
     * 固定时间窗口（毫秒）
     */
    private long interval;
    /**
     * 多少个子窗口
     */
    private int windowCount = 10;
    /**
     * 窗口列表
     */
    private WindowInfo[] windowArray = new WindowInfo[windowCount];

    public SlidingWindowRateLimiter(int qps, long timeWindows) {
        this.limit = qps;
        this.interval = timeWindows;
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < windowArray.length; i++) {
            windowArray[i] = new WindowInfo(currentTimeMillis, new AtomicInteger(0));
        }
    }

    /**
     * 1. 计算当前时间窗口
     * 2. 更新当前窗口计数 & 重置过期窗口计数
     * 3. 当前 QPS 是否超过限制
     *
     * @return
     */
    public synchronized boolean tryAcquire() {
        long currentTimeMillis = System.currentTimeMillis();
        // 1. 计算当前时间窗口
        int currentIndex = (int)(currentTimeMillis % interval / (interval / windowCount));
        // 2.  更新当前窗口计数 & 重置过期窗口计数
        int sum = 0;
        for (int i = 0; i < windowArray.length; i++) {
            WindowInfo windowInfo = windowArray[i];
            if ((currentTimeMillis - windowInfo.getTime()) > interval) {
                windowInfo.getNumber().set(0);
                windowInfo.setTime(currentTimeMillis);
            }
            if (currentIndex == i && windowInfo.getNumber().get() < limit) {
                windowInfo.getNumber().incrementAndGet();
            }
            sum = sum + windowInfo.getNumber().get();
        }
        // 3. 当前 limit 是否超过限制
        return sum <= limit;
    }

    public static void main(String[] args) throws InterruptedException {
        int limit = 5, count = 10, sleep = 100, success = count * sleep / 1000 * limit;
        System.out.println(String.format("当前limit限制为:%d,当前测试次数:%d,间隔:%dms,预计成功次数:%d", limit, count, sleep, success));
        success = 0;
        SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(limit, 1000);
        for (int i = 0; i < count; i++) {
            Thread.sleep(sleep);
            Date now = new Date();
            if (rateLimiter.tryAcquire()) {
                success++;
                if (success % limit == 0) {
                    System.out.println(now + ": success, ");
                } else {
                    System.out.println(now + ": success, ");
                }
            } else {
                System.out.println(now + ": fail");
            }
        }
        System.out.println("实际测试成功次数:" + success);
    }

    @Data
    class WindowInfo {
        // 窗口开始时间
        private Long time;
        // 计数器
        private AtomicInteger number;

        public WindowInfo(long time, AtomicInteger number) {
            this.time = time;
            this.number = number;
        }
    }

}
