package cn.regexp.coding.trainee.pattern.factory.method;

/**
 * @author Regexpei
 * @date 2024/6/16 23:14
 * @description 法拉利赛车工厂
 */
public class LamborghiniFactory implements ICarFactory {
    @Override
    public ICar createCar() {
        return new LamborghiniCar();
    }
}
