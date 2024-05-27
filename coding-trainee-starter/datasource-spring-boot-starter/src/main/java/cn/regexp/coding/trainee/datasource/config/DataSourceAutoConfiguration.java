package cn.regexp.coding.trainee.datasource.config;

import cn.regexp.coding.trainee.datasource.factory.DataSourceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Regexpei
 * @date 2024/5/26 21:49
 * @description 数据源自动配置类
 */
@Configuration
@ConditionalOnClass(DataSourceProperties.class)
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceAutoConfiguration {

    private final DataSourceProperties dataSourceConfig;

    public DataSourceAutoConfiguration(DataSourceProperties dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    @Bean
    public DataSourceFactory dataSourceFactory() {
        return new DataSourceFactory(dataSourceConfig.getDataSourceEntityList());
    }
}
