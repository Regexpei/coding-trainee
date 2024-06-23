package cn.regexp.coding.trainee.pattern.factory.config;

import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Regexpei
 * @date 2024/6/23 20:28
 * @description 文件日志实现
 */
@Slf4j
public class FileLogger implements Logger {
    @Override
    public void log(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter("app.log", true))) {
            out.println("File: " + message);
        } catch (IOException e) {
            log.error("记录日志到文件异常, message: {}", e.getMessage(), e);
        }
    }
}
