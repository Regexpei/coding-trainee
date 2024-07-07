package cn.regexp.coding.trainee.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author Regexpei
 * @date 2024/7/6 22:13
 * @description 用户类
 */
@Setter
@Getter
@ToString
@Table(value = "tb_user")
public class User {

    // 主键
    @Id(keyType = KeyType.Auto)
    private Long id;
    // 昵称
    private String nickname;
    // 姓名
    private String name;
    // 年龄
    private Integer age;
    // 性别
    private String gender;
    // 出生日期
    private LocalDate birthDate;
    // 籍贯
    private String nativePlace;
    // 住址
    private String address;
    // 个性签名
    private String personalSignature;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;

}
