package cn.regexp.coding.trainee.pattern.prototype.shallow;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Regexpei
 * @date 2024/6/25 21:14
 * @description 浅克隆
 */
@Getter
@Setter
public class ShallowClone implements Cloneable {

    /**
     * 用户
     */
    private User user;

    @Override
    public ShallowClone clone() throws CloneNotSupportedException {
        return (ShallowClone) super.clone();
    }
}
