package com.example.tobySpringBootAction;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 테스트 코드 내에서 Spring Container 를 띄우고, 등록된 빈 구성 정보를 가져와서 테스트
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HelloBootApplication.class)
@TestPropertySource("classpath:/application.properties")
public class DataSourceTest {

  @Autowired
  DataSource dataSource;

  @Test
  void connectTest() throws SQLException {
    Connection connection = dataSource.getConnection();
    connection.close();
  }
}
