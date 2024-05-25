package cn.regexp.coding.trainee.task;

import org.slf4j.MDC;

import java.util.Map;

/**
 * @author Regexpei
 * @date 2024/5/23 22:58
 * @description MDC 任务线程
 */
public class MDCRunnable implements Runnable {

    private final Runnable runnable;

    private final Map<String, String> contextMap;

    public MDCRunnable(Runnable runnable) {
        this.runnable = runnable;
        this.contextMap = MDC.getCopyOfContextMap();
    }

    @Override
    public void run() {
        MDC.setContextMap(contextMap);
        runnable.run();
        MDC.clear();
    }
}
