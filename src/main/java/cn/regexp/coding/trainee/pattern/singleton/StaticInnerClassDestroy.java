package cn.regexp.coding.trainee.pattern.singleton;

import java.io.Serializable;

/**
 * @author Regexpei
 * @date 2024/6/16 18:02
 * @description 单例模式-静态内部类
 */
public class StaticInnerClassDestroy implements Serializable {

    private StaticInnerClassDestroy() {
    }

    // 静态内部类，提供一个静态属性
    private static class StaticInnerClassInstance {
        public static final StaticInnerClassDestroy INSTANCE = new StaticInnerClassDestroy();
    }

    // 提供一个静态方法获取实例，首次调用会加载静态内部类并完成实例初始化
    public static StaticInnerClassDestroy getInstance() {
        return StaticInnerClassInstance.INSTANCE;
    }

    // 当进行反序列化时，会调用此方法，将该方法的返回值直接返回
    public Object readResolve() {
        return StaticInnerClassInstance.INSTANCE;
    }
}
