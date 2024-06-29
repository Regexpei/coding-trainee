## 建造者模式（Builder Pattern）

### 一、介绍
建造者模式用于创建复杂对象，其核心思想是一个复杂对象的**构建和表示相互分离**，使得同样地创建过程可以生成不同的对象。

简单地说，就是提供不同的参数，通过这些参数构建出一个你想要的对象。

以组装电脑为例，你可以选择不同的 CPU、内存、硬盘、主板、屏幕等，然后组装成一台电脑，由于提供的配置不同，最终组装出的电脑也不同。

**特点：调用建造者产生的对象的方法时，返回值为建造者对象本身。**

**使用场景**：
- 通过多个参数创建复杂对象（如果使用构造函数，那么构造函数参数个数太多，容易传错参数）

### 二、示例 

通过建造者模式创建订单，传入订单项目、收货地址、支付方式即可生产一个订单对象。

参考：[builder](../src/main/java/cn/regexp/coding/trainee/pattern/builder)

### 三、源码

#### 1. JDK 中的 StringBuilder

虽然 StringBuilder 本身并不是典型的建造者模式实现，但它的使用方式体现了建造者模式的思想。

通过 append 方法，我们可以逐步构建一个字符串，而不是一次性指定所有内容。

#### 2. Spring 中的 BeanDefinitionBuilder

BeanDefinitionBuilder 是 Spring 框架中的一个建造者模式实现，能够通过链式调用方式来配置和创建 BeanDefinition 对象。

通过 BeanDefinitionBuilder ，我们可以设置 bean 的各种属性，如作用域、是否懒加载、初始化和销毁方法等。

参考：[BuilderTest#testBeanDefinitionBuilder](../src/test/java/cn/regexp/coding/trainee/pattern/BuilderTest.java)

#### 3. MyBatis 中的 SqlSessionFactoryBuilder

SqlSessionFactoryBuilder 也是建造者模式的一种实现，主要负责构建 SqlSessionFactory 实例，SqlSessionFactory 是用于创建 SqlSession 的工厂。


### 四、对比

#### 1. 工厂方法模式 VS 建造者模式 

工厂方法模式注重的是**整体对象的创建**，而建造者模式注重的是**创建的过程**，通过一步又一步的方式创建对象。

举个栗子，创建一台电脑，对于工厂方法模式，它就直接创建一台电脑给你，而建造者模式则需要你提供电脑的各种配置信息，
比如 CPU、内存、硬盘、主板等，然后一步步的创建，最后得到一台电脑。


#### 2. 抽象工厂模式 VS 建造者模式

抽象工厂模式注重的是对产品族的创建，不需要关注构建过程，只需要关注什么产品由什么工厂创建即可。

举个栗子，创建一台电脑，对于抽象工厂模式，你需要选择一个电脑工厂，然后创建一台电脑给你；而建造者模式则需要你提供电脑的各种配置，然后一步步的创建，最后得到一台电脑。