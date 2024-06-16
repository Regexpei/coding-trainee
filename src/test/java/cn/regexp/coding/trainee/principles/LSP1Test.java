package cn.regexp.coding.trainee.principles;

import cn.regexp.coding.trainee.principles.lsp.before.Rectangle;
import cn.regexp.coding.trainee.principles.lsp.before.Square;
import org.junit.Test;

/**
 * @author Regexpei
 * @date 2024/6/16 12:26
 * @description 里氏代换原则测试
 */
public class LSP1Test {

    @Test
    public void testBefore() {
        Rectangle rectangle = new Rectangle(10, 5);
        resize(rectangle);
        System.out.println(rectangle);

        /*
            由于 Square 类重写了 setLength 和 setWidth 方法，导致
            每次修改宽度都会导致长度跟宽度保持一致，所以就会陷入死循环
         */
        Rectangle square = new Square();
        square.setLength(10);
        resize(square);
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
