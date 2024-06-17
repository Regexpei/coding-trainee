package cn.regexp.coding.trainee.pattern.factory.method;

/**
 * @author Regexpei
 * @date 2024/6/16 23:11
 * @description 法拉利
 */
public class FerrariCar implements ICar {
    @Override
    public void start() {
        System.out.println("法拉利启动发动机......");
    }

    @Override
    public void run() {
        System.out.println("法拉利正在疾驰中......");
    }

    @Override
    public void stop() {
        System.out.println("法拉利停止发动机......");
    }
}
