package cn.regexp.coding.trainee.pattern.proxy.jdk;

import cn.regexp.coding.trainee.pattern.proxy.RailwayStation;
import cn.regexp.coding.trainee.pattern.proxy.SellTicket;

import java.lang.reflect.Proxy;

/**
 * @author Regexpei
 * @date 2024/7/7 22:25
 * @description JDK 代理对象工厂
 */
public class JdkProxyFactory {

    // 目标对象
    private final RailwayStation railwayStation = new RailwayStation();

    public SellTicket getProxyObject() {
        /*
            ClassLoader loader: 类加载器，用于加载代理类，可以通过目标对象获取类加载器
            Class<?>[] interfaces: 代理类实现的接口的字节码对象
            InvocationHandler h: 代理对象的调用处理程序
         */
        return (SellTicket) Proxy.newProxyInstance(railwayStation.getClass().getClassLoader(),
                railwayStation.getClass().getInterfaces(),
                /*
                    proxy: 代理对象，一般不用
                    method: 正在被调用的方法对象
                    args: 调用方法时，传入的参数
                 */
                (proxy, method, args) -> {
                    System.out.println("代售点收取服务费！");
                    return method.invoke(railwayStation, args);
                });
    }

}
