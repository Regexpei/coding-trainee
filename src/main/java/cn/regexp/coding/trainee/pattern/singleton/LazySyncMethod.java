package cn.regexp.coding.trainee.pattern.singleton;

/**
 * @author Regexpei
 * @date 2024/6/16 16:46
 * @description 单例模式-懒汉式（线程安全，同步方法）
 */
public class LazySyncMethod {

    private LazySyncMethod() {
    }

    private static LazySyncMethod INSTANCE;


    // 当获取该实例时，采取创建对象，添加 synchronized 同步方法保证线程安全
    public static synchronized LazySyncMethod getInstance() {
        if (INSTANCE == null) {
            //noinspection InstantiationOfUtilityClass
            INSTANCE = new LazySyncMethod();
        }
        return INSTANCE;
    }
}
