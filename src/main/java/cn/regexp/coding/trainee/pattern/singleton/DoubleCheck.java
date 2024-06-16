package cn.regexp.coding.trainee.pattern.singleton;

/**
 * @author Regexpei
 * @date 2024/6/16 17:19
 * @description 单例模式-双重检查
 */
public class DoubleCheck {

    private DoubleCheck() {
    }

    private volatile static DoubleCheck instance;

    public static DoubleCheck getInstance() {
        if (instance == null) {
            // 假如有多个线程通过第一个 if 判断进入到这里进行排队
            synchronized (DoubleCheck.class) {
                // 第一个线程进入这里，if 成立，初始化实例
                // 第二个线程进入这里，if 不成立，跳过初始化
                if (instance == null) {
                    //noinspection InstantiationOfUtilityClass
                    instance = new DoubleCheck();
                }
            }
        }
        return instance;
    }
}
