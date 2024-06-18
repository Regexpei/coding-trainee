package cn.regexp.coding.trainee.pattern.factory.ebstract.impl;

import cn.regexp.coding.trainee.pattern.factory.ebstract.ILaptop;

/**
 * @author Regexpei
 * @date 2024/6/18 23:03
 * @description 华为笔记本电脑
 */
public class HuaweiLaptop implements ILaptop {
    @Override
    public void work() {
        System.out.println("使用华为笔记本电脑工作");
    }

    @Override
    public void learn() {
        System.out.println("使用华为笔记本电脑学习");
    }
}
