package cn.regexp.coding.trainee.pattern.singleton;

/**
 * @author Regexpei
 * @date 2024/6/16 16:30
 * @description 单例模式-饿汉式（静态代码块）
 */
public class HungryStaticCodeBlock {

    // 1.构造器私有化，防止外部直接 new
    private HungryStaticCodeBlock() {
    }

    private static final HungryStaticCodeBlock INSTANCE;

    // 2.内部创建对象实例
    static {
        //noinspection InstantiationOfUtilityClass
        INSTANCE = new HungryStaticCodeBlock();
    }

    // 3.提供一个静态方法给外界获取该实例
    public static HungryStaticCodeBlock getInstance() {
        return INSTANCE;
    }

}
