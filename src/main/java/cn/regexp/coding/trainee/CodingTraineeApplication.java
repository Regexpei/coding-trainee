package cn.regexp.coding.trainee;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.regexp.coding.trainee.client.EmqClient;
import cn.regexp.coding.trainee.enums.QosEnum;
import cn.regexp.coding.trainee.properties.MqttProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author Regexpei
 * @date 2024/5/21 22:24
 * @description 启动类
 */
@Slf4j
@SpringBootApplication
public class CodingTraineeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodingTraineeApplication.class, args);
        log.info("CodingTraineeApplication startup success!");
    }

    @Autowired
    private EmqClient emqClient;
    @Autowired
    private MqttProperties mqttProperties;

    @PostConstruct
    public void init() {
        emqClient.connect(mqttProperties.getUsername(), mqttProperties.getPassword());
        emqClient.subscribe("test_topic/#", QosEnum.QOS2);
        ThreadUtil.execAsync(() -> {
            while (true) {
                String payload = "hello world, the present time: " + DateUtil.now();
                emqClient.publish("test_topic/123", payload, QosEnum.QOS2, false);
                ThreadUtil.sleep(1000);
            }
        });
    }

}
