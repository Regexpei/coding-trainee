package cn.regexp.coding.trainee.pattern;

import cn.regexp.coding.trainee.pattern.builder.Order;
import cn.regexp.coding.trainee.pattern.builder.OrderBean;
import cn.regexp.coding.trainee.pattern.builder.Product;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Regexpei
 * @date 2024/6/29 17:04
 * @description 建造者模式测试
 */
@SpringBootTest
public class BuilderTest {

    @Test
    public void testOrder() {
        Product computer = new Product("电脑", "这是一台电脑", 1399.99);
        Product laptop = new Product("笔记本电脑", "这是一台笔记本电脑电脑", 2399.99);
        Product mouse = new Product("鼠标", "这是一个鼠标", 399.99);

        Order order = new Order.Builder()
                .addOrderItem(new Order.OrderItem(computer, 2))
                .addOrderItem(new Order.OrderItem(laptop, 1))
                .addOrderItem(new Order.OrderItem(mouse, 10))
                .setShippingAddress(new Order.ShippingAddress("CN", 1))
                .setPaymentMethod(new Order.PaymentMethod("Alipay", "不要漏了"))
                .build();
        System.out.println(JSON.toJSONString(order, JSONWriter.Feature.PrettyFormat));
    }

    @Test
    public void testBeanDefinitionBuilder() {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(OrderBean.class)
                // 设置为单例
                .setScope(ConfigurableBeanFactory.SCOPE_SINGLETON)
                // 设置为懒加载
                .setLazyInit(true)
                // 设置初始化方法
                .setInitMethodName("initMethod")
                // 设置销毁方法
                .setDestroyMethodName("destroyMethod")
                .getBeanDefinition();

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 将 bean 注册到 BeanFactory 中
        beanFactory.registerBeanDefinition(OrderBean.class.getSimpleName(), beanDefinition);
        // 从工厂中获取 bean
        OrderBean orderBean = beanFactory.getBean(OrderBean.class);
        orderBean.initMethod();
        orderBean.destroyMethod();
        System.out.println(orderBean);
    }
}
