package cn.regexp.coding.trainee.datasource.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Regexpei
 * @date 2024/5/26 22:40
 * @description 数据源实体类
 */
@Getter
@Setter
public class DataSourceEntity {

    /**
     * 数据源名称
     */
    private String dataSourceName;

    /**
     * 数据源类型
     */
    private String type;

    /**
     * 驱动类名
     */
    private String driverClassName;

    /**
     * 数据库 url
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库用户密码
     */
    private String password;

}
