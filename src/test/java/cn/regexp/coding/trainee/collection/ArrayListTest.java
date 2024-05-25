package cn.regexp.coding.trainee.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Regexpei
 * @date 2024/4/13 14:27
 * @description
 */
public class ArrayListTest {

    public static final List<String> list = new ArrayList<>();

    static {
        list.add("java");
        list.add("mysql");
        list.add("redis");
        list.add("spring");
        list.add("linux");
        list.add("git");
    }

    @Test
    public void testSameEleAddr() {
        List<String> list = new ArrayList<>();
        list.add("java");    // @857
        list.add("java");    // @857
        list.add("mysql");   // @860
        System.out.println(list);
        // 多个元素引用相同的对象
    }

    @Test
    public void testCovert() {
        // List ==> 数组
        Object[] objects = list.toArray();
        String[] strings = list.toArray(new String[]{});

        // 数组 ==> List
        List<Object> objectList = Arrays.asList(objects);
        List<String> stringList = Arrays.asList(strings);

        System.out.println(objectList);
        System.out.println(stringList);
    }

    @Test
    public void testListToArrayUpdateEffect() {
        String[] strings = list.toArray(new String[]{});
        list.remove(1);
        System.out.println(list);
        System.out.println(Arrays.toString(strings));

        /*
            输出结果：
                [java, redis, spring, linux, git]
                [java, mysql, redis, spring, linux, git]
            分析：
                toArray：将 list 中元素复制到新数组，修改 list 没有影响
         */
    }

    @Test
    public void testArrayToListUpdateEffect() {
        String[] strings = list.toArray(new String[]{});
        List<String> stringList = Arrays.asList(strings);
        strings[0] = "jar";

        System.out.println(stringList);
        System.out.println(Arrays.toString(strings));

        /*
            输出结果：
                [jar, mysql, redis, spring, linux, git]
                [jar, mysql, redis, spring, linux, git]
            分析：
                asList：将数组直接赋值给 stringList 中的数组对象，指向同一内存地址，修改数组有影响
         */
    }
}
