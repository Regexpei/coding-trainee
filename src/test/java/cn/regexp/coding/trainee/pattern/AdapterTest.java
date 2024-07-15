package cn.regexp.coding.trainee.pattern;

import cn.regexp.coding.trainee.pattern.adapter.object.InventoryAdapter;
import cn.regexp.coding.trainee.pattern.adapter.object.LegacyInventorySystem;
import cn.regexp.coding.trainee.pattern.adapter.object.LegacyInventorySystemImpl;
import cn.regexp.coding.trainee.pattern.adapter.object.OrderProcessingSystem;
import org.junit.Test;

/**
 * @author Regexpei
 * @date 2024/7/15 22:07
 * @description 适配器模式测试类
 * @since 1.0.0
 */
public class AdapterTest {

    @Test
    public void testSystemDemo() {
        String productId = "321";
        LegacyInventorySystem legacyInventory = new LegacyInventorySystemImpl();
        System.out.println(legacyInventory.checkStock(productId));

        // 通过适配器适配
        OrderProcessingSystem orderProcessing = new InventoryAdapter(legacyInventory);
        if (orderProcessing.canProcessOrder(productId)) {
            System.out.println("订单可以处理，产品ID: " + productId);
        } else {
            System.out.println("订单无法处理，产品ID: " + productId + " 无库存");
        }
    }

}
