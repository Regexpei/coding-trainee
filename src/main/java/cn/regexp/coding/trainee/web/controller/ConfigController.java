package cn.regexp.coding.trainee.web.controller;

import cn.regexp.coding.trainee.config.ThreadPoolConfig;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Regexpei
 * @date 2024/8/24 13:01
 * @description 配置控制器
 * @since 1.0.0
 */
@Slf4j
@Controller
public class ConfigController {

    @Autowired
    private ThreadPoolConfig threadPoolConfig;

    @GetMapping(value = "/checkConfig/{index}")
    public void checkConfig(@PathVariable Integer index) {
        List<int[]> config = threadPoolConfig.getConfig();
        if (config != null && config.size() >= index) {
            log.info("索引：{}，配置：{}", index, JSON.toJSONString(config.get(index - 1)));
        }
    }
}
