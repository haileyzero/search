package com.hailey.search.api.config.redis;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@RequiredArgsConstructor
@Configuration
public class EmbeddedRedisConfig {
    private final RedisProperties redisProperties;
    private RedisServer redisServer;

    @PostConstruct
    public void RedisServer() {
        redisServer = new RedisServer(redisProperties.getPort());
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if(redisServer != null) {
            redisServer.stop();
        }
    }
}
