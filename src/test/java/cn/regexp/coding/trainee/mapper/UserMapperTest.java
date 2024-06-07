package cn.regexp.coding.trainee.mapper;

import cn.regexp.coding.trainee.entity.User;
import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Regexpei
 * @date 2024/6/7 19:38
 * @description
 */
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void selectAllFromDb1() {
        List<User> userList = userMapper.selectFromDb1();
        System.out.println(JSON.toJSONString(userList));
        /*
            查询结果：
                [{"age":25,"id":1,"name":"小一"},{"age":30,"id":2,"name":"小二"},{"age":35,"id":3,"name":"小三"}]
         */
    }

    @Test
    void selectAllFromDb2() {
        List<User> userList = userMapper.selectFromDb2();
        System.out.println(JSON.toJSONString(userList));
        /*
            查询结果：
                [{"age":15,"id":1,"name":"大一"},{"age":20,"id":2,"name":"大二"},{"age":25,"id":3,"name":"大三"}]
         */
    }

    @Test
    void selectFromDb1NoAnno() {
        List<User> userList = userMapper.selectFromDb1NoAnno();
        System.out.println(JSON.toJSONString(userList));
        /*
            查询结果：
                [{"age":25,"id":1,"name":"小一"},{"age":30,"id":2,"name":"小二"},{"age":35,"id":3,"name":"小三"}]
         */
    }
}