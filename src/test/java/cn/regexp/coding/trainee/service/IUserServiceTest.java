package cn.regexp.coding.trainee.service;

import cn.regexp.coding.trainee.core.service.BaseService;
import cn.regexp.coding.trainee.domain.User;
import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Regexpei
 * @date 2024/8/2 22:37
 * @description 用户服务测试
 * @since 1.0.0
 */
@SpringBootTest
class IUserServiceTest extends BaseService {

    @Test
    void selectAll() {
        List<User> userList = get(IUserService.class).selectAll();
        System.out.println(JSON.toJSONString(userList));
        assertNotNull(userList);
    }

}