package com.imooc.mall.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：     线程池配置
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService getThreadPool() {
        return new ThreadPoolExecutor(10, 20, 0l, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }
}
