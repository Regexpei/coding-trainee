package cn.regexp.coding.trainee.common.datasource;

import cn.hutool.core.util.StrUtil;

/**
 * @author Regexpei
 * @date 2024/6/2 16:44
 * @description 动态数据源上下文辅助类，用于保存数据源标识
 */
public class DataSourceContextHolder {

    /**
     * 数据源标识
     * <p>
     * ThreadLocal：用于提供线程内部局部变量的类，内部其实是一个 Map，key 为线程对象，value 为 set 的值
     */
    private static final ThreadLocal<String> DATA_SOURCE = new ThreadLocal<>();


    /**
     * 设置数据源标识
     *
     * @param dsType 数据源标识
     */
    public static void setDataSource(String dsType) {
        DATA_SOURCE.set(dsType);
    }

    /**
     * 获取数据源标识
     *
     * @return 数据源标识
     */
    public static String getDataSource() {
        return StrUtil.blankToDefault(DATA_SOURCE.get(), DynamicDataSource.DB1_DATASOURCE);
    }

    /**
     * 清除数据源标识
     */
    public static void clearDataSource() {
        DATA_SOURCE.remove();
    }
}
