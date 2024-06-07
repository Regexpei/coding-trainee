package cn.regexp.coding.trainee.common.datasource;

import java.lang.annotation.*;

/**
 * @author Regexpei
 * @date 2024/6/2 17:04
 * @description 动态数据源注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface DynamicDataSource {


    String DB1_DATASOURCE = "db1DataSource";
    /**
     * 数据源2
     */
    String DB2_DATASOURCE = "db2DataSource";

    /**
     * 数据源名称
     *
     * @return 数据源名称
     */
    String name() default DynamicDataSource.DB1_DATASOURCE;

}
