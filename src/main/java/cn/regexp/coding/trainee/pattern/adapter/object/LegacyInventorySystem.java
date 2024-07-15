package cn.regexp.coding.trainee.pattern.adapter.object;

/**
 * @author Regexpei
 * @date 2024/7/15 22:04
 * @description 旧库存系统
 * @since 1.0.0
 */
public interface LegacyInventorySystem {

    /**
     * 检查库存
     *
     * @param productId 商品ID
     * @return 库存情况
     */
    String checkStock(String productId);

}
