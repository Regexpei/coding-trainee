package cn.regexp.coding.trainee.pattern.factory.simple;

import org.springframework.stereotype.Service;

/**
 * @author Regexpei
 * @date 2024/6/16 23:04
 * @description 法师类
 */
@Service
public class Mage implements IGameRole {
    @Override
    public void attack() {
        System.out.println("法师使用法术进行攻击！");
    }

    @Override
    public void defend() {
        System.out.println("法师使用魔法屏障进行防御！");
    }
}
