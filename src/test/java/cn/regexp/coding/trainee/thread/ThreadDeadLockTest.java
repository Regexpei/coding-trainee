package cn.regexp.coding.trainee.thread;

import static java.lang.Thread.sleep;

/**
 * @author Regexpei
 * @date 2024/4/13 21:59
 * @description
 */
public class ThreadDeadLockTest {

    public static final Object A = new Object();
    public static final Object B = new Object();

    public static void main(String[] args) {
        /*
            线程 t1 获取资源A，再获取资源B
            线程 t2 获取资源B，再获取资源A

            线程 t1 获取到资源A之后，等待资源B被释放
            线程 t2 获取到资源B之后，等待资源A被释放
            它们之间陷入循环等待

            诊断死锁方式：
            方式一：
                ①通过 jps 查看运行的线程
                ②通过 jstack -l 线程号 查看线程运行的情况
                ③观察信息，若存在线程死锁，则在最后会有“Found 1 deadlock.”的字样

            方式二：jconsole / jvisualvm 工具
                JDK 安装目录 bin目录下

         */

        Thread t1 = new Thread(() -> {
            synchronized (A) {
                System.out.println("lock A");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (B) {
                    System.out.println("lock B");
                    System.out.println("操作...");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                System.out.println("lock B");
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (A) {
                    System.out.println("lock A");
                    System.out.println("操作...");
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}