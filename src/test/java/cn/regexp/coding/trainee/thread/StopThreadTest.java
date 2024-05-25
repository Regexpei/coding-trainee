package cn.regexp.coding.trainee.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Regexpei
 * @date 2024/4/13 18:20
 * @description 测试停止线程执行的方式
 */
public class StopThreadTest {

    private static volatile boolean stopRequested = false;

    @Test
    public void testFlag() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int count = 0;
            while (!stopRequested) {
                System.out.println(++count + "-Hello world!");
            }
            System.out.println("stop");
        });

        thread.start();
        Thread.sleep(1);

        stopRequested = true;
        // 标志位退出
    }

    @Test
    public void testInterrupted() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(++count + "-Hello world!");
            }
            System.out.println("stop");
        });

        thread.start();
        Thread.sleep(1);

        thread.interrupt();
        // interrupt 中断
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Test
    public void testThreadPool() throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            int count = 0;
            while (true) {
                System.out.println(++count + "-Hello world!");
            }
        });
        Thread.sleep(1);

        future.cancel(true);
        System.out.println("future.isCancelled() = " + future.isCancelled());
        // 使用 Future 和 ExecutorService 进行取消
    }
}
