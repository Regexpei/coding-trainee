package cn.regexp.coding.trainee.pattern.factory.ebstract;

/**
 * @author Regexpei
 * @date 2024/6/18 23:04
 * @description 工厂接口
 */
public interface IFactory {
    /**
     * 创建手机
     *
     * @return 手机
     */
    IPhone createPhone();

    /**
     * 创建笔记本电脑
     *
     * @return 笔记本电脑
     */
    ILaptop createLaptop();
}
