package cn.regexp.coding.trainee.pattern;

import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.thread.ThreadUtil;
import cn.regexp.coding.trainee.pattern.singleton.Enum;
import cn.regexp.coding.trainee.pattern.singleton.*;
import com.alibaba.fastjson2.JSON;
import org.junit.Test;

import java.util.Set;

/**
 * @author Regexpei
 * @date 2024/6/16 16:22
 * @description 单例模式测试类
 */
public class SingletonTest {

    @Test
    public void testStaticVariable() {
        HungryStaticVariable instance = HungryStaticVariable.getInstance();
        HungryStaticVariable instance1 = HungryStaticVariable.getInstance();

        // 相同实例对象
        Assert.equals(instance, instance1);
        Assert.equals(instance.hashCode(), instance1.hashCode());
    }

    @Test
    public void testStaticCodeBlock() {
        HungryStaticCodeBlock instance = HungryStaticCodeBlock.getInstance();
        HungryStaticCodeBlock instance1 = HungryStaticCodeBlock.getInstance();

        // 相同实例对象
        Assert.equals(instance, instance1);
        Assert.equals(instance.hashCode(), instance1.hashCode());
    }

    @Test
    public void testLazyUnsafe() {
        // 模拟并发，测试是否产生多个实例
        Set<Object> set = new ConcurrentHashSet<>();
        for (int i = 0; i < 100; i++) {
            ThreadUtil.execAsync(() -> set.add(LazyUnsafe.getInstance().hashCode()));
        }

        System.out.println("实例个数：" + set.size());
        System.out.println("实例对象：" + JSON.toJSONString(set));
    }

    @Test
    public void testLazySyncMethod() {
        LazySyncMethod instance = LazySyncMethod.getInstance();
        LazySyncMethod instance1 = LazySyncMethod.getInstance();

        // 相同实例对象
        Assert.equals(instance, instance1);
        Assert.equals(instance.hashCode(), instance1.hashCode());
    }

    @Test
    public void testLazySyncCodeBlock() {
        // 模拟并发，测试是否产生多个实例
        Set<Object> set = new ConcurrentHashSet<>();
        for (int i = 0; i < 100; i++) {
            ThreadUtil.execAsync(() -> set.add(LazySyncCodeBlock.getInstance().hashCode()));
        }

        System.out.println("实例个数：" + set.size());
        System.out.println("实例对象：" + JSON.toJSONString(set));
    }

    @Test
    public void testDoubleCheck() {
        // 模拟并发，测试是否产生多个实例
        Set<Object> set = new ConcurrentHashSet<>();
        for (int i = 0; i < 100; i++) {
            ThreadUtil.execAsync(() -> set.add(DoubleCheck.getInstance().hashCode()));
        }

        System.out.println("实例个数：" + set.size());
        System.out.println("实例对象：" + JSON.toJSONString(set));
    }

    @Test
    public void testStaticInnerClass() {
        // 模拟并发，测试是否产生多个实例
        Set<Object> set = new ConcurrentHashSet<>();
        for (int i = 0; i < 100; i++) {
            ThreadUtil.execAsync(() -> set.add(StaticInnerClass.getInstance().hashCode()));
        }

        System.out.println("实例个数：" + set.size());
        System.out.println("实例对象：" + JSON.toJSONString(set));
    }

    @Test
    public void testEnum() {
        // 模拟并发，测试是否产生多个实例
        Set<Object> set = new ConcurrentHashSet<>();
        for (int i = 0; i < 100; i++) {
            ThreadUtil.execAsync(() -> set.add(Enum.INSTANCE.hashCode()));
        }

        System.out.println("实例个数：" + set.size());
        System.out.println("实例对象：" + JSON.toJSONString(set));
    }

}
