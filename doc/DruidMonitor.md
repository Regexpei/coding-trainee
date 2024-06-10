## Druid Monitor

### 介绍

Druid 是一个开源的、高性能的数据库连接池，由阿里巴巴开发并维护。

而 Druid Monitor 则是 Druid 中内置的一个**监控工具**，用于实时监控数据库连接池的运行状态、性能指标以及SQL执行情况等。

通过 Druid Monitor，我们可以对数据源、慢查询、SQL、URI、Session 等内容进行监控，从而及时发现潜在的问题并进行优化。

- **监控数据源**：能够实时监控数据源（如 MySQL、Oracle 等）的连接数、活跃连接数、空闲连接数、等待连接数等指标
- **监控慢查询**：记录并展示慢查询日志，通过配置慢查询阈值，可以自动记录执行时间超过阈值的 SQL 语句
- **SQL 监控**：实时监控 SQL 语句的执行情况，包括执行时间、执行次数、并发数等指标
- **URI 监控**：监控 Web 应用的 URI 请求情况，包括请求次数、响应时间等指标
- **Session 监控**：监控用户的 Session 情况，包括 Session 数量、活跃 Session 数、Session 创建时间等指标

参考资料：https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter

### 简单使用

> 以下内容为主要内容，其余内容请看项目具体文件

1. 添加依赖 [pom.xml](../pom.xml)
    ```xml
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.2.16</version>
    </dependency>
    ```
2. 添加配置 [application.yaml](../src/main/resources/application.yaml)
    ```yaml
    spring:
      datasource:
        druid:
          # Druid 内置监控
          stat-view-servlet:
            # 开启监控
            enabled: true
          # 启用 stat 过滤器，用于统计监控信息，不配置执行的 SQL 将不会被统计       
          filters: stat
    ```

效果展示：

![DruidMonitor_1.png](../imgs/DruidMonitor_1.png)