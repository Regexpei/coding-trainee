## 适配器模式（Adapter Pattern）

### 一、介绍

适配器模式（Adapter Pattern）是一种结构型设计模式，它**允许不兼容的接口之间进行交互**。通过创建一个中间层——适配器，
来实现两个不兼容的接口的兼容。

生活中，有很多适配器模式的应用场景，比如电源适配器、数据转换头、插头转换器等。

#### 应用场景：
- 旧系统存在满足新系统所需要的类，但其接口不符合新系统所需要的接口。
- 使用第三方提供的组件，但组件接口定义和自己要求的接口定义不同。

### 二、分类

- **类适配器模式**：通过继承方式实现，耦合度高
- **对象适配器模式**：通过聚合或组合方式实现

### 三、案例

假设在一个电商系统中，有一个老旧的库存管理系统（LegacyInventorySystem）和新的订单处理系统（OrderProcessingSystem）。
库存管理系统提供的是 checkStock 方法用于检查库存，但是该方法返回的是一个特定的字符串结果（如"InStock"或"OutOfStock"），
而新的订单处理系统期望的是一个布尔值（true表示有货，false表示无货）来判断是否可以进行订单处理。为了让两个系统能够协同工作，
需要一个适配器来转换这两个不兼容的接口。

代码参考：[objectAdapter](../src/main/java/cn/regexp/coding/trainee/pattern/adapter/object)


### 四、应用

#### 1. Java 日志框架

Java 中存在多种日志框架，如 Log4j、Logback、java.util.logging（JUL）以及 Apache 的 Jakarta Commons Logging（JCL）等。
这些日志框架虽然提供了相似的功能，如按照不同级别（debug、info、warn、error）打印日志等，但它们的接口并不统一。 
这使得项目中使用多个不同日志框架的组件或库时，会面临接口不兼容的问题。

为了解决这个问题，便约定了一系列统一的接口（类似 JDBC），即 **SLF4J（Simple Logging Facade for Java）**。但由于这种约定比日志框架出现得晚，
日志框架不可能重新去进行实现，所以就可以通过**适配器模式**来进行兼容。


