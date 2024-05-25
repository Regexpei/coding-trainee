package cn.regexp.coding.trainee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Regexpei
 * @date 2024/4/6 20:22
 * @description
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private Integer age;


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("User - finalize");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User person = (User) o;
        return Objects.equals(name, person.name) && Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

}
