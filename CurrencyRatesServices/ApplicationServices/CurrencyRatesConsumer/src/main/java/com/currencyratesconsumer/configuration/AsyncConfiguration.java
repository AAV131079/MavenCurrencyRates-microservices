package com.currencyratesconsumer.configuration;

import com.enumerators.ProviderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.concurrent.Executor;

@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

    @Value("${spring.task.scheduling.pool.size}")
    private int maxPoolSize;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize((int) Arrays.stream(ProviderEnum.values()).count());
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(maxPoolSize);
        threadPoolTaskExecutor.setThreadNamePrefix("CRExecutor-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        log.info("{} class exception", AsyncConfiguration.class.getName());
        return (throwable, method, objects) -> {
            log.info("Exception message - {}", throwable.getMessage());
            log.info("Method name - {}", method.getName());
            for (Object param : objects) {
                log.info("Parameter value - {}", param);
            }
        };
    }

}