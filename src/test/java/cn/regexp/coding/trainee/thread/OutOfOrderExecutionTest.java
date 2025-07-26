package cn.regexp.coding.trainee.thread;

/**
 * @author Regexpei
 * @date 2025/7/26 21:39
 * @description 测试指令重排序
 * @since 1.0.0
 */
public class OutOfOrderExecutionTest {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        while (true) {
            count++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            Thread t1 = new Thread(() -> {
                a = 1;  // 指令 1
                x = b;  // 指令 2
            });

            Thread t2 = new Thread(() -> {
                b = 1;  // 指令 3
                y = a;  // 指令 4
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            // 如果 CPU 严格按顺序执行，x 和 y 不可能同时为 0
            if (x == 0 && y == 0) {
                System.out.println("第 " + count + " 次执行时发生重排序 (x=0, y=0)");
                break;
            }
        }
    }
}
