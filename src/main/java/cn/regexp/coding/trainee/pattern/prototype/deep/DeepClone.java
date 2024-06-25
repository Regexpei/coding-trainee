package cn.regexp.coding.trainee.pattern.prototype.deep;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Regexpei
 * @date 2024/6/25 21:14
 * @description 深克隆
 */
@Getter
@Setter
public class DeepClone implements Cloneable {

    /**
     * 用户
     */
    private Student student;

    @Override
    public DeepClone clone() throws CloneNotSupportedException {
        DeepClone deepClone = (DeepClone) super.clone();
        // 引用类型克隆赋值
        this.student = this.student.clone();
        return deepClone;
    }
}
