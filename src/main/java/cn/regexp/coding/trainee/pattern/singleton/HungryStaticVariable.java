package cn.regexp.coding.trainee.pattern.singleton;

/**
 * @author Regexpei
 * @date 2024/6/16 16:16
 * @description 单例模式-饿汉式（静态变量）
 */
public class HungryStaticVariable {

    // 1.构造器私有化，防止外部直接 new
    private HungryStaticVariable() {
    }

    // 2.内部创建对象实例
    @SuppressWarnings("InstantiationOfUtilityClass")
    private static final HungryStaticVariable INSTANCE = new HungryStaticVariable();

    // 3.提供一个静态方法给外界获取该实例
    public static HungryStaticVariable getInstance() {
        return INSTANCE;
    }
}
