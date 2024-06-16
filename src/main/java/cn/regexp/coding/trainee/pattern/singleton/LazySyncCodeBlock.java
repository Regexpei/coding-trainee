package cn.regexp.coding.trainee.pattern.singleton;

/**
 * @author Regexpei
 * @date 2024/6/16 16:46
 * @description 单例模式-懒汉式（线程不安全，同步代码块）
 */
public class LazySyncCodeBlock {

    private LazySyncCodeBlock() {
    }

    private static LazySyncCodeBlock INSTANCE;


    // 当获取该实例时，采取创建对象，添加 synchronized 同步代码块
    public static LazySyncCodeBlock getInstance() {
        if (INSTANCE == null) {
            // 有多个线程通过 if 判断进入这里，那么当获取到锁后就会继续执行初始化实例的代码，会产生多个实例
            synchronized (LazySyncCodeBlock.class) {
                //noinspection InstantiationOfUtilityClass
                INSTANCE = new LazySyncCodeBlock();
            }
        }
        return INSTANCE;
    }
}
