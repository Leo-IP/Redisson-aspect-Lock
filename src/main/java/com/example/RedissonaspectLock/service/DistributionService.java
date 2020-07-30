package com.example.RedissonaspectLock.service;

import com.example.RedissonaspectLock.common.annotation.DistributedLock;
import com.example.RedissonaspectLock.domain.Product;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistributionService {

    @Autowired
    private RedissonClient redissonClient;

    @DistributedLock(param = "id", lockNamePost = ".lock")
    public Integer aspect(Product product) {
        return getInteger();
    }

    @DistributedLock(argNum = 1, lockNamePost = ".lock")
    public Integer aspect(String i) {
        return getInteger();
    }

    private Integer getInteger() {
        RMap<String, Integer> map = redissonClient.getMap("distributionTest");

        Integer count = map.get("count");

        if (count > 0) {
            count = count - 1;
            map.put("count", count);
        }

        return count;
    }

}