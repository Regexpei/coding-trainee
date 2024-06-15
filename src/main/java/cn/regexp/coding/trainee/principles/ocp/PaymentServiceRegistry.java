package cn.regexp.coding.trainee.principles.ocp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Regexpei
 * @date 2024/6/15 21:12
 * @description 支付实现类注册
 */
@Service
public class PaymentServiceRegistry {

    /**
     * key：支付方式
     * value：支付方式对应的实现类
     */
    private final Map<String, PaymentService> paymentMap;

    @Autowired
    public PaymentServiceRegistry(Map<String, PaymentService> paymentMap) {
        this.paymentMap = paymentMap;
    }

    public PaymentService getPaymentService(String type) {
        return paymentMap.get(type);
    }
}
