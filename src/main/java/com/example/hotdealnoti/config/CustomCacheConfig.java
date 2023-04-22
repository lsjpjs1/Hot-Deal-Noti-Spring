package com.example.hotdealnoti.config;

import com.example.hotdealnoti.common.converter.BytesToTimestampConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.Date;

@Configuration
@Slf4j
public class CustomCacheConfig {
    public static final String FIND_HOT_DEALS = "FIND_HOT_DEALS";

    @CacheEvict(allEntries = true, value = {FIND_HOT_DEALS})
    @Scheduled(fixedDelay = 2 * 60 * 1000 )
    public void reportCacheEvict() {
        log.info("Cache clear: " + FIND_HOT_DEALS);
    }
}
