package cn.regexp.coding.trainee.pattern.adapter.object;

/**
 * @author Regexpei
 * @date 2024/7/15 22:06
 * @description 旧库存系统实现类
 * @since 1.0.0
 */
public class LegacyInventorySystemImpl implements LegacyInventorySystem {
    @Override
    public String checkStock(String productId) {
        if ("123".equals(productId)) {
            return "OutOfStock";
        }
        // 有货
        return "InStock";
    }
}
