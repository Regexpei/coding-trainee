#### 描述

用 LinkedHashMap 实现 LRUCache，包括 get 和 put 方法。

#### 介绍

LRU（Least Recently Used），即最近最少使用算法。该算法认为如果一个数据在最近一段时间内没有被访问，那么将来被访问的可能性也不大。 

我们可以简单理解为用当前时间减去最后一次访问时间，值越大被淘汰的优先级就越高。

#### 示例

参考：[LRUCache](../src/main/java/cn/regexp/coding/trainee/utils/LRUCache.java)

#### 源码

##### 1. `cn.regexp.coding.trainee.utils.LRUCache.removeEldestEntry`

**方法作用**：当 Map 中数量大于指定缓存个数时，返回 true，自动删除最老的数据

**调用时机**：当往 Map 中新增元素 `java.util.HashMap.putVal` 后，会在最后调用 `afterNodeInsertion(evict)`，而 LinkedHashMap 
对该方法进行了重写，在 `afterNodeInsertion(evict)` 方法中，会调用 `removeEldestEntry(eldest)` 方法判断是否移除最老元素
