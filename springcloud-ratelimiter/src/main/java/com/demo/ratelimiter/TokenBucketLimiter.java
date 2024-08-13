package com.demo.ratelimiter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 限流实现-令牌桶
 * @author xfenggeng
 * @date 2024-08-10 12:46
 */
public class TokenBucketLimiter {

    //桶的容量
    private long capacity;
    //放入令牌的速率,单位秒
    private long rate;
    //上次放置令牌的时间
    private long lastTime;
    //桶中令牌的余量
    private AtomicLong tokenNum;

    public TokenBucketLimiter(long capacity, long rate){
        this.capacity = capacity;
        this.rate = rate;
        this.lastTime = System.currentTimeMillis();
        this.tokenNum = new AtomicLong();
    }

    /**
     * true 代表放行，请求可已通过
     * false 代表限制，不让请求通过
     */
    public synchronized boolean tryAcquire() {
        //更新桶中剩余令牌的数量
        long now = System.currentTimeMillis();
        tokenNum.addAndGet((now - lastTime) / 1000 * rate);
        tokenNum.set(Math.min(capacity, tokenNum.get()));
        //更新时间
        lastTime += (now - lastTime) / 1000 * 1000;
        //桶中还有令牌就放行
        if (tokenNum.get() > 0) {
            tokenNum.decrementAndGet();
            return true;
        } else {
            return false;
        }
    }


    //测试
    public static void main(String[] args) throws InterruptedException {
        // 线程数
        int threads = 10;
        // 初始化令牌桶
        TokenBucketLimiter tokenBucketLimiter = new TokenBucketLimiter(10,1);
        // 被限制的次数
        AtomicInteger limited = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            Thread.sleep(200);
            if (tokenBucketLimiter.tryAcquire()) {
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
