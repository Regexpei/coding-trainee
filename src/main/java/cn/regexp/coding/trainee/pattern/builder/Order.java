package cn.regexp.coding.trainee.pattern.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Regexpei
 * @date 2024/6/29 17:03
 * @description 订单类
 */
@Getter
@Setter
@ToString
public class Order {

    /**
     * 订单项
     */
    private final List<OrderItem> orderItems;

    /**
     * 收货地址
     */
    private final ShippingAddress shippingAddress;

    /**
     * 支付方式
     */
    private final PaymentMethod paymentMethod;

    private Order(Builder builder) {
        this.orderItems = builder.orderItems;
        this.shippingAddress = builder.shippingAddress;
        this.paymentMethod = builder.paymentMethod;
    }

    public static class Builder {
        private final List<OrderItem> orderItems = new ArrayList<>();
        private ShippingAddress shippingAddress;
        private PaymentMethod paymentMethod;

        public Builder addOrderItem(OrderItem orderItem) {
            orderItems.add(orderItem);
            return this;
        }

        public Builder setShippingAddress(ShippingAddress shippingAddress) {
            this.shippingAddress = shippingAddress;
            return this;
        }

        public Builder setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    /**
     * 订单项类
     */
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class OrderItem {
        /**
         * 商品
         */
        private Product product;
        /**
         * 数量
         */
        private int quantity;
    }

    /**
     * 收货地址类
     */
    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    public static class ShippingAddress {
        /**
         * 国家
         */
        private String country;

        /**
         * 地址状态
         */
        private Integer status;
    }

    /**
     * 支付方式类
     */
    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    public static class PaymentMethod {
        /**
         * 支付方式
         */
        private String type;

        /**
         * 支付备注
         */
        private String remark;
    }
}
