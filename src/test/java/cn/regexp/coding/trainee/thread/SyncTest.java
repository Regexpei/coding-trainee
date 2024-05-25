package cn.regexp.coding.trainee.thread;

public class SyncTest {

    public static final Object lock = new Object();

    private static int count = 0;

    public static void main(String[] args) {
        synchronized (lock) {
            count++;
        }
        System.out.println(count);
    }

}
