package cn.regexp.coding.trainee.pattern.proxy;

/**
 * @author Regexpei
 * @date 2024/7/7 22:12
 * @description 火车站
 */
public class RailwayStation implements SellTicket {
    @Override
    public void sellTicket(String customerName, Double price) {
        System.out.println(customerName + " 购买了一张火车票，花费：" + price + "元。");
    }
}
