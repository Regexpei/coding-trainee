package cn.regexp.coding.trainee.pattern.factory.simple;

import org.springframework.stereotype.Service;

/**
 * @author Regexpei
 * @date 2024/6/16 22:45
 * @description 战士类
 */
@Service
public class Warrior implements IGameRole {
    @Override
    public void attack() {
        System.out.println("战士使用剑进行攻击！");
    }

    @Override
    public void defend() {
        System.out.println("战士使用盾牌进行防御！");
    }
}
