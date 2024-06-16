package cn.regexp.coding.trainee.pattern.singleton;

/**
 * @author Regexpei
 * @date 2024/6/16 16:46
 * @description 单例模式-懒汉式（线程不安全）
 */
public class LazyUnsafe {

    private LazyUnsafe() {
    }

    private static LazyUnsafe INSTANCE;

    // 当获取该实例时，采取创建对象
    public static LazyUnsafe getInstance() {
        if (INSTANCE == null) {
            //noinspection InstantiationOfUtilityClass
            INSTANCE = new LazyUnsafe();
        }
        return INSTANCE;
    }
}
