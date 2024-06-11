## Druid Monitor

### 一、介绍

Druid 是一个开源的、高性能的数据库连接池，由阿里巴巴开发并维护。

而 Druid Monitor 则是 Druid 中内置的一个**监控工具**，用于实时监控数据库连接池的运行状态、性能指标以及SQL执行情况等。

通过 Druid Monitor，我们可以对数据源、慢查询、SQL、URI、Session 等内容进行监控，从而及时发现潜在的问题并进行优化。

- **监控数据源**：能够实时监控数据源（如 MySQL、Oracle 等）的连接数、活跃连接数、空闲连接数、等待连接数等指标
- **监控慢查询**：记录并展示慢查询日志，通过配置慢查询阈值，可以自动记录执行时间超过阈值的 SQL 语句
- **SQL 监控**：实时监控 SQL 语句的执行情况，包括执行时间、执行次数、并发数等指标
- **URI 监控**：监控 Web 应用的 URI 请求情况，包括请求次数、响应时间等指标
- **Session 监控**：监控用户的 Session 情况，包括 Session 数量、活跃 Session 数、Session 创建时间等指标

参考资料：https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter

### 二、简单使用

> 以下内容为主要内容，其余内容请看项目具体文件

1. 添加依赖 [pom.xml](../pom.xml)
    ```xml
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.2.16</version>
    </dependency>
    ```
2. 添加配置 [application.properties](../src/main/resources/application.properties)
    ```properties
   ## Druid 内置监控
   # 开启监控
   spring.datasource.druid.stat-view-servlet.enabled=true
   # 启用 stat 过滤器，用于统计监控信息，不配置执行的 SQL 将不会被统计
   spring.datasource.druid.filters=stat
    ```

3. 效果展示：
![DruidMonitor_1.png](../imgs/DruidMonitor_1.png)

### 三、进阶使用

以上仅仅只是对 Druid Monitor 使用的一个最简单配置使用案例，而 Druid 其实提供了很多丰富的配置供开发人员进行设置。

对于实际中的企业项目，特别是上线并对外开放的项目，安全性是至关重要的。而对于上述的配置，只要有人具有部署这个应用的机器权限，就可以访问到
Druid Monitor，这还是挺危险的！

那么，我们可以考虑对这个 Druid Monitor 的访问权限进行控制，比如设置账号密码登录、限制 IP 登录等操作。

##### 配置账号密码

```properties
# 登录用户
spring.datasource.druid.stat-view-servlet.login-username=regexp
# 登录密码
spring.datasource.druid.stat-view-servlet.login-password=regexp
```

##### 配置访问IP

```properties
# 允许访问的 IP，默认为本地（可以看下自己手机连接的 WiFi IP，进行测试）
spring.datasource.druid.stat-view-servlet.allow=192.168.0.1
# 禁止访问的 IP
spring.datasource.druid.stat-view-servlet.deny=127.0.0.1
```

>
从源码[com.alibaba.druid.spring.boot.autoconfigure.stat.DruidStatViewServletConfiguration.statViewServletRegistrationBean]
中可以看出，当项目配置文件中没有配置允许访问的 IP 时，默认设置为本地

#### 配置禁止重置

在 Druid Monitor 页面，导航栏有两个按钮“重置”和“记录日志并重置”，触发后会将所有的统计记录清零重新开始。

为了避免别人误操作导致数据丢失，可以通过配置进行避免。

如下：

```properties
# 禁止重置 stat
spring.datasource.druid.stat-view-servlet.reset-enable=false
```

> **注意**：当设置为 false 时，表示禁止重置，但按钮依然在，依然能够操作，只不过操作后并不会清除统计记录。

#### 配置过滤器

`spring.datasource.druid.filters` 用于配置 Druid 数据源连接池中的过滤器，
能够为数据库连接执行额外的操作，比如日志记录、监控、SQL解析等。
Druid 中主要提供了 **stat、log4j、wall** 过滤器。

> 如果需要配置多个，可以使用英文逗号“,”进行分割。

1.
stat：用于统计监控信息，可以通过它来获取连接池的连接数、SQL执行次数、执行时间等信息，对应于 `com.alibaba.druid.filter.stat.StatFilter`
类。
> https://github.com/alibaba/druid/wiki/配置_StatFilter

   - `spring.datasource.druid.filter.stat.slow-sql-millis` 用来配置 SQL 慢的标准，即执行时间超过给定时间的就是慢，默认值为3000（3秒），单位毫秒。
   - `spring.datasource.druid.filter.stat.log-slow-sql` 配置是否将慢 SQL 通过日志输出。

```properties
# 慢 SQL 标准，超过 1 秒则为慢 SQL
spring.datasource.druid.filter.stat.slow-sql-millis=1000
# 将慢 SQL 通过日志输出
spring.datasource.druid.filter.stat.log-slow-sql=true
```

2. log4j: 用于记录日志，将日志信息通过SLF4J接口输出。
3. wall: 防火墙过滤器，用于防止SQL注入等攻击

### 四、其它

1. 清除 Druid Monitor
   页面底部的广告图，具体实现查看 [ClearDruidAdConfiguration](../src/main/java/cn/regexp/coding/trainee/config/ClearDruidAdConfiguration.java)

2. 生成百万数据
   - 通过 Navicat 可视化工具生成（**推荐**）
     ![NavicatGenData.png](../imgs/NavicatGenData.png)
   - 通过 MySQL 存储过程生成（**太慢了，不建议**）[generate_data.sql](sql/generate_data.sql)
   - 通过 Java 代码生成（**推荐
     **）[CodingTraineeApplicationTests.java](../src/test/java/cn/regexp/coding/trainee/CodingTraineeApplicationTests.java)
