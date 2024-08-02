package cn.regexp.coding.trainee.context;

import cn.regexp.coding.trainee.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Regexpei
 * @date 2024/8/2 23:30
 * @description 应用上下文测试
 * @since 1.0.0
 */
@SpringBootTest
class AppContextTest {

    @Test
    void getBeanByType() {
        IUserService userService = AppContext.getBean(IUserService.class);
        System.out.println(userService);
        assertNotNull(userService);
    }

    @Test
    void getBeanByName() {
        Object userService = AppContext.getBean("userServiceImpl");
        System.out.println(userService);
        assertNotNull(userService);
    }

    @Test
    void getBeanByNameAndType() {
        IUserService userService = AppContext.getBean("userServiceImpl", IUserService.class);
        System.out.println(userService);
        assertNotNull(userService);
    }

    @Test
    void getBeanMap() {
        Map<?, ?> beanMap = AppContext.getBeanMap(IUserService.class);
        System.out.println(beanMap);
        assertFalse(beanMap.isEmpty());
    }

    @Test
    void getBeans() {
        List<IUserService> userServiceList = AppContext.getBeans(IUserService.class);
        System.out.println(userServiceList);
        assertFalse(userServiceList.isEmpty());
    }

    @Test
    void getBeanMapByAnnotation() {
        Map<String, ?> beanMap = AppContext.getBeanMapByAnnotation(Service.class);
        System.out.println(beanMap);
        assertFalse(beanMap.isEmpty());
    }

    @Test
    void getBeansByAnnotation() {
        List<?> beans = AppContext.getBeansByAnnotation(Service.class);
        System.out.println(beans);
        assertFalse(beans.isEmpty());
    }

}