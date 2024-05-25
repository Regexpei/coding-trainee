package cn.regexp.coding.trainee.reflect;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Regexpei
 * @date 2024/4/7 21:46
 * @description
 */
public class ReflectTest {


    @Test
    public void testDiffType() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(20);

        Method method = list.getClass().getDeclaredMethod("add", Object.class);
        method.invoke(list, "java");

        System.out.println(Arrays.toString(list.toArray()));
    }
}
