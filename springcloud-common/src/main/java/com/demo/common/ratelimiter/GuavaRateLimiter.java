package com.demo.common.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

public class GuavaRateLimiter {

    private static RateLimiter rateLimiter = RateLimiter.create(10);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("Request start..." + startTime);
        for (int i = 0; i < 100; i++) {
            double dd = rateLimiter.acquire();
            System.out.println("Request " + (i + 1) + " allowed " + dd);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Request end..." + (endTime-startTime));
    }

}
