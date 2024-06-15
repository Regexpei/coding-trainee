package cn.regexp.coding.trainee.principles;

import cn.regexp.coding.trainee.common.Constants;
import cn.regexp.coding.trainee.principles.ocp.PaymentService;
import cn.regexp.coding.trainee.principles.ocp.PaymentServiceRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Regexpei
 * @date 2024/6/15 21:07
 * @description 开闭原则测试
 */
@SpringBootTest
class OCPTest {


    @Autowired
    private PaymentServiceRegistry paymentServiceRegistry;

    @Test
    public void test() {
        PaymentService payment = paymentServiceRegistry.getPaymentService(Constants.PaymentType.ALI_PAY);
        payment.pay();
    }

}