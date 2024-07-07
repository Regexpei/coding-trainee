package cn.regexp.coding.trainee.pattern;

import cn.regexp.coding.trainee.pattern.proxy.SellTicket;
import cn.regexp.coding.trainee.pattern.proxy.jdk.TicketProxyFactory;
import cn.regexp.coding.trainee.pattern.proxy.xtatic.TicketProxyPoint;
import org.junit.Test;

/**
 * @author Regexpei
 * @date 2024/7/7 22:18
 * @description 代理模式测试类
 */
public class ProxyTest {

    @Test
    public void testStaticProxy() {
        TicketProxyPoint ticketProxyPoint = new TicketProxyPoint();
        ticketProxyPoint.sellTicket("小一", 100.0);
    }

    @Test
    public void testJdkProxy() {
        // 获取到的是代理对象
        SellTicket proxyObject = new TicketProxyFactory().getProxyObject();
        proxyObject.sellTicket("小二", 100.0);
        System.out.println(proxyObject.getClass());

        // 让程序一直跑，然后通过反编译获取代码，否则，程序停止，代理类将在内存中被释放
        // while (true){}
    }
}
