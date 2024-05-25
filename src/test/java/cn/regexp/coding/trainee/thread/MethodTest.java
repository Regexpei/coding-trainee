package cn.regexp.coding.trainee.thread;

import org.junit.Test;

public class MethodTest {

    private static final Object lock = new Object();

    @Test
    public void testJoin() {
        // 创建线程对象
        Thread t1 = new Thread(() -> System.out.println("t1"));

        Thread t2 = new Thread(() -> {
            try {
                // 加入线程t1,只有t1线程执行完毕以后，再次执行该线程
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t2");
        });

        Thread t3 = new Thread(() -> {
            try {
                // 加入线程t2,只有t2线程执行完毕以后，再次执行该线程
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t3");
        });

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }

    @Test
    public void testNotify() {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "...waiting...");
                try {
                    Thread.sleep(1500);
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "...flag is true");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "...waiting...");
                try {
                    Thread.sleep(1000);
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "...flag is true");
            }
        });

        t1.start();
        t2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (lock) {
            // lock.notify();
            lock.notifyAll();
        }
    }

    @Test
    public void testGetCpuCoreNum() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
