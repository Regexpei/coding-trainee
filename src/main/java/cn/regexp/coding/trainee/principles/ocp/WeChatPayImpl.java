package cn.regexp.coding.trainee.principles.ocp;

import cn.regexp.coding.trainee.common.Constants;
import org.springframework.stereotype.Service;

/**
 * @author Regexpei
 * @date 2024/6/15 20:48
 * @description 微信支付类
 */
@Service(Constants.PaymentType.WECHAT_PAY)
public class WeChatPayImpl implements PaymentService {

    @Override
    public void pay() {
        System.out.println("通过微信支付");
    }
}

