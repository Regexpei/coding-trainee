package cn.regexp.coding.trainee.pattern.singleton;

/**
 * @author Regexpei
 * @date 2024/6/16 18:02
 * @description 单例模式-静态内部类
 */
public class StaticInnerClass {

    private StaticInnerClass() {
    }

    // 静态内部类，提供一个静态属性
    private static class StaticInnerClassInstance {
        @SuppressWarnings("InstantiationOfUtilityClass")
        public static final StaticInnerClass INSTANCE = new StaticInnerClass();
    }

    // 提供一个静态方法获取实例，首次调用会加载静态内部类并完成实例初始化
    public static StaticInnerClass getInstance() {
        return StaticInnerClassInstance.INSTANCE;
    }
}
