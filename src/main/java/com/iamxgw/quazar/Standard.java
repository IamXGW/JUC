package com.iamxgw.quazar;

import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @description: 模拟普通线程调用微服务
 * @author: IamXGW
 * @create: 2023-12-23 14:58
 */
public class Standard {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(10000);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        ExecutorService executorService = Executors.newFixedThreadPool(200);
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0, 10000).forEach(i -> executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count.countDown();
        }));
        count.await();
        stopWatch.stop();
        System.out.println("STOP, " + stopWatch.prettyPrint());
        executorService.shutdownNow();
    }
}