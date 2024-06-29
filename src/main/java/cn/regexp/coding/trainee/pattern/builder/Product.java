package cn.regexp.coding.trainee.pattern.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Regexpei
 * @date 2024/6/29 17:04
 * @description 商品类
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Product {
    
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品描述
     */
    private String description;
    
    /**
     * 商品价格
     */
    private double price;
}
