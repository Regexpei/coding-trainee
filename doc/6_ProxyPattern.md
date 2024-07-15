## 代理模式（Proxy Pattern）

### 一、介绍

代理模式为其他对象提供一种代理以控制对这个对象的访问。
简单来说，就是在访问某个对象时，不直接访问该对象本身，而是通过一个“代理”来间接访问。
这个“代理”可以看作是原对象的一个“替身”或“中介”。

> **例子1**：我们买电脑的时候，通常都是到官方店铺购买，而不是直接找厂家。在这里，官方店铺是代理类，电脑厂家是被代理类。
> 
> **例子2**：假如我们现在需要对某些业务方法进行事务管理，在没有使用代理之前，每一个业务方法都需要处理业务（开启、提交、回滚），
> 这必然导致大量重复的代码，并且业务层也掺杂了一些非业务代码。

#### 作用：
- **解决代码结构重复**：在开发中尽量做到不要重复代码，重复代码意味着维护成本增大
- **处理责任不分离**：业务层只需要关心业务功能的实现，不需要关心事务管理、日志管理、权限管理等内容

#### 应用场景
- **远程代理**：RPC、Java RMI（远程方法调用）
- **虚拟代理**：MyBatis 中的延迟加载、Spring 框架中的按需加载
- **安全代理**：验证用户的身份和权限

### 二、分类

> 按照代理类生成时机划分

1. **静态代理**：代理类在**编译时**生成，即程序执行之前就已经存在了，需要我们自己编写，代理对象和真实对象的关系是在编译时确定的。
2. **动态代理**：代理类在**运行时**由 JVM 通过**反射等机制动态生成**，不需要我们编写，代理对象和真实对象的关系在运行时确定的。
   - **JDK 代理**：委托类必须实现一个或多个接口
   - **CGLib（Code Generation Library） 代理**：可以在没有接口的情况下创建代理对象

### 三、示例 

#### 1. 静态代理

参考：[static](../src/main/java/cn/regexp/coding/trainee/pattern/proxy/xtatic)

#### 2. JDK 动态代理

参考：[jdk](../src/main/java/cn/regexp/coding/trainee/pattern/proxy/jdk)

#### 3. CGLib 动态代理

参考：[cglib](../src/main/java/cn/regexp/coding/trainee/pattern/proxy/cglib)


### 四、原理

> **动态代理实现机制**：
> 
> 由于 JVM 是通过字节码的二进制信息加载类的，若我们在程序运行时，遵循 Java 编译系统组织 .class 文件的格式和结构，生成相应的二进制数据，
> 然后再把这个二进制数据加载并转换成对应的类。这样，我们就能够完成在程序中动态地创建一个类了。

#### 1. JDK 动态代理

使用 [arthas-boot.jar](../tool/arthas-boot.jar) 工具反编译代理类，获取代理类源码：

```shell
# 启动 Arthas
java -jar .\tool\arthas-boot.jar
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


### 五、对比

#### 1. 静态代理
- 优点
  - **责任分离**，业务类只需要关注业务逻辑本身，保证了业务类的重用性。
  - **隐藏对象**，将真实对象隐藏起来，从而保护真实对象。
- 缺点
  - **单一服务**：代理对象的某个接口只服务于某一类型的对象，即需要为每个真实类创建一个代理类，假如有很多个类，将需要创建很多个代理类
  - **代理代码重复**：若需要代理的方法很多，则需要为每一个方法进行代理处理
  - **多余方法实现**：若接口添加一个方法，则实现该接口的实现类都需要实现该方法，包含代理类

#### 2. JDK 动态代理
- 优点
  - 对比静态代理，不需要手动创建代理类
- 缺点
  - 真实类必须实现接口
  - 动态代理的最小单位是类（**类中的某些方法都会被处理，比如对 toString() 方法进行事务处理**），如果想拦截一部分方法，则需要在 invoke() 方法中根据方法名进行判断

#### 3. CGLib 动态代理
- 优点
  - 对比静态代理，不需要手动创建代理类
  - 对比 JDK 动态代理，不需要实现接口
- 缺点
  - 无法代理 final 类和方法，因为 CGLib 是通过继承的方式来实现代理的，而 final 类和 final 方法无法继承
  - 借助 ASM 等字节码操作框架实现，相对复杂
        
> 如何选择？
> - 若被代理类需要实现接口，且接口方法数量不多，且接口方法调用频繁，则使用 JDK 动态代理
> - 若被代理类没有实现接口，则使用 CGLib 动态代理



