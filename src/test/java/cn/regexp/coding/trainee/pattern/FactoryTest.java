package cn.regexp.coding.trainee.pattern;

import cn.regexp.coding.trainee.pattern.factory.method.FerrariFactory;
import cn.regexp.coding.trainee.pattern.factory.method.ICar;
import cn.regexp.coding.trainee.pattern.factory.method.ICarFactory;
import cn.regexp.coding.trainee.pattern.factory.method.LamborghiniFactory;
import cn.regexp.coding.trainee.pattern.factory.simple.IGameRole;
import cn.regexp.coding.trainee.pattern.factory.simple.RoleFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Regexpei
 * @date 2024/6/16 23:07
 * @description 工厂模式测试类
 */
@SpringBootTest
public class FactoryTest {

    @Autowired
    private RoleFactory roleFactory;

    /**
     * 测试简单工厂模式
     * org.junit.jupiter.api.Test
     */
    @Test
    public void testSimpleFactory() {
        IGameRole warrior = roleFactory.createRole("warrior");
        if (warrior != null) {
            warrior.attack();
            warrior.defend();
        }

        IGameRole mage = roleFactory.createRole("mage");
        if (mage != null) {
            mage.attack();
            mage.defend();
        }
    }

    /**
     * 测试静态工厂模式
     */
    @Test
    public void testStaticFactory() {
        IGameRole warrior = RoleFactory.createRoleStatic("warrior");
        if (warrior != null) {
            warrior.attack();
            warrior.defend();
        }

        IGameRole mage = RoleFactory.createRoleStatic("mage");
        if (mage != null) {
            mage.attack();
            mage.defend();
        }
    }


    /**
     * 测试工厂方法模式
     */
    @Test
    public void testFactoryMethod() {
        // 创建法拉利工厂
        ICarFactory ferrariFactory = new FerrariFactory();
        ICar ferrariCar = ferrariFactory.createCar();
        ferrariCar.start();
        ferrariCar.run();
        ferrariCar.stop();

        // 创建兰博基尼工厂
        ICarFactory lamborghiniFactory = new LamborghiniFactory();
        ICar lamborghiniCar = lamborghiniFactory.createCar();
        lamborghiniCar.start();
        lamborghiniCar.run();
        lamborghiniCar.stop();
    }
}
