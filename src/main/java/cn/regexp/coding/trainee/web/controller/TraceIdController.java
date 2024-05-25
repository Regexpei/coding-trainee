package cn.regexp.coding.trainee.web.controller;

import cn.regexp.coding.trainee.service.TraceIdService;
import cn.regexp.coding.trainee.task.MDCRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Regexpei
 * @date 2024/5/22 21:00
 * @description
 */
@Slf4j
@RestController
@RequestMapping
public class TraceIdController {

    @Autowired
    private TraceIdService traceIdService;

    // localhost:8080/testTraceId
    @GetMapping("/testTraceId")
    public String testTraceId() {
        log.info("test trace id");
        return getResult();
    }

    private String getResult() {
        log.info("getResult");
        return "ok";
    }

    // localhost:8080/testTraceIdAsync
    @GetMapping("/testTraceIdAsync")
    public String testTraceIdAsync() {
        log.info("cn.regexp.dailylearning.web.controller.TraceIdController.testTraceIdAsync");
        traceIdService.testTraceIdAsync();
        return "ok";
    }

    // localhost:8080/testTraceIdRunnable
    @GetMapping("/testTraceIdRunnable")
    public String testTraceIdRunnable() {
        log.info("cn.regexp.dailylearning.web.controller.TraceIdController.testTraceIdRunnable");
        new Thread(new MDCRunnable(() ->
                log.info("cn.regexp.dailylearning.task.MDCRunnable"))).start();
        return "ok";
    }

}
