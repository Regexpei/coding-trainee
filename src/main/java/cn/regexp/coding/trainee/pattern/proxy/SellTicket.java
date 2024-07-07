package cn.regexp.coding.trainee.pattern.proxy;

/**
 * @author Regexpei
 * @date 2024/7/7 22:11
 * @description 售票接口
 */
public interface SellTicket {

    /**
     * 售票
     *
     * @param customerName 购票人
     * @param price        价格
     */
    void sellTicket(String customerName, Double price);
}
