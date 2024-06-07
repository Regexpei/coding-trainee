package cn.regexp.coding.trainee.common.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Regexpei
 * @date 2024/6/2 17:10
 * @description 动态数据源切面类
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("execution(* cn.regexp.coding.trainee.mapper.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object switchDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取类
        Class<?> aClass = joinPoint.getTarget().getClass();

        // 获取方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 优先级：方法 > 类 > 默认
        if (method.isAnnotationPresent(DynamicDataSource.class)) {
            DynamicDataSource annotation = method.getAnnotation(DynamicDataSource.class);
            DataSourceContextHolder.setDataSource(annotation.name());
        } else if (aClass.isAnnotationPresent(DynamicDataSource.class)) {
            DynamicDataSource annotation = aClass.getAnnotation(DynamicDataSource.class);
            DataSourceContextHolder.setDataSource(annotation.name());
        }

        try {
            return joinPoint.proceed();
        } finally {
            // 使用后清除标识，避免内存泄露
            DataSourceContextHolder.clearDataSource();
        }
    }
}
