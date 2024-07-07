package cn.regexp.coding.trainee;

import cn.regexp.coding.trainee.entity.User;
import cn.regexp.coding.trainee.mapper.UserMapper;
import cn.regexp.coding.trainee.util.UserDataGenUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
class CodingTraineeApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @Test
    void contextLoads() {
        // 一千万条数据
        long totalCount = 10000000;
        // 每次插入一千条数据
        int everyBatchSize = 1000;
        // 次数
        long times = totalCount / everyBatchSize;
        System.out.println("times: " + times);

        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (int i = 1; i <= times; i++) {
            int finalI = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    System.out.println("正在进行" + finalI + "次插入");
                    List<User> userList = new ArrayList<>();
                    IntStream.range(0, everyBatchSize).parallel().forEach(j -> {
                        userList.add(UserDataGenUtils.generateUser());
                    });
                    userMapper.insertBatch(userList);
                    System.out.println("第" + finalI + "次插入完成");
                } catch (Exception e) {
                    log.error("插入数据发生异常", e);
                }
            }, taskExecutor);
            futures.add(future);
        }

        CompletableFuture<?>[] futureArray = futures.toArray(new CompletableFuture<?>[0]);
        CompletableFuture.allOf(futureArray);
    }

}
