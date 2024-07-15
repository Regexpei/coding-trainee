package cn.regexp.coding.trainee.pattern.adapter.object;

/**
 * @author Regexpei
 * @date 2024/7/15 22:08
 * @description 库存适配器
 * @since 1.0.0
 */
public class InventoryAdapter implements OrderProcessingSystem {

    private final LegacyInventorySystem legacyInventorySystem;

    public InventoryAdapter(LegacyInventorySystem legacyInventorySystem) {
        this.legacyInventorySystem = legacyInventorySystem;
    }

    @Override
    public boolean canProcessOrder(String productId) {
        String stockStatus = legacyInventorySystem.checkStock(productId);
        return "InStock".equalsIgnoreCase(stockStatus);
    }
}
