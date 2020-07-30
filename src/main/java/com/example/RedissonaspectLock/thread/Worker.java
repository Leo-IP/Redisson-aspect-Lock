package com.example.RedissonaspectLock.thread;

import com.example.RedissonaspectLock.domain.Product;
import com.example.RedissonaspectLock.service.DistributionService;
import org.redisson.api.RedissonClient;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    private final DistributionService service;
    private RedissonClient redissonClient;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal, DistributionService service, RedissonClient redissonClient) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.service = service;
        this.redissonClient = redissonClient;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " start");

            startSignal.await();

            Integer count = service.aspect(new Product(1, "Zurich insurance"));
//            Integer count = service.aspect("lock");

            System.out.println(Thread.currentThread().getName() + ": count = " + count);

            doneSignal.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
