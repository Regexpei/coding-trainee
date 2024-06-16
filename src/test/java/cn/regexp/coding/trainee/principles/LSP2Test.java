package cn.regexp.coding.trainee.principles;

import cn.regexp.coding.trainee.principles.lsp.after.Rectangle;
import cn.regexp.coding.trainee.principles.lsp.after.Square;
import org.junit.Test;

/**
 * @author Regexpei
 * @date 2024/6/16 12:26
 * @description 里氏代换原则测试
 */
public class LSP2Test {

    @Test
    public void testAfter() {
        Rectangle rectangle = new Rectangle(10, 5);
        resize(rectangle);
        System.out.println(rectangle);

        Square square = new Square(10);
        // resize(square);
        System.out.println(square);
    }

    /**
     * 扩大宽度到比长度大
     *
     * @param rectangle 长方形
     */
    public static void resize(Rectangle rectangle) {
        // 只有宽度大于长度时，才会退出循环
        while (rectangle.getWidth() <= rectangle.getLength()) {
            rectangle.setWidth(rectangle.getWidth() + 1);
        }
    }
}
