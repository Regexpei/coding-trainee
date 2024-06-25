package cn.regexp.coding.trainee.pattern.prototype.shallow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Regexpei
 * @date 2024/6/25 21:15
 * @description 用户实体
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;
}
