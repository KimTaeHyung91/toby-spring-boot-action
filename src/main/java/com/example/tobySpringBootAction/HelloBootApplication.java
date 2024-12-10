package com.example.tobySpringBootAction;

import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class HelloBootApplication {

  private final JdbcTemplate jdbcTemplate;

  public HelloBootApplication(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * Spring LifeCycle 에 따라서 모든 빈이 초기화 된 후 실행되는 코드를 삽입
   */
  @PostConstruct
  void init() {
    jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
  }

  public static void main(String[] args) {
    SpringApplication.run(HelloBootApplication.class, args);
  }
}
