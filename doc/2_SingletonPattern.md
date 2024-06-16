## 单例模式（Singleton Pattern）

### 一、介绍

单例模式，就是采用某些方法来确保在整个软件系统中一个类**只有一个实例**，并提供一个**静态方法**来获取该实例。

**使用场景：**
- 需要频繁创建和销毁对象
- 创建对象很耗时或很耗资源

在 JDK 中的 Runtime（饿汉式）、MyBatis 的 SqlSessionFactory 都采用了单例模式进行实现，而在我们实际开发项目中，数据库连接池、线程池也都需要采用单例模式进行实现。

**实现方式（8种）：**

- 饿汉式（静态变量）
- 饿汉式（静态代码块）
- 懒汉式（线程不安全）
- 懒汉式（线程安全，同步方法）
- 懒汉式（线程不安全，同步代码块）
- 双重检查
- 静态内部类
- 枚举

### 二、实现

#### 1. 饿汉式（静态变量）<span style="color: cornflowerblue">【可用】</span>

优点：简单，类装载时完成初始化，避免线程同步问题。

缺点：类装载就完成初始化，没有达到懒加载的效果，假如这个实例没有被用过，则会造成内存浪费。

参考：[HungryStaticVariable.java](../src/main/java/cn/regexp/coding/trainee/pattern/singleton/HungryStaticVariable.java)

#### 2. 饿汉式（静态代码块）<span style="color: cornflowerblue">【可用】</span>

优缺点与饿汉式（静态变量）类似

参考：[HungryStaticCodeBlock.java](../src/main/java/cn/regexp/coding/trainee/pattern/singleton/HungryStaticCodeBlock.java)

#### 3. 懒汉式（线程不安全）<span style="color: red">【不推荐使用】</span>

优点：能够起到懒加载的效果。

缺点：只能在单线程下使用，多线程中会产生多个实例。

参考：[LazyUnsafe.java](../src/main/java/cn/regexp/coding/trainee/pattern/singleton/LazyUnsafe.java)

#### 4. 懒汉式（线程安全，同步方法）<span style="color: red">【不推荐使用】</span>

优点：懒加载，线程安全。

缺点：效率低，获取实例时每个线程都需要进行排队。

参考：[LazySyncMethod.java](../src/main/java/cn/regexp/coding/trainee/pattern/singleton/LazySyncMethod.java)

#### 5. 懒汉式（线程不安全，同步代码块）<span style="color: red">【不推荐使用】</span>

该方式是对懒汉式（线程安全，同步方法）的改进，但是这种方式并**不能起到线程安全的作用**。因为，当有多个线程通过 if
判断后，会自旋去获取锁，获取到锁之后就会执行初始化实例的代码，这样将会产生多个实例。

参考：[LazySyncCodeBlock.java](../src/main/java/cn/regexp/coding/trainee/pattern/singleton/LazySyncCodeBlock.java)

#### 6. 双重检查<span style="color: mediumseagreen">【推荐使用】</span>

优点：懒加载，线程安全，效率较高

缺点：实现相对复杂

参考：[DoubleCheck.java](../src/main/java/cn/regexp/coding/trainee/pattern/singleton/DoubleCheck.java)

> **volatile 的作用**：
>
> 1. **防止指令重排序**。为了提高程序的执行性能，编译器和处理器常常会对指令进行重排序。但在并发编程中，指令重排序可能会导致数据不一致。volatile
     关键字可以禁止重排序，从而确保程序的顺序一致性。
> 2. **保证线程之间的可见性**。进程中包含多个线程，进程有共享内存，而线程有私有内存。volatile
     能够保证修改的值会立即被更新到共享内存，当有其他线程需要读取时，就会去共享内存中读取新值，即保证所有线程使用这个变量时都是最新的。

#### 7. 静态内部类<span style="color: mediumseagreen">【推荐使用】</span>

采用类加载机制来保证只有一个线程完成实例的初始化。

优点：懒加载，线程安全（JVM 的类加载机制保证），效率较高

参考：[StaticInnerClass.java](../src/main/java/cn/regexp/coding/trainee/pattern/singleton/StaticInnerClass.java)

> **静态内部类的特点**：
> 1. 静态内部类所在的类被加载时，静态内部类并不会被加载。
> 2. 当获取静态内部类内的变量时，静态内部类会被加载，并且只加载一次。

#### 8. 枚举<span style="color: mediumseagreen">【推荐使用】</span>

优点：线程安全，能够防止反序列化重新创建新的对象

参考：[Enum.java](../src/main/java/cn/regexp/coding/trainee/pattern/singleton/Enum.java)

> Effective Java 作者提倡的方式

### 三、拓展

#### 破坏单例模式

##### 1. 通过序列化、反序列化破坏单例模式

参考：[SingletonTest#testBreakSingletonBySerializable](../src/test/java/cn/regexp/coding/trainee/pattern/SingletonTest.java)
     
**解决方案**：在类中添加`readResolve()`方法，并返回已创建的实例对象。该方法会在反序列化时通过反射进行调用，并返回方法的值；若没有定义，则返回新 new 的对象。

参考：[StaticInnerClassDestroy#readResolve](../src/main/java/cn/regexp/coding/trainee/pattern/singleton/StaticInnerClass.java)

> **源码追踪**：
> 
> java.io.ObjectInputStream.readObject() →
> java.io.ObjectInputStream.readObject(java.lang.Class<?>) →
> java.io.ObjectInputStream.readObject0 →
> java.io.ObjectInputStream.readOrdinaryObject →
> java.io.ObjectStreamClass.hasReadResolveMethod →
> java.io.ObjectStreamClass.invokeReadResolve
> 
> **最后判断是否有`readResolve()`方法，有则调用该方法**


##### 2. 通过反射破坏单例模式

参考：[SingletonTest#testBreakSingletonByReflect](../src/test/java/cn/regexp/coding/trainee/pattern/SingletonTest.java)


