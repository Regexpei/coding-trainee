package cn.regexp.coding.trainee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Regexpei
 * @date 2024/7/6 13:28
 * @description 线程池配置
 */
@Configuration
public class TaskExecutorConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 线程池核心线程数
        taskExecutor.setCorePoolSize(corePoolSize);
        // 线程池最大线程数
        taskExecutor.setMaxPoolSize(corePoolSize + 5);
        // 线程池队列大小
        taskExecutor.setQueueCapacity(500);
        // 线程池线程空闲时间
        taskExecutor.setKeepAliveSeconds(60);
        // 线程池线程名称前缀
        taskExecutor.setThreadNamePrefix("taskExecutor-");
        // 线程池拒绝策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程池关闭时是否等待任务执行完成
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程池关闭时等待任务执行的超时时间
        taskExecutor.setAwaitTerminationSeconds(120);
        return taskExecutor;
    }
}