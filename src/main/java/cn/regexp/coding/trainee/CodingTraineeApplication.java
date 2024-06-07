package cn.regexp.coding.trainee;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Regexpei
 * @date 2024/5/21 22:24
 * @description 启动类
 */
@Slf4j
@MapperScan("cn.regexp.coding.trainee.mapper")
@SpringBootApplication
public class CodingTraineeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodingTraineeApplication.class, args);
        log.info("CodingTraineeApplication startup success!");
    }

}
