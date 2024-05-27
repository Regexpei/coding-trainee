package cn.regexp.coding.trainee.datasource.config;

import cn.regexp.coding.trainee.datasource.entity.DataSourceEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Regexpei
 * @date 2024/5/26 21:43
 * @description 数据源配置
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DataSourceProperties {

    List<DataSourceEntity> dataSourceEntityList;
}
