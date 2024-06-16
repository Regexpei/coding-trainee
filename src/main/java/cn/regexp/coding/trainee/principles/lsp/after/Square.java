package cn.regexp.coding.trainee.principles.lsp.after;

import lombok.*;

/**
 * @author Regexpei
 * @date 2024/6/16 12:45
 * @description 正方形
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Square implements Quadrilateral {

    private double side;

    @Override
    public double getLength() {
        return side;
    }

    @Override
    public double getWidth() {
        return side;
    }
}
