package cn.regexp.coding.trainee.collection;

import cn.regexp.coding.trainee.entity.User;
import org.junit.Test;

/**
 * @author Regexpei
 * @date 2024/4/7 22:18
 * @description
 */
public class HashCodeTest {

    @Test
    public void testByte() {
        byte b = 1;
        System.out.println(Byte.hashCode(b));
        // hash 值为该数
    }

    @Test
    public void testShort() {
        short s = 1;
        System.out.println(Short.hashCode(s));
        // hash 值为该数
    }

    @Test
    public void testInteger() {
        int i = 10;
        System.out.println(Integer.hashCode(i));
        // hash 值为该数
    }

    @Test
    public void testFloat() {
        float f = 1.0f;
        // 0001 0000 0110 0101 0011 0101 0011 0010 0001 0110
        System.out.println(Float.hashCode(f));
        // hash 值为浮点数的各部分二进制表示直接拼接为32位二进制数的十进制数
    }

    @Test
    public void testLong() {
        long l = 1L;
        System.out.println(Long.hashCode(l));
        // 将二进制数的低32位和高32位进行异或
    }

    @Test
    public void testDouble() {
        double d = 1.0;
        // 1072693248
        System.out.println(Double.hashCode(d));
        // Float 方式 + Long 方式
    }

    @Test
    public void testBoolean() {
        boolean b = true;       // 1231
        boolean b1 = false;     // 1237

        System.out.println(Boolean.hashCode(b));
        System.out.println(Boolean.hashCode(b1));
    }

    @Test
    public void testCharacter() {
        char c = 'a';   // 97        0061
        char c1 = '正';   // 27491   6B63

        System.out.println(Character.hashCode(c));
        System.out.println(Character.hashCode(c1));
        // Unicode 编码十六进制数转为十进制数
    }

    @Test
    public void testString() {
        /*
            1888 <==> 1 * 10 ^ 3 + 8 * 10 ^ 2 + 8 * 10 ^ 1 + 8 * 10 ^ 0
            java <==> j * n ^ 3 + a * n ^ 2 + v * n ^ 1 + a * n ^ 0

            字符串由若干个字符组成，而字符的本质是整数（ASCII码）
            在 JDK 中，n 为 31（31是奇素数，统计表明该数能让哈希分布更均匀）
            JVM会将 31 * i 优化为 (i << 5) - i
         */

        String str = "java";
        System.out.println(str.hashCode());
        // 按照十进制整数的展开式，将字符串进行展开计算，其中的基数为31
    }

    @Test
    public void testCustom() {
        User user = new User("小一", 18);
        System.out.println(user.hashCode());
        // String 方式（依次对自定义对象中的属性求取 hash 值）
    }

    @Test
    public void testSameInfoDiffObject() {
        // 相同信息不同对象
        User user = new User("小一", 18);
        User user1 = new User("小一", 18);

        // 如果只重写 equals 方法，哈希值不同
        System.out.println(user.hashCode());      // 992136656
        System.out.println(user1.hashCode());     // 511833308

        // 重写 equals 和 hashCode 方法，哈希值相同
        System.out.println(user.hashCode());      // 23267874
        System.out.println(user1.hashCode());     // 23267874
    }
}
