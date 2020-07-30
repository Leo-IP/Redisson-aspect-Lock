package com.example.RedissonaspectLock.controller;

import com.example.RedissonaspectLock.service.DistributedLockManager;
import com.example.RedissonaspectLock.service.DistributionService;
import com.example.RedissonaspectLock.thread.Worker;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/distributedLockTest")
public class DistributedLockTestController {

    private int count = 10;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private DistributionService service;

//    @Autowired
//    private DistributedLockManager distributedLockManager;

    @RequestMapping(method = RequestMethod.GET)
    public String distributedLockTest() throws Exception {

        RMap<String, Integer> map = redissonClient.getMap("distributionTest");
        map.put("count", 8);

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(count);

        for (int i = 0; i < count; ++i) { // create and start threads
            new Thread(new Worker(startSignal, doneSignal, service, redissonClient)).start();
//            new Thread(new Worker1(startSignal, doneSignal, distributedLockManager, redissonClient)).start();
        }

        startSignal.countDown(); // let all threads proceed
        doneSignal.await();
        System.out.println("All processors done. Shutdown connection");

        return "finish";
    }

}
