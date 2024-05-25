package cn.regexp.coding.trainee.array;

import cn.regexp.coding.trainee.entity.User;
import cn.regexp.coding.trainee.utils.MyArrayList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Regexpei
 * @date 2024/4/4 23:28
 * @description
 */
@Slf4j
public class DynamicArrayTest {

    public static MyArrayList<String> list = new MyArrayList<>();

    static {
        list.add("java");
        list.add("mysql");
        list.add("redis");
        list.add("spring");
        list.add("linux");
        list.add("git");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetException() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.get(1);
    }

    @Test
    public void testGetException1() {
        try {
            MyArrayList<Integer> list = new MyArrayList<>();
            list.get(1);
            fail("Excepted an ArrayIndexOutOfBoundsException to be thrown");
        } catch (Exception e) {
            log.error("message: {}", e.getMessage(), e);
        }
    }

    @Test
    public void testRemove() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i, i);
        }
        list.remove(0);
        list.remove(2);

        System.out.println(list);
        assertEquals(1, (int) list.get(0));
        assertEquals(4, (int) list.get(2));
        assertEquals(list.size(), 8);
    }

    @Test
    public void testSize() {
        assertEquals(6, list.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(list.isEmpty());

        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testAdd() {
        list.add(2, "redisson");

        assertEquals("redisson", list.get(2));
        assertEquals("redis", list.get(3));

        try {
            list.add(-1, "list");
            fail("Excepted an ArrayIndexOutOfBoundsException to be thrown");
        } catch (Exception e) {
            log.error("message: {}", e.getMessage());
        }

        try {
            list.add(list.size() + 1, "list");
            fail("Excepted an ArrayIndexOutOfBoundsException to be thrown");
        } catch (Exception e) {
            log.error("message: {}", e.getMessage());
        }
    }

    @Test
    public void testGet() {
        String ele = list.get(1);

        assertEquals("mysql", ele);

        try {
            list.get(-1);
            fail("Excepted an ArrayIndexOutOfBoundsException to be thrown");
        } catch (Exception e) {
            log.error("message: {}", e.getMessage());
        }

        try {
            list.get(list.size() + 1);
            fail("Excepted an ArrayIndexOutOfBoundsException to be thrown");
        } catch (Exception e) {
            log.error("message: {}", e.getMessage());
        }
    }


    @Test
    public void testSet() {
        String ele = list.set(1, "mybatis");

        assertEquals("mysql", ele);
        assertEquals("mybatis", list.get(1));

        try {
            list.set(-1, "list");
            fail("Excepted an ArrayIndexOutOfBoundsException to be thrown");
        } catch (Exception e) {
            log.error("message: {}", e.getMessage());
        }

        try {
            list.set(list.size() + 1, "list");
            fail("Excepted an ArrayIndexOutOfBoundsException to be thrown");
        } catch (Exception e) {
            log.error("message: {}", e.getMessage());
        }
    }

    @Test
    public void testRemove1() {
        String ele = list.remove(1);

        assertEquals("mysql", ele);
        assertEquals("redis", list.get(1));

        try {
            list.remove(-1);
            fail("Excepted an ArrayIndexOutOfBoundsException to be thrown");
        } catch (Exception e) {
            log.error("message: {}", e.getMessage());
        }

        try {
            list.remove(list.size() + 1);
            fail("Excepted an ArrayIndexOutOfBoundsException to be thrown");
        } catch (Exception e) {
            log.error("message: {}", e.getMessage());
        }
    }

    @Test
    public void testContains() {
        boolean exist = list.contains("mysql");
        boolean exist1 = list.contains("mysql1");

        assertTrue(exist);
        assertFalse(exist1);
    }

    @Test
    public void testIndexOf() {
        int exist = list.indexOf("mysql");
        int exist1 = list.indexOf("mysql1");

        assertEquals(1, exist);
        assertEquals(-1, exist1);
    }


    @Test
    public void testClear() {
        MyArrayList<User> list = new MyArrayList<>();
        list.add(new User("小一", 18));
        list.add(new User("小二", 17));
        list.add(new User("小三", 16));

        list.clear();

        System.gc();
        assertEquals(0, list.size());
    }
}
