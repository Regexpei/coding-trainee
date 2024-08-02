package cn.regexp.coding.trainee.core.service;

import cn.regexp.coding.trainee.context.AppContext;

/**
 * @author Regexpei
 * @date 2024/8/2 22:27
 * @description 基础服务
 * @since 1.0.0
 */
public class BaseService {

    protected static <T> T get(Class<T> clazz) {
        return AppContext.getBean(clazz);
    }
}
