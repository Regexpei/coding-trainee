package cn.regexp.coding.trainee.pattern.factory.ebstract.impl;

import cn.regexp.coding.trainee.pattern.factory.ebstract.IFactory;
import cn.regexp.coding.trainee.pattern.factory.ebstract.ILaptop;
import cn.regexp.coding.trainee.pattern.factory.ebstract.IPhone;

/**
 * @author Regexpei
 * @date 2024/6/18 23:06
 * @description 华为工厂
 */
public class HuaweiFactory implements IFactory {
    @Override
    public IPhone createPhone() {
        return new HuaweiPhone();
    }

    @Override
    public ILaptop createLaptop() {
        return new HuaweiLaptop();
    }
}
