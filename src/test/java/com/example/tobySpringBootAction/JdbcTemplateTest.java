package com.example.tobySpringBootAction;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class JdbcTemplateTest {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @BeforeEach
  void init() {
    jdbcTemplate.execute(
        "create table if not exists member(name varchar(50) primary key, count int)");
  }

  @Test
  void insertAndQuery1() {
    jdbcTemplate.update("insert into member values (?,?)", "James", 3);
    jdbcTemplate.update("insert into member values (?,?)", "Spring", 3);

    Long count = jdbcTemplate.queryForObject("select count(*) from member", Long.class);

    assertThat(count).isEqualTo(2);
  }

  /**
   * rollback 여부 확인
   * <p>
   * 전체 테스트 수행 시 insertAndQuery1 에 대하여 작업 완료 시 롤백 여부가 결정
   * <p>
   * 아래 테스트가 통화 시 롤백 통과 못할 시 롤백 적용X
   */
  @Test
  void insertAndQuery2() {
    jdbcTemplate.update("insert into member values (?,?)", "James", 3);
    jdbcTemplate.update("insert into member values (?,?)", "Spring", 3);

    Long count = jdbcTemplate.queryForObject("select count(*) from member", Long.class);

    assertThat(count).isEqualTo(2);
  }
}
