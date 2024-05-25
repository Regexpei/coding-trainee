package cn.regexp.coding.trainee.decorator;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

/**
 * @author Regexpei
 * @date 2024/5/19 22:36
 * @description MDC 装饰器
 * TaskDecorator：允许用户自定义由 ThreadPoolTaskExecutor 或其他任务执行器管理的任务的装饰行为。
 */
public class MDCDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        // 获取主线程 MDC 上下文
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            try {
                // 将主线程 MDC 添加到子线程
                MDC.setContextMap(contextMap);
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
