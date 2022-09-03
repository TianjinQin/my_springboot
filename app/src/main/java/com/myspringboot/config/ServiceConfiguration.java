//package com.myspringboot.config;
//
//import com.myspringboot.service.MemoryStorageService;
//import com.myspringboot.service.impl.MemoryStorageServiceRedisImpl;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//@Configuration
//@ComponentScan(basePackages = "com.myspringboot.service.**")
//@PropertySource(value = "classpath:commonConfig.properties")
//public class ServiceConfiguration {
//    @Value("${redis.host.name}")
//    private String redisHostName;
//
//    @Value("${redis.host.port}")
//    private Integer redisHostPort;
//
//    @Value("${redis.password}")
//    private String redisPassword;
//
//    @Bean(name = "memoryStorageServiceRedisImpl", initMethod = "init")
//    public MemoryStorageServiceRedisImpl redisServiceImpl() {
//        MemoryStorageServiceRedisImpl redisImpl = new MemoryStorageServiceRedisImpl();
//        redisImpl.setHost(redisHostName);
//        redisImpl.setPassword(redisPassword);
//        redisImpl.setPort(redisHostPort);
//        return redisImpl;
//    }
//
//}
