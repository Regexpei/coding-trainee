package cn.regexp.coding.trainee.pattern;

import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.thread.ThreadUtil;
import cn.regexp.coding.trainee.pattern.singleton.Enum;
import cn.regexp.coding.trainee.pattern.singleton.*;
import com.alibaba.fastjson2.JSON;
import org.junit.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    /**
     * 测试通过序列化、反序列化破坏单例模式
     */
    @Test
    public void testBreakSingletonBySerializable() {
        // 获取对象
        StaticInnerClassDestroy instance = StaticInnerClassDestroy.getInstance();

        Path path = Paths.get("doc\\" + instance.getClass().getSimpleName() + ".txt");
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            // 将类信息写到本地文件
            oos.writeObject(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (ObjectInputStream oos = new ObjectInputStream(Files.newInputStream(path))) {
            StaticInnerClassDestroy object = (StaticInnerClassDestroy) oos.readObject();
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (ObjectInputStream oos = new ObjectInputStream(Files.newInputStream(path))) {
            StaticInnerClassDestroy object = (StaticInnerClassDestroy) oos.readObject();
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试通过反射破坏单例模式
     */
    @Test
    public void testBreakSingletonByReflect() {
        try {
            // 获取字节码对象
            Class<StaticInnerClass> aClass = StaticInnerClass.class;
            // 获取无参构造方法对象
            Constructor<StaticInnerClass> constructor = aClass.getDeclaredConstructor();
            // 取消访问检查
            constructor.setAccessible(true);
            // 创建对象
            StaticInnerClass staticInnerClass = constructor.newInstance();
            System.out.println(staticInnerClass);

            StaticInnerClass staticInnerClass1 = constructor.newInstance();
            System.out.println(staticInnerClass1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
