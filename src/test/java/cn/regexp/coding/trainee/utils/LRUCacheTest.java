package cn.regexp.coding.trainee.utils;

import org.junit.Test;

/**
 * @author Regexpei
 * @date 2024/6/22 18:59
 * @description 最近最少使用缓存测试
 */
public class LRUCacheTest {

    /**
     * 最近最少使用缓存
     */
    private static final LRUCache<Integer, String> CACHE = new LRUCache<>(2);

    static {
        CACHE.put(1, "Java");
        CACHE.put(2, "MySQL");
    }

    @Test
    public void testPut() {
        CACHE.put(3, "Redis");
        System.out.println(CACHE);
    }

    @Test
    public void testGet() {
        System.out.println(CACHE.get(1));
    }

}