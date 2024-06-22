package cn.regexp.coding.trainee.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Regexpei
 * @date 2024/6/22 18:24
 * @description 最近最少使用缓存
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    /**
     * 容量
     */
    private final int capacity;

    public LRUCache(int capacity) {
        /*
            第一个参数 capacity：指定缓存的容量
            第二个参数负载因子：指定在插入新元素时，如果达到负载因子，则进行扩容
            第三个参数：true 表明按照访问顺序排序（最近访问的元素会被放在 Map 的尾部，最久未访问的元素会被放在头部。
                      当 Map 容量满时，会优先移除头部的元素，即最久未访问的元素），false 表明按照插入顺序排序
         */
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    @Override
    public V get(Object key) {
        return super.get(key);
    }

    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    @Override
    public String toString() {
        return super.entrySet()
                .stream()
                .map(e -> e.getKey() + " : " + e.getValue())
                .collect(Collectors.joining(", "));
    }

}
