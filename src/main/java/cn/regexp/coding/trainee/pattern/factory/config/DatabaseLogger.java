package cn.regexp.coding.trainee.pattern.factory.config;

/**
 * @author Regexpei
 * @date 2024/6/23 20:30
 * @description 数据库日志实现
 */
public class DatabaseLogger implements Logger {
    @Override
    public void log(String message) {
        // 实际可以将日志记录到数据库中
        System.out.println("Database: " + message);
    }
}
