package cn.regexp.coding.trainee.client;

import cn.regexp.coding.trainee.enums.QosEnum;
import cn.regexp.coding.trainee.properties.MqttProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Regexpei
 * @date 2024/8/5 22:50
 * @description EMQ 客户端
 * @since 1.0.0
 */
@Slf4j
@Component
public class EmqClient {

    @Autowired
    private MqttProperties mqttProperties;
    @Autowired
    private MqttCallback mqttCallback;
    private IMqttClient mqttClient;

    /**
     * 在构造函数加载后初始化 MQTT 客户端
     */
    @PostConstruct
    public void init() {
        // 尝试使用内存持久化方式创建 MqttClient
        try (MqttClientPersistence memoryPersistence = new MemoryPersistence()) {
            mqttClient = new MqttClient(mqttProperties.getBrokerUrl(), mqttProperties.getClientId(), memoryPersistence);
        } catch (MqttException e) {
            log.error("EMQ 客户端初始化失败, brokerUrl: {}, clientId: {}, message: {}",
                    mqttProperties.getBrokerUrl(), mqttProperties.getClientId(), e.getMessage(), e);
        }
    }


    /**
     * 建立连接
     *
     * @param username 用户名
     * @param password 密码
     */
    public void connect(String username, String password) {
        try {
            // 创建MQTT连接选项实例
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置自动重新连接
            options.setAutomaticReconnect(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            // 设置为 true，表示客户端在断开连接时清除会话状态
            options.setCleanSession(true);
            // 设置回调，以便在连接事件发生时能够及时处理
            mqttClient.setCallback(mqttCallback);
            // 尝试连接到EMQ服务器
            mqttClient.connect();
        } catch (MqttException e) {
            log.error("EMQ 客户端连接失败, brokerUrl: {}, clientId: {}, message: {}",
                    mqttProperties.getBrokerUrl(), mqttProperties.getClientId(), e.getMessage(), e);
        }
    }

    /**
     * 断开连接
     * <p>
     * {@code @PreDestroy 注解，标识该方法应在应用程序上下文销毁之前被调用，主要用于清理资源，确保应用程序在退出前释放持有的资源}
     */
    @PreDestroy
    public void disconnect() {
        try {
            mqttClient.disconnect();
        } catch (MqttException e) {
            log.error("EMQ 客户端断开连接失败, brokerUrl: {}, clientId: {}, message: {}",
                    mqttProperties.getBrokerUrl(), mqttProperties.getClientId(), e.getMessage(), e);
        }
    }

    /**
     * 重新连接
     */
    public void reconnect() {
        try {
            mqttClient.reconnect();
        } catch (MqttException e) {
            log.error("EMQ 客户端重连失败, brokerUrl: {}, clientId: {}, message: {}",
                    mqttProperties.getBrokerUrl(), mqttProperties.getClientId(), e.getMessage(), e);
        }
    }

    /**
     * 发布消息
     *
     * @param topic    主题
     * @param payload  消息体
     * @param qos      消息质量
     * @param retained 是否保留消息
     */
    public void publish(String topic, String payload, QosEnum qos, boolean retained) {
        try {
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(qos.getValue());
            message.setRetained(retained);
            mqttClient.publish(topic, message);
        } catch (MqttException e) {
            log.error("EMQ 客户端发布失败, topic: {}, payload: {}, qos: {}, retained: {}, message: {}",
                    topic, payload, qos.getValue(), retained, e.getMessage(), e);
        }
    }

    /**
     * 订阅主题
     *
     * @param topic 主题
     * @param qos   消息质量
     */
    public void subscribe(String topic, QosEnum qos) {
        try {
            mqttClient.subscribe(topic, qos.getValue());
        } catch (MqttException e) {
            log.error("EMQ 客户端订阅失败, topic: {}, qos: {}, message: {}", topic, qos.getValue(), e.getMessage(), e);
        }
    }

    /**
     * 取消订阅主题
     *
     * @param topic 主题
     */
    public void unsubscribe(String topic) {
        try {
            mqttClient.unsubscribe(topic);
        } catch (MqttException e) {
            log.error("EMQ 客户端取消订阅失败, topic: {}, message: {}", topic, e.getMessage(), e);
        }
    }
}
