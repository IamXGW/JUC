package com.iamxgw.quazar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @description: 模拟 Fiber 调用微服务
 * @author: IamXGW
 * @create: 2023-12-23 15:04
 */
public class FiberExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        IntStream.range(0, 10000).forEach(i ->
                new Fiber<String>() {
                    @Override
                    protected String run() throws SuspendExecution, InterruptedException {
                        Strand.sleep(1000);
                        countDownLatch.countDown();
                        return "aa";
                    }
                }.start()
        );
        countDownLatch.await();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}