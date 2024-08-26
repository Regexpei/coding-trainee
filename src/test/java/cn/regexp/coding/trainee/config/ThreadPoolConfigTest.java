package cn.regexp.coding.trainee.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Regexpei
 * @date 2024/8/24 13:25
 * @description 测试
 * @since 1.0.0
 */
@SpringBootTest
class ThreadPoolConfigTest {

    @Autowired
    private ThreadPoolConfig threadPoolConfig;

    @Test
    public void testGetConfig() {
        IntStream.range(1, 10).forEach(index -> {
            List<int[]> config = threadPoolConfig.getConfig();
            if (config != null && config.size() >= index) {
                int[] configs = config.get(index - 1);
                if (configs.length == 4) {
                    System.out.println("索引：" + index +
                            " 配置：" + configs[0] + " " + configs[1] + " " + configs[2] + " " + configs[3]);
                }
            }
        });
    }
}