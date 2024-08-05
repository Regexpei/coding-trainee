package cn.regexp.coding.trainee.enums;

import lombok.Getter;

/**
 * @author Regexpei
 * @date 2024/8/5 23:17
 * @description 消息服务质量枚举值
 * @since 1.0.0
 */
@Getter
public enum QosEnum {

    /**
     * 至多一次
     */
    QOS0(0),

    /**
     * 至少一次
     */
    QOS1(1),

    /**
     * 只有一次
     */
    QOS2(2);

    private final int value;

    QosEnum(int value) {
        this.value = value;
    }

}
