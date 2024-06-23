package cn.regexp.coding.trainee.pattern.factory.config;

/**
 * @author Regexpei
 * @date 2024/6/23 20:27
 * @description 控制台日志实现
 */
public class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("Console: " + message);
    }
}
