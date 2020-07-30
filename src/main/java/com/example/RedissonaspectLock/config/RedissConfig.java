package com.example.RedissonaspectLock.config;

import com.example.RedissonaspectLock.common.lock.DistributedLockTemplate;
import com.example.RedissonaspectLock.common.lock.SingleDistributedLockTemplate;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class RedissConfig {

    @Value("classpath:/redisson-conf.yml")
    Resource configFile;

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson()
            throws IOException {
        Config config = Config.fromYAML(configFile.getInputStream());
        return Redisson.create(config);
    }

    @Bean
    DistributedLockTemplate distributedLockTemplate(RedissonClient redissonClient) {
        return new SingleDistributedLockTemplate(redissonClient);
    }
}
