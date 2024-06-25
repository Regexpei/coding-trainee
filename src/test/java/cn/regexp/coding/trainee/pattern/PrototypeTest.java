package cn.regexp.coding.trainee.pattern;

import cn.hutool.core.io.FileUtil;
import cn.regexp.coding.trainee.pattern.prototype.deep.DeepClone;
import cn.regexp.coding.trainee.pattern.prototype.deep.Student;
import cn.regexp.coding.trainee.pattern.prototype.file.ConfigFile;
import cn.regexp.coding.trainee.pattern.prototype.shallow.ShallowClone;
import cn.regexp.coding.trainee.pattern.prototype.shallow.User;
import com.alibaba.fastjson2.JSON;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author Regexpei
 * @date 2024/6/25 21:17
 * @description 原型模式测试
 */
public class PrototypeTest {

    /**
     * 测试浅克隆
     *
     * @throws CloneNotSupportedException 没有实现 Cloneable 接口时抛出
     */
    @Test
    public void testShallowClone() throws CloneNotSupportedException {
        ShallowClone shallowClone = new ShallowClone();
        shallowClone.setUser(new User("小一", 18));

        ShallowClone clone = shallowClone.clone();

        System.out.println("原来对象：" + shallowClone);
        System.out.println("克隆对象：" + clone);

        System.out.println("原来对象的 User 对象：" + shallowClone.getUser());
        System.out.println("克隆对象的 User 对象：" + clone.getUser());

        System.out.println("原来对象与克隆对象是否为同一对象？" + (shallowClone == clone));
        System.out.println("原来对象与克隆对象中的 User 对象是否为同一对象？" + (shallowClone.getUser() == clone.getUser()));

        System.out.println("原来对象内容：" + JSON.toJSONString(shallowClone));
        System.out.println("克隆对象内容：" + JSON.toJSONString(clone));

        // 修改克隆对象中 User 的名字
        System.out.println("修改克隆对象中 User 的名字");
        clone.getUser().setName("小二");

        System.out.println("原来对象内容：" + JSON.toJSONString(shallowClone));
        System.out.println("克隆对象内容：" + JSON.toJSONString(clone));
        /*
            这个你将会发现，原来对象中的 User 对象的 name 也被修改了，说明是
            浅拷贝对被克隆对象中引用类型对象采用了克隆引用的方式，而不是完整克隆这个对象
         */
    }

    /**
     * 测试深克隆
     *
     * @throws CloneNotSupportedException 没有实现 Cloneable 接口时抛出
     */
    @Test
    public void testDeepClone() throws CloneNotSupportedException {
        DeepClone deepClone = new DeepClone();
        deepClone.setStudent(new Student("小一", 19));

        DeepClone clone = deepClone.clone();
        System.out.println("原来对象：" + deepClone);
        System.out.println("克隆对象：" + clone);

        System.out.println("原来对象的 Student 对象：" + deepClone.getStudent());
        System.out.println("克隆对象的 Student 对象：" + clone.getStudent());

        System.out.println("原来对象与克隆对象是否为同一对象？" + (deepClone == clone));
        System.out.println("原来对象与克隆对象中的 Student 对象是否为同一对象？" + (deepClone.getStudent() == clone.getStudent()));

        System.out.println("原来对象内容：" + JSON.toJSONString(deepClone));
        System.out.println("克隆对象内容：" + JSON.toJSONString(clone));

        System.out.println("修改克隆对象中 Student 的名字");
        clone.getStudent().setName("小二");

        System.out.println("原来对象内容：" + JSON.toJSONString(deepClone));
        System.out.println("克隆对象内容：" + JSON.toJSONString(clone));

    }

    /**
     * 测试文件克隆
     *
     * @throws CloneNotSupportedException 没有实现 Cloneable 接口时抛出
     */
    @Test
    public void testFileClone() throws CloneNotSupportedException {
        String content = FileUtil.readString("application.properties", Charset.defaultCharset());
        // 假如这个对象是从外部传来的
        ConfigFile configFile = new ConfigFile("config.properties", "properties", content);

        ConfigFile clone = configFile.clone();
        // 修改文件名
        clone.setName(System.currentTimeMillis() + "_" + clone.getName());
        System.out.println("克隆对象：" + clone);
    }

    /**
     * 测试 FastJson 实现深克隆
     */
    @Test
    public void testFastJsonDeepClone() {
        DeepClone deepClone = new DeepClone();
        deepClone.setStudent(new Student("小一", 19));

        DeepClone clone = JSON.parseObject(JSON.toJSONString(deepClone), DeepClone.class);
        System.out.println("原来对象：" + deepClone);
        System.out.println("克隆对象：" + clone);

        System.out.println("原来对象的 Student 对象：" + deepClone.getStudent());
        System.out.println("克隆对象的 Student 对象：" + clone.getStudent());

        System.out.println("原来对象与克隆对象是否为同一对象？" + (deepClone == clone));
        System.out.println("原来对象与克隆对象中的 Student 对象是否为同一对象？" + (deepClone.getStudent() == clone.getStudent()));

        System.out.println("原来对象内容：" + JSON.toJSONString(deepClone));
        System.out.println("克隆对象内容：" + JSON.toJSONString(clone));

        System.out.println("修改克隆对象中 Student 的名字");
        clone.getStudent().setName("小二");

        System.out.println("原来对象内容：" + JSON.toJSONString(deepClone));
        System.out.println("克隆对象内容：" + JSON.toJSONString(clone));
    }

    @Test
    public void testArraysCopyOf() {
        Student[] students = new Student[]{new Student("小一", 19)};

        Student[] copyStudents = Arrays.copyOf(students, students.length);
        students[0].setName("小二");

        System.out.println("students: " + JSON.toJSONString(students));
        System.out.println("copyStudents: " + JSON.toJSONString(copyStudents));
    }
}
