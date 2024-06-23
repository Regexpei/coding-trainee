package cn.regexp.coding.trainee.pattern;

import cn.regexp.coding.trainee.pattern.factory.config.Logger;
import cn.regexp.coding.trainee.pattern.factory.config.LoggerFactory;
import cn.regexp.coding.trainee.pattern.factory.ebstract.IFactory;
import cn.regexp.coding.trainee.pattern.factory.ebstract.ILaptop;
import cn.regexp.coding.trainee.pattern.factory.ebstract.IPhone;
import cn.regexp.coding.trainee.pattern.factory.ebstract.impl.HonourFactory;
import cn.regexp.coding.trainee.pattern.factory.ebstract.impl.HuaweiFactory;
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

    /**
     * 测试抽象工厂模式
     */
    @Test
    public void testAbstractFactory() {
        String brand = "honour";
        IFactory factory = getFactory(brand);
        if (factory == null) {
            return;
        }

        IPhone phone = factory.createPhone();
        phone.call();
        phone.sendMsg();

        ILaptop laptop = factory.createLaptop();
        laptop.work();
        laptop.learn();
    }

    private static IFactory getFactory(String brand) {
        IFactory factory = null;
        switch (brand) {
            case "huawei":
                factory = new HuaweiFactory();
                break;
            case "honour":
                factory = new HonourFactory();
                break;
        }
        return factory;
    }


    @Test
    public void testConfigFactory() {
        Logger logger = LoggerFactory.getLogger("console");
        logger.log("控制台输出日志");

        logger = LoggerFactory.getLogger("file");
        logger.log("日志输出到文件中");

        logger = LoggerFactory.getLogger("database");
        logger.log("数据库记录日志");
    }

}
