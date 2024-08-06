package cn.regexp.coding.trainee.client;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

/**
 * @author Regexpei
 * @date 2024/8/6 19:47
 * @description 消息回调
 * @since 1.0.0
 */
@Slf4j
@Component
public class MessageCallback implements MqttCallback {

    /**
     * 连接丢失后触发的回调
     *
     * @param cause 连接丢失原因
     */
    @Override
    public void connectionLost(Throwable cause) {
        log.error("连接丢失:{}", cause.getMessage(), cause);
    }

    /**
     * 客户端接收到服务端消息的回调
     *
     * @param topic   消息主题
     * @param message 消息内容
     * @throws Exception 异常
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("接收消息主题:{}, 消息ID:{}, 消息 Qos:{}, 消息内容: {}",
                topic, message.getId(), message.getQos(), new String(message.getPayload()));
    }

    /**
     * 消息发布完成回调
     *
     * @param token 消息发布 token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("消息发布完成, 消息ID:{}, 消息主题:{}", token.getMessageId(), token.getTopics());
    }
}
