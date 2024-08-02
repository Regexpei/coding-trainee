package cn.regexp.coding.trainee.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Regexpei
 * @date 2024/8/2 22:21
 * @description 用户
 * @since 1.0.0
 */
@Setter
@Getter
public class User {
    private String name;
    private String roleEnName;
    private String roleCnName;

    public User(String name, String roleEnName) {
        this.name = name;
        this.roleEnName = roleEnName;
    }
}
