package cn.regexp.coding.trainee.datasource.factory;

import cn.regexp.coding.trainee.datasource.entity.DataSourceEntity;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Regexpei
 * @date 2024/5/26 21:58
 * @description 数据源工厂类
 */
public class DataSourceFactory {

    /**
     * 数据源 Map
     */
    private static final Map<String, DataSource> DATA_SOURCE_MAP = new ConcurrentHashMap<>();

    public DataSourceFactory(List<DataSourceEntity> dataSourceEntityList) {
        for (DataSourceEntity entity : dataSourceEntityList) {
            DataSource dataSource = DataSourceBuilder.create()
                    .type(DruidDataSource.class)
                    .driverClassName(entity.getDriverClassName())
                    .url(entity.getUrl())
                    .username(entity.getUsername())
                    .password(entity.getPassword())
                    .build();
            DATA_SOURCE_MAP.put(entity.getDataSourceName(), dataSource);
        }
    }

    public DataSource get(String dataSourceName) {
        return DATA_SOURCE_MAP.get(dataSourceName);
    }
}
