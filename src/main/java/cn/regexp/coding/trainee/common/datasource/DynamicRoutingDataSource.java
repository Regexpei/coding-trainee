package cn.regexp.coding.trainee.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Regexpei
 * @date 2024/6/2 16:59
 * @description 动态路由数据源
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        /*
         * 获取数据源标识时，会调用该方法进行获取，并从 org.springframework.jdbc.datasource.
         * lookup.AbstractRoutingDataSource.resolvedDataSources 中获取数据源对象
         * */
        return DataSourceContextHolder.getDataSource();
    }
}
