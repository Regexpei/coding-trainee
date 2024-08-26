package cn.regexp.coding.trainee.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Regexpei
 * @date 2024/8/24 12:50
 * @description 线程池配置
 * @since 1.0.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "thread.pool")
public class ThreadPoolConfig {


    /**
     * 线程池配置
     */
    private List<int[]> config;

}
