package cn.regexp.coding.trainee.web.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.regexp.coding.trainee.anno.ApiOprLogAnno;
import cn.regexp.coding.trainee.entity.User;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Regexpei
 * @date 2024/5/19 16:50
 * @description 用户控制层
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get")
    @ApiOprLogAnno(apiType = "查询", apiDetail = "查询单个用户", isSaveResponse = true)
    public User get() {
        return new User("Regexp", 18);
    }

    @PostMapping("/save")
    @ApiOprLogAnno(apiType = "保存", apiDetail = "保存单个用户", isSaveRequest = true)
    public String save(@RequestBody User user) {
        log.debug("save user: {}", JSON.toJSONString(user));
        return "ok";
    }

    @GetMapping("/getEx")
    @ApiOprLogAnno(apiType = "查询", apiDetail = "查询单个用户（异常情况）")
    public User getEx() {
        throw new IllegalArgumentException();
    }

    @GetMapping("/getAsync")
    @ApiOprLogAnno(apiType = "查询", apiDetail = "查询单个用户（异步）")
    public User getAsync() {
        ThreadUtil.execAsync(() -> log.debug("getAsync"));
        return new User();
    }
}
