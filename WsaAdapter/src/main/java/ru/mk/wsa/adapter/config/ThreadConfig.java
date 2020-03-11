package ru.mk.wsa.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadConfig {

    private final WsaPropertiesList wsaPropertiesList;

    public ThreadConfig(WsaPropertiesList wsaPropertiesList) {
        this.wsaPropertiesList = wsaPropertiesList;
    }

    @Bean
    public TaskExecutor redisThreadPoolExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(wsaPropertiesList.getThreadPoolSize()*2);
        executor.setCorePoolSize(wsaPropertiesList.getThreadPoolSize()*2);
        executor.setThreadNamePrefix("red_exec_");
        executor.initialize();
        return executor;
    }

    @Bean
    public ScheduledThreadPoolExecutor timeoutBreaker() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(wsaPropertiesList.getThreadPoolSize());
        return executor;
    }
}
