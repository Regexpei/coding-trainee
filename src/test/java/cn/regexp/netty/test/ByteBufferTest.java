package cn.regexp.netty.test;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Regexpei
 * @date 2024/9/21 17:34
 * @description ByteBuffer 测试
 * @since 1.0.0
 */
@Slf4j
public class ByteBufferTest {

    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("data.txt");
             FileChannel channel = inputStream.getChannel()) {
            // 准备缓存区
            ByteBuffer buffer = ByteBuffer.allocate(5);
            // 从 channel 中读取数据，往 buffer 中写入
            int len = channel.read(buffer);

            // 若 len 为 -1，则说明读到底了
            while (len != -1) {
                buffer.flip();  // 切换为读模式

                // 是否还有剩余未读数据
                while (buffer.hasRemaining()) {
                    log.info("读取到的字节：{}", (char) buffer.get());
                }

                // 切换为写模式
                buffer.clear();
                len = channel.read(buffer);
            }
        } catch (IOException ignored) {
        }
    }

}
