
### 一、modbus4j 依赖库
> maven 仓库 setting.xml 配置中需使用原始配置，自添加的镜像需注释掉。

项目 pom.xml 添加以下内容（与 dependencies 同层级）：
```xml
<repositories>
    <repository>
        <releases>
            <enabled>false</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
        <id>ias-snapshots</id>
        <name>Infinite Automation Snapshot Repository</name>
        <url>https://maven.mangoautomation.net/repository/ias-snapshot/</url>
    </repository>
    <repository>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>ias-releases</id>
        <name>Infinite Automation Release Repository</name>
        <url>https://maven.mangoautomation.net/repository/ias-release/</url>
    </repository>
</repositories>
```
引入依赖，如下：
```xml
<dependency>
    <groupId>com.infiniteautomation</groupId>
    <artifactId>modbus4j</artifactId>
    <version>3.1.0</version>
</dependency>
```