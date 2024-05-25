package cn.regexp.coding.trainee.jvm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Regexpei
 * @date 2024/4/27 17:16
 * @description JVM 内存结构
 */
public class MemoryStructureTest extends ClassLoader {

    /**
     * 测试栈内存溢出
     */
    @SuppressWarnings("InfiniteRecursion")
    @Test
    public void testStackOverflowError() {
        testStackOverflowError();
    }

    /**
     * 测试堆内存溢出
     * VM options: -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
     */
    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "InfiniteLoopStatement"})
    @Test
    public void testOutOfMemoryError() {
        List<Object> list = new ArrayList<>();
        int count = 0;
        while (true) {
            list.add(new Byte[1024 * 1024]);
            System.out.println("created " + ++count + "对象");
        }
    }
}
