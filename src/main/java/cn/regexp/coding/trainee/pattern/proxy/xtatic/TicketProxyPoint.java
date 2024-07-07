package cn.regexp.coding.trainee.pattern.proxy.xtatic;

import cn.regexp.coding.trainee.pattern.proxy.RailwayStation;
import cn.regexp.coding.trainee.pattern.proxy.SellTicket;

/**
 * @author Regexpei
 * @date 2024/7/7 22:14
 * @description 代售点
 */
public class TicketProxyPoint implements SellTicket {

    private final RailwayStation railwayStation = new RailwayStation();

    @Override
    public void sellTicket(String customerName, Double price) {
        System.out.println("代售点收取服务费！");
        railwayStation.sellTicket(customerName, price);
    }
}