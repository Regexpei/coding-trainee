package cn.regexp.coding.trainee.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Regexpei
 * @date 2024/4/13 14:27
 * @description
 */
@Slf4j
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

    // ------------------------------ 测试遍历过程中移出列表中的元素 ------------------------------

    /**
     * 这种方式虽然不会报错，但是会导致数据出现问题，比如
     * list: [java, mysql, redis, redis, spring, linux, git]
     * 由于存在两个连续的 redis，当删除第一个时，后面的元素会向前填充，而迭代索引是一直增加的（即 i ），
     * 所以第二个 redis 没有经过检查直接跳过了，导致最终数据为：
     * [java, mysql, redis, spring, linux, git]
     * <p>
     * IDEA 其实也给出了我们提示：Suspicious 'List.remove()' in loop
     * 想必就是因为这个原因
     */
    @Test
    public void testRemoveFor() {
        list.add(2, "redis");
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            if ("redis".equals(list.get(i))) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    /**
     * 将 .class 文件反编译后如下（将 .class 用 IDEA 打开即可）：
     * <p>
     * Iterator var1 = list.iterator();
     * while(var1.hasNext()) {
     * String str = (String)var1.next();
     * if ("redis".equals(str)) {
     * list.remove(str);
     * }
     * }
     * <p>
     * 从代码可以看出，for-each 实际上就是使用迭代器进行遍历元素，当在 for-each 中
     * 通过 java.util.ArrayList#remove 删除元素时，迭代器内部其实是不知道的，而
     * 当执行 java.util.ArrayList.Itr#next() 操作时，会检查内部状态是否一致，
     * 不一致则抛出 java.util.ConcurrentModificationException
     */
    @Test
    public void testRemoveForEach() {
        try {
            for (String str : list) {
                if ("redis".equals(str)) {
                    list.remove(str);
                }
            }
            System.out.println(list);
            Assert.fail("Excepted an ConcurrentModificationException to be thrown");
        } catch (Exception e) {
            log.error("message: {}", e.getMessage(), e);
        }
    }

    /**
     * 这种方式与上面 testRemoveForEach 相比，其实就只是将内部的 remove 换成了
     * 迭代器内部的 remove 方法，既然用的是迭代器自己的，那迭代器内部肯定就能够知道
     * 列表发生变化，从而直接更新其内部状态，所以就不会报错了
     * <p>
     * lastRet: 最后一次返回元素的索引
     * cursor：下一个返回元素的索引
     * <p>
     * public void remove() {
     * if (lastRet < 0)
     * throw new IllegalStateException();
     * checkForComodification();
     * try {
     * // 调用 ArrayList 内部的 remove 进行删除元素，即java.util.ArrayList#remove
     * ArrayList.this.remove(lastRet);
     * // 将最后一次返回元素的索引赋值给下一个返回元素的索引（因为当前元素被删除后，后一个元素前移了）
     * cursor = lastRet;
     * lastRet = -1;
     * // remove 之后，modCount 会加一，所以需要将 modCount 赋值给 expectedModCount，从而保持一致
     * expectedModCount = modCount;
     * } catch (IndexOutOfBoundsException ex) {
     * throw new ConcurrentModificationException();
     * }
     * }
     * <p>
     */
    @Test
    public void testRemoveIterator() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if ("redis".equals(str)) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    /**
     * 通过倒序的方式遍历列表并删除元素，虽然这种方式也能够实现删除元素不报错，但从性能上考虑，
     * 当删除的元素比较多时，时间复杂度会变成 O(n^2)，所以这种方式也不建议使用
     */
    @Test
    public void testRemoveForDesc() {
        for (int i = list.size() - 1; i >= 0; i--) {
            if ("redis".equals(list.get(i))) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    /**
     * 如果使用的是 JDK 1.8，建议使用这种方式删除元素，代码简洁优雅
     * 查看源码，removeIf 底层其实就是使用迭代器的方式进行删除
     */
    @Test
    public void testRemoveIf() {
        list.removeIf("redis"::equals);
        System.out.println(list);
    }
}
