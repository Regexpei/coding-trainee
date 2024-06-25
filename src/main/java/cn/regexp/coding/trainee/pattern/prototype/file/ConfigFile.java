package cn.regexp.coding.trainee.pattern.prototype.file;

import lombok.*;

/**
 * @author Regexpei
 * @date 2024/6/25 21:30
 * @description 配置文件实体
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConfigFile implements Cloneable {

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件内容
     */
    private String content;


    @Override
    public ConfigFile clone() throws CloneNotSupportedException {
        return (ConfigFile) super.clone();
    }
}
