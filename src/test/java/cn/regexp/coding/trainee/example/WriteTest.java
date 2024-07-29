package cn.regexp.coding.trainee.example;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.junit.Test;

import java.time.Instant;

/**
 * @author Regexpei
 * @date 2024/7/29 19:54
 * @description 测试向 InfluxDB 写数据
 * @since 1.0.0
 */
public class WriteTest {

    public static final String hostUrl = "http://localhost:8086";
    public static final char[] authToken =
            "1N4izYHCQf1Gh2nPncYvDucHmYcjUk0QWsXZyZ5OuMxax8W1eqhZTBJXhUtqk2_rU87BcWzhX8AafTJy7pI5nA==".toCharArray();
    public static final String org = "Regexp";
    public static final String bucket = "example_java";

    @Test
    public void testWriteRecord() {
        try (InfluxDBClient client = InfluxDBClientFactory.create(hostUrl, authToken, org, bucket)) {
            WriteApiBlocking writeApiBlocking = client.getWriteApiBlocking();
            writeApiBlocking.writeRecord(WritePrecision.MS, "temperature,location=north value=30");
        }
    }

    @Test
    public void testWritePoint() {
        try (InfluxDBClient client = InfluxDBClientFactory.create(hostUrl, authToken, org, bucket)) {
            WriteApiBlocking writeApiBlocking = client.getWriteApiBlocking();
            Point point = Point.measurement("temperature")
                    .addTag("location", "west")
                    .addField("value", 29.5)
                    .time(Instant.now(), WritePrecision.MS);

            // point 转换为行协议
            System.out.println(point.toLineProtocol());
            writeApiBlocking.writePoint(point);
        }
    }
}
