package cn.regexp.coding.trainee.common.datasource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Regexpei
 * @date 2024/6/2 16:47
 * @description 动态数据源配置
 */
@Configuration
// 取消对原生数据源自动化配置的使用
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DataSourceConfiguration {

    @Bean(DynamicDataSource.DB1_DATASOURCE)
    // 项目启动时，Spring Boot 会将相关配置注入到返回的对象中
    @ConfigurationProperties(prefix = "spring.db1-datasource")
    public DataSource db1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(DynamicDataSource.DB2_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.db2-datasource")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DynamicDataSource.DB1_DATASOURCE, db1DataSource());
        dataSourceMap.put(DynamicDataSource.DB2_DATASOURCE, db2DataSource());

        // 设置目标数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        // 设置默认目标数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(db1DataSource());
        return dynamicRoutingDataSource;
    }

}
