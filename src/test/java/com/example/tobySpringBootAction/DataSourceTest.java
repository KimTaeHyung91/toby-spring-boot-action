package com.example.tobySpringBootAction;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@HelloBootTest
public class DataSourceTest {

  @Autowired
  DataSource dataSource;

  @Test
  void connectTest() throws SQLException {
    Connection connection = dataSource.getConnection();
    connection.close();
  }
}
