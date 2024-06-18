package cn.regexp.coding.trainee.pattern.factory.ebstract.impl;

import cn.regexp.coding.trainee.pattern.factory.ebstract.IFactory;
import cn.regexp.coding.trainee.pattern.factory.ebstract.ILaptop;
import cn.regexp.coding.trainee.pattern.factory.ebstract.IPhone;

/**
 * @author Regexpei
 * @date 2024/6/18 23:07
 * @description 荣耀工厂
 */
public class HonourFactory implements IFactory {
    @Override
    public IPhone createPhone() {
        return new HonourPhone();
    }

    @Override
    public ILaptop createLaptop() {
        return new HonourLaptop();
    }
}
