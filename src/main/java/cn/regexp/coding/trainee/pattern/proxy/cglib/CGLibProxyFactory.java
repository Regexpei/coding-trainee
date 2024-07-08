package cn.regexp.coding.trainee.pattern.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @author Regexpei
 * @date 2024/7/8 21:17
 * @description CGLib 代理工厂
 */
public class CGLibProxyFactory {

    public static Object getProxy(Class<?> clazz) {
        // 创建增强器
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(clazz);
        // 设置回调函数
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("代售点收取服务费！");
            return methodProxy.invokeSuper(o, objects);
        });
        // 创建代理对象
        return enhancer.create();
    }

}
