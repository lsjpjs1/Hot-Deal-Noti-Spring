package com.example.hotdealnoti.config;

import com.example.hotdealnoti.common.converter.BytesToTimestampConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.Arrays;

@Configuration
@EnableRedisRepositories(basePackages = "com.example.hotdealnoti.repository.redis")
@EnableJpaRepositories(basePackages = "com.example.hotdealnoti.repository.jpa")
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisCustomConversions redisCustomConversions(BytesToTimestampConverter bytesToTimestampConverter){
        return new RedisCustomConversions(Arrays.asList(bytesToTimestampConverter));
    }
}
