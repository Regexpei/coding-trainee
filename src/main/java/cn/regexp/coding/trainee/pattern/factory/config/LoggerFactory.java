package cn.regexp.coding.trainee.pattern.factory.config;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Regexpei
 * @date 2024/6/23 20:31
 * @description 日志工厂类，负责根据配置创建并返回相应的日志实例，使用单例模式和静态方法提供全局唯一的工厂实例。
 */
@Slf4j
public class LoggerFactory {

    /**
     * 存储日志实例，键为日志类型，值为日志实例
     */
    private static final Map<String, Logger> LOGGER_MAP = new HashMap<>();

    /*
      加载日志配置并根据配置创建日志实例
     */
    static {
        Properties props = new Properties();

        // 通过类加载器获取输入流
        try (InputStream is = LoggerFactory.class.getClassLoader().getResourceAsStream("logger-config.properties")) {
            // 加载配置文件
            props.load(is);

            // 遍历配置文件，根据配置创建日志实例
            for (String key : props.stringPropertyNames()) {
                // 获取类的全限定名
                String className = props.getProperty(key);
                // 创建类实例
                Logger logger = (Logger) Class.forName(className).newInstance();
                LOGGER_MAP.put(key, logger);
            }
        } catch (Exception e) {
            log.error("Failed to load logger config file, message: {}", e.getMessage(), e);
        }
    }

    private LoggerFactory() {}

    public static Logger getLogger(String loggerType) {
        return LOGGER_MAP.get(loggerType);
    }
}
