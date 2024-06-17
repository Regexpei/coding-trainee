package cn.regexp.coding.trainee.pattern.factory.simple;

import org.springframework.stereotype.Component;

/**
 * @author Regexpei
 * @date 2024/6/16 23:06
 * @description 角色工厂类
 */
@Component
public class RoleFactory {

    /**
     * 创建角色方法
     *
     * @param roleType 角色类型
     * @return 角色
     */
    public IGameRole createRole(String roleType) {
        switch (roleType) {
            case "warrior":
                return new Warrior();
            case "mage":
                return new Mage();
            default:
                return null;
        }
    }

    /**
     * 创建角色静态方法
     *
     * @param roleType 角色类型
     * @return 角色
     */
    public static IGameRole createRoleStatic(String roleType) {
        switch (roleType) {
            case "warrior":
                return new Warrior();
            case "mage":
                return new Mage();
            default:
                return null;
        }
    }
}
