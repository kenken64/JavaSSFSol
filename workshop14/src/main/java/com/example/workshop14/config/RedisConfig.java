package com.example.workshop14.config;

import com.example.workshop14.model.Contact;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;


// Configuration class to set up the Redis configuration.
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
 
    @Bean
    public RedisTemplate<String, Contact> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Contact> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
