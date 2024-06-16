package cn.regexp.coding.trainee.principles.lsp.before;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Regexpei
 * @date 2024/6/16 12:45
 * @description 正方形
 */
@Setter
@Getter
@AllArgsConstructor
public class Square extends Rectangle{

    @Override
    public void setLength(double length) {
        super.setLength(length);
        super.setWidth(length);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        super.setLength(width);
    }
}
