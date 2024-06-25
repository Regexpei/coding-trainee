package cn.regexp.coding.trainee.pattern.prototype.deep;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Regexpei
 * @date 2024/6/25 21:15
 * @description 学生实体
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Cloneable {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    @Override
    protected Student clone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }
}
