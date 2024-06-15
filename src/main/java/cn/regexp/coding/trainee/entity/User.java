package cn.regexp.coding.trainee.entity;

import com.mybatisflex.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Regexpei
 * @date 2024/6/10 16:46
 * @description 用户实体类
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    private String name;

    private Integer age;
}
