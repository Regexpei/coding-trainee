package cn.regexp.coding.trainee.pattern.factory.ebstract.impl;

import cn.regexp.coding.trainee.pattern.factory.ebstract.IPhone;

/**
 * @author Regexpei
 * @date 2024/6/18 23:01
 * @description 荣耀手机
 */
public class HonourPhone implements IPhone {
    @Override
    public void call() {
        System.out.println("使用荣耀手机打电话");
    }

    @Override
    public void sendMsg() {
        System.out.println("使用荣耀手机打电话");
    }
}
