package com.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Configuration class for enabling asynchronous processing.
 * This configuration will set up a thread pool task executor to handle async tasks.
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    /**
     * Creates and configures a ThreadPoolTaskExecutor bean for asynchronous task execution.
     *
     * @return an Executor for handling async tasks
     */
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        // Create a new ThreadPoolTaskExecutor
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // Set the core pool size (minimum number of threads)
        executor.setCorePoolSize(2);

        // Set the maximum pool size (maximum number of threads)
        executor.setMaxPoolSize(2);

        // Set the queue capacity (number of tasks that can be held in the queue before new threads are created)
        executor.setQueueCapacity(500);

        // Set the thread name prefix for easier debugging
        executor.setThreadNamePrefix("EmailSender-");

        // Initialize the executor
        executor.initialize();

        return executor;
    }
}
