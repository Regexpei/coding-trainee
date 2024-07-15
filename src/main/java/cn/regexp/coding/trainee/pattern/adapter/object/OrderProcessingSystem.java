package cn.regexp.coding.trainee.pattern.adapter.object;

/**
 * @author Regexpei
 * @date 2024/7/15 22:05
 * @description 新的订单处理系统
 * @since 1.0.0
 */
public interface OrderProcessingSystem {
    /**
     * 检查订单是否可以处理
     *
     * @param productId 商品ID
     * @return 是否可以处理
     */
    boolean canProcessOrder(String productId);
}
