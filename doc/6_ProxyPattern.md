## 代理模式（Proxy Pattern）

### 一、介绍

代理模式为其他对象提供一种代理以控制对这个对象的访问。
简单来说，就是在访问某个对象时，不直接访问该对象本身，而是通过一个“代理”来间接访问。
这个“代理”可以看作是原对象的一个“替身”或“中介”。

> 我们买电脑的时候，通常都是到官方店铺购买，而不是直接找厂家。在这里，官方店铺是代理类，电脑厂家是被代理类。

#### 分类（按照代理类生成时机）：

1. **静态代理**：代理类在编译时生成，代理类和被代理类在编译时绑定。
2. **动态代理**：在 Java 运行时动态生成代理类，代理类和被代理类在运行时绑定。
   - **JDK 代理**：委托类必须实现一个或多个接口
   - **CGLib（Code Generation Library） 代理**：可以在没有接口的情况下创建代理对象


### 二、示例 

#### 1. 静态代理

参考：[static](../src/main/java/cn/regexp/coding/trainee/pattern/proxy/xtatic)

#### 2. JDK 动态代理

参考：[jdk](../src/main/java/cn/regexp/coding/trainee/pattern/proxy/jdk)

#### 3. CGLib 动态代理

参考：[cglib](../src/main/java/cn/regexp/coding/trainee/pattern/proxy/cglib)


### 三、原理

#### 1. JDK 动态代理

使用 [arthas-boot.jar](../tool/arthas-boot.jar) 工具反编译代理类，获取代理类源码：

```shell
# 启动 Arthas
java -jar ../tool/arthas-boot.jar
# 选择当前启动的进程
# 反编译代理类
jad com.sun.proxy.$Proxy4
```
**重点代码**：

```java
public final class $Proxy4 extends Proxy implements SellTicket {
    private static Method m3;

    public $Proxy4(InvocationHandler invocationHandler) {
        super(invocationHandler);
    }

    static {
        m3 = Class.forName("cn.regexp.coding.trainee.pattern.proxy.SellTicket")
			.getMethod("sellTicket", Class.forName("java.lang.String"), 
				Class.forName("java.lang.Double"));
    }
	
	public void sellTicket(String string, Double d) {
        this.h.invoke(this, m3, new Object[]{string, d});
    }
}

public class Proxy {
	protected InvocationHandler h;
	protected Proxy(InvocationHandler h) {
        Objects.requireNonNull(h);
        this.h = h;
    }
}

public class TicketProxyFactory {
   private final RailwayStation railwayStation = new RailwayStation();
   public SellTicket getProxyObject() {
      return (SellTicket) Proxy.newProxyInstance(railwayStation.getClass().getClassLoader(),
              railwayStation.getClass().getInterfaces(),
              (proxy, method, args) -> {
                 System.out.println("代售点收取服务费！");
                 return method.invoke(railwayStation, args);
              });
   }
}
```

**执行流程**：

1. 在测试类中通过代理对象调用 sellTicket() 方法
2. 根据多态特性，执行的是代理类（$Proxy4）中的 sellTicket() 方法
3. 代理类（$Proxy4）中的 sellTicket() 方法又调用了 InvocationHandler 的 invoke() 方法
4. invoke() 方法中，通过反射调用被代理类的 sellTicket() 方法

![JdkProxyExecFlow.png](../imgs/JdkProxyExecFlow.png)
