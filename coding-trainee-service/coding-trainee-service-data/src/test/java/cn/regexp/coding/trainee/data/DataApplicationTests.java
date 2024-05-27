package cn.regexp.coding.trainee.data;

import cn.regexp.coding.trainee.datasource.factory.DataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Regexpei
 * @date 2024/5/26 22:56
 * @description
 */
@Slf4j
@SpringBootTest
public class DataApplicationTests {

    @Autowired
    private DataSourceFactory dataSourceFactory;

    @Test
    void contextLoads() {
        String sql = "select * from user";
        try {
            Connection conn1 = dataSourceFactory.get("ds1").getConnection();
            ResultSet resultSet1 = conn1.prepareStatement(sql).executeQuery();
            while (resultSet1.next()) {
                int id = resultSet1.getInt("id");
                String name = resultSet1.getString("name");
                int age = resultSet1.getInt("age");
                log.info("id:{}, name:{}, age:{}", id, name, age);
            }

            log.info("=================================");

            Connection conn2 = dataSourceFactory.get("ds2").getConnection();
            ResultSet resultSet2 = conn2.prepareStatement(sql).executeQuery();
            while (resultSet2.next()) {
                int id = resultSet2.getInt("id");
                String name = resultSet2.getString("name");
                int age = resultSet2.getInt("age");
                log.info("id:{}, name:{}, age:{}", id, name, age);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
