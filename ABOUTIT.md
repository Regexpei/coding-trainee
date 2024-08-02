### 简化注入 Bean

ApplicationContextAware 是 Spring 框架提供的一种获取 Bean 的方式，通过实现 ApplicationContextAware 接口，
在 ApplicationContextAware 的 setApplicationContext 方法中，可以获取到 ApplicationContext 对象，从而获取到 Bean。

[AppContext](src/main/java/cn/regexp/coding/trainee/context/AppContext.java) 提供了简单获取 Bean 的静态方法，通过编写 [BaseService](src/main/java/cn/regexp/coding/trainee/core/service/BaseService.java) 类并提供一个静态的 get 方法，在编写业务 Service 时，只需要继承 BaseService 类便可以在代码中直接获取到 Bean，而不再需要通过 `@Autowired` 或 `@Resource` 注解进行注入。 