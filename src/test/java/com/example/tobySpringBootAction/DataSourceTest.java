package com.example.tobySpringBootAction;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

/**
 * jdbc 에 필요한 정보만을 가져오하는 애노테이션
 */
@JdbcTest
public class DataSourceTest {

  @Autowired
  DataSource dataSource;

  @Test
  void connectTest() throws SQLException {
    Connection connection = dataSource.getConnection();
    connection.close();
  }
}
