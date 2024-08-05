package cn.regexp.coding.trainee.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Regexpei
 * @date 2024/8/5 22:50
 * @description mqtt 配置
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    private String brokerUrl;

    private String clientId;

    private String username;

    private String password;

}
