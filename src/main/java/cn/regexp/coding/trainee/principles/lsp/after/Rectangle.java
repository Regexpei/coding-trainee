package cn.regexp.coding.trainee.principles.lsp.after;

import lombok.*;

/**
 * @author Regexpei
 * @date 2024/6/16 12:42
 * @description 长方形类
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rectangle implements Quadrilateral {
    /**
     * 长度
     */
    private double length;

    /**
     * 宽度
     */
    private double width;
}