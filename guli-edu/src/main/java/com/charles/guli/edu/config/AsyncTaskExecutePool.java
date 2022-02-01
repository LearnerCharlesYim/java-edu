package com.charles.guli.edu.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@Slf4j
public class AsyncTaskExecutePool {

    @Bean
    @Primary
    public ThreadPoolTaskExecutor asyncServiceExecutor() {
        log.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 通过Runtime方法来获取当前服务器cpu内核，根据cpu内核来创建核心线程数和最大线程数
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        /**
         * 配置线程个数
         如果是CPU密集型任务，那么线程池的线程个数应该尽量少一些，一般为CPU的个数+1条线程(大量计算)
         如果是IO密集型任务，那么线程池的线程可以放的很大，如2*CPU的个数(IO操作)
         */
        executor.setCorePoolSize(availableProcessors * 2);
        // 允许线程池超时
        executor.setAllowCoreThreadTimeOut(true);
        //配置最大线程数
        executor.setMaxPoolSize(availableProcessors * 4);
        // 空闲存活时间
        executor.setKeepAliveSeconds(60);
        // 设置 等待终止秒数
        executor.setAwaitTerminationSeconds(60);
        //配置队列大小
        executor.setQueueCapacity(availableProcessors * 100);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-task-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
