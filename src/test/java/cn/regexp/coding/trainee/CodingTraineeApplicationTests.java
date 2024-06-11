package cn.regexp.coding.trainee;

import cn.hutool.core.util.RandomUtil;
import cn.regexp.coding.trainee.entity.User;
import cn.regexp.coding.trainee.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CodingTraineeApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        generateData();
    }

    private void generateData() {
        int total = 1000000;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            String name = RandomUtil.randomString(3);
            int age = RandomUtil.randomInt(1, 100);
            users.add(new User(null, name, age));

            // 每一万条插入一次
            if (i % 10000 == 0) {
                userMapper.insertBatch(users);
                users.clear();
            }
        }
    }

}
