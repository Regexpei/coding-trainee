package cn.regexp.coding.trainee.service.impl;

import cn.regexp.coding.trainee.service.TraceIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Regexpei
 * @date 2024/5/23 22:27
 * @description TraceId 服务层实现类
 */
@Slf4j
@Service
public class TraceIdServiceImpl implements TraceIdService {

    @Async("taskExecutor")
    @Override
    public void testTraceIdAsync() {
        log.info("cn.regexp.dailylearning.service.impl.TraceIdServiceImpl.testTraceIdAsync");
    }
}
