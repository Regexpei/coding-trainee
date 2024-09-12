package cn.regexp.coding.trainee;

import cn.regexp.coding.trainee.entity.User;
import com.alibaba.fastjson2.JSON;

/**
 * @author Regexpei
 * @date 2024/9/12 20:26
 * @description
 * @since 1.0.0
 */
public class Application {

    /*
        {\"name\":\"xiaoyi\",\"age\":20} 12
     */
    public static void main(String[] args) {
        String arg = args[0];
        String arg1 = args[1];

        User user = JSON.parseObject(arg, User.class);
        System.out.println(JSON.toJSONString(user));
        System.out.println(arg1);

    }
}
