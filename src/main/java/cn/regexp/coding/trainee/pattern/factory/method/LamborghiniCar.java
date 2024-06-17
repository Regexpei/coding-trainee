package cn.regexp.coding.trainee.pattern.factory.method;

/**
 * @author Regexpei
 * @date 2024/6/16 23:11
 * @description 兰博基尼
 */
public class LamborghiniCar implements ICar {
    @Override
    public void start() {
        System.out.println("兰博基尼启动发动机......");
    }

    @Override
    public void run() {
        System.out.println("兰博基尼正在疾驰中......");
    }

    @Override
    public void stop() {
        System.out.println("兰博基尼停止发动机......");
    }
}
